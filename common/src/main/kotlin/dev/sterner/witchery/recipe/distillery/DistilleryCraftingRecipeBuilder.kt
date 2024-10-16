package dev.sterner.witchery.recipe.distillery

import dev.sterner.witchery.recipe.WitcheryRecipeBuilder
import net.minecraft.advancements.AdvancementRequirements
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.Criterion
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class DistilleryCraftingRecipeBuilder(
    private var inputItems: MutableList<ItemStack> = mutableListOf(),
    private var outputStack: MutableList<ItemStack> = mutableListOf(),
    private val altarPower: Int = 0
) : WitcheryRecipeBuilder() {

    var order = 0

    private val criteria: MutableMap<String, Criterion<*>> = LinkedHashMap()
    private var group: String? = null

    companion object {
        fun create(): DistilleryCraftingRecipeBuilder {
            return DistilleryCraftingRecipeBuilder()
        }
    }

    fun addInput(itemStack: ItemStack): DistilleryCraftingRecipeBuilder {
        inputItems.add(itemStack)
        order++
        return this
    }

    fun addOutput(itemStack: ItemStack, count: Int): DistilleryCraftingRecipeBuilder {
        itemStack.count = count
        outputStack += itemStack
        return this
    }

    fun addOutput(itemStack: ItemStack): DistilleryCraftingRecipeBuilder {
        outputStack += itemStack
        return this
    }

    fun addOutput(item: Item): DistilleryCraftingRecipeBuilder {
        outputStack += item.defaultInstance
        return this
    }

    fun setAltarPower(power: Int): DistilleryCraftingRecipeBuilder {
        return DistilleryCraftingRecipeBuilder(inputItems, outputStack, power)
    }

    override fun unlockedBy(name: String, criterion: Criterion<*>): DistilleryCraftingRecipeBuilder {
        criteria[name] = criterion
        return this
    }

    override fun group(groupName: String?): DistilleryCraftingRecipeBuilder {
        group = groupName
        return this
    }

    override fun getResult(): Item {
        return outputStack[0].item
    }

    override fun save(recipeOutput: RecipeOutput, id: ResourceLocation) {
        val builder = recipeOutput.advancement()
            .addCriterion("has_recipe", RecipeUnlockedTrigger.unlocked(id))
            .rewards(AdvancementRewards.Builder.recipe(id))
            .requirements(AdvancementRequirements.Strategy.OR)

        criteria.forEach { (name, criterion) ->
            builder.addCriterion(name, criterion)
        }

        val cauldronCraftingRecipe = DistilleryCraftingRecipe(inputItems, outputStack, altarPower)
        recipeOutput.accept(
            suffixHash(id.withPrefix("distillery_crafting/"), inputItems),
            cauldronCraftingRecipe,
            builder.build(suffixHash(id.withPrefix("recipes/distillery_crafting/"), inputItems))
        )
    }
}