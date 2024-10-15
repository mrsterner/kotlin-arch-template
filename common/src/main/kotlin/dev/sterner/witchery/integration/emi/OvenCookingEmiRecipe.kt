package dev.sterner.witchery.integration.emi

import dev.emi.emi.api.recipe.EmiRecipe
import dev.emi.emi.api.recipe.EmiRecipeCategory
import dev.emi.emi.api.render.EmiTexture
import dev.emi.emi.api.stack.EmiIngredient
import dev.emi.emi.api.stack.EmiStack
import dev.emi.emi.api.widget.WidgetHolder
import dev.sterner.witchery.Witchery
import dev.sterner.witchery.recipe.oven.OvenCookingRecipe
import net.minecraft.resources.ResourceLocation

class OvenCookingEmiRecipe(val recipeId: ResourceLocation, val recipe: OvenCookingRecipe) : EmiRecipe {

    override fun getCategory(): EmiRecipeCategory {
        return WitcheryEmiPlugin.OVEN_COOKING_CATEGORY
    }

    override fun getId(): ResourceLocation {
        return recipeId
    }

    override fun getInputs(): MutableList<EmiIngredient> {
        val mutableList = mutableListOf<EmiIngredient>()
        mutableList.add(EmiIngredient.of(recipe.ingredient))
        mutableList.add(EmiIngredient.of(recipe.extraIngredient))
        return mutableList
    }

    override fun getOutputs(): MutableList<EmiStack> {
        val mutableList = mutableListOf<EmiStack>()
        mutableList.add(EmiStack.of(recipe.result))
        mutableList.add(EmiStack.of(recipe.extraOutput))
        return mutableList
    }

    override fun getDisplayWidth(): Int {
        return 18 * 8
    }

    override fun getDisplayHeight(): Int {
        return 18 * 6
    }

    override fun addWidgets(widgets: WidgetHolder) {
        widgets.addTexture(Witchery.id("textures/gui/oven_emi.png"), 18, 9, 108,57,0,0)
        EmiTexture.FULL_FLAME

        widgets.add(
            WitcherySlotWidget(EmiStack.of(recipe.result), 2 + 2 + 18 + 24 + 24+ 9, 50 - 18 - 4)
                .drawBack(false)
        )

        widgets.add(
            WitcherySlotWidget(EmiStack.of(recipe.extraIngredient.items[0]), 2 + 2 + 18 + 36 + 36 + 12 + 1, 48)
                .drawBack(false)
        )

        widgets.add(
            WitcherySlotWidget(EmiStack.of(recipe.extraOutput), 2 + 2 + 18 + 36 + 36 + 12 + 1, 9)
                .drawBack(false)
        )

        widgets.add(
            WitcherySlotWidget(EmiStack.of(recipe.ingredient.items[0]), 2 + 18 - 2, 10)
                .drawBack(false)
        )
    }
}