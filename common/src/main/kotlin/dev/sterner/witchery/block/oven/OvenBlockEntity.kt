package dev.sterner.witchery.block.oven

import dev.architectury.registry.menu.ExtendedMenuProvider
import dev.architectury.registry.menu.MenuRegistry
import dev.sterner.witchery.api.block.WitcheryBaseBlockEntity
import dev.sterner.witchery.menu.OvenMenu
import dev.sterner.witchery.recipe.oven.OvenCookingRecipe
import dev.sterner.witchery.registry.WitcheryBlockEntityTypes
import dev.sterner.witchery.registry.WitcheryRecipeTypes
import io.netty.buffer.Unpooled
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
import net.minecraft.core.*
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.util.Mth
import net.minecraft.world.Container
import net.minecraft.world.ContainerHelper
import net.minecraft.world.InteractionResult
import net.minecraft.world.WorldlyContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.player.StackedContents
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.inventory.RecipeCraftingHolder
import net.minecraft.world.inventory.StackedContentsCompatible
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.*
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.AbstractFurnaceBlock
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity
import net.minecraft.world.level.block.state.BlockState
import java.util.function.Function


class OvenBlockEntity(blockPos: BlockPos, blockState: BlockState
) : WitcheryBaseBlockEntity(WitcheryBlockEntityTypes.OVEN.get(), blockPos, blockState), Container, WorldlyContainer,
    RecipeCraftingHolder, StackedContentsCompatible {

    var items: NonNullList<ItemStack> = NonNullList.withSize(3, ItemStack.EMPTY)
    var litTime: Int = 0
    var litDuration: Int = 0
    var cookingProgress: Int = 0
    var cookingTotalTime: Int = 0

    val dataAccess: ContainerData = object : ContainerData {
        override fun get(index: Int): Int {
            return when (index) {
                DATA_LIT_TIME -> this@OvenBlockEntity.litTime
                DATA_LIT_DURATION -> this@OvenBlockEntity.litDuration
                DATA_COOKING_PROGRESS -> this@OvenBlockEntity.cookingProgress
                DATA_COOKING_TOTAL_TIME -> this@OvenBlockEntity.cookingTotalTime
                else -> 0
            }
        }

        override fun set(index: Int, value: Int) {
            when (index) {
                DATA_LIT_TIME -> this@OvenBlockEntity.litTime = value
                DATA_LIT_DURATION -> this@OvenBlockEntity.litDuration = value
                DATA_COOKING_PROGRESS -> this@OvenBlockEntity.cookingProgress = value
                DATA_COOKING_TOTAL_TIME -> this@OvenBlockEntity.cookingTotalTime = value
            }
        }

        override fun getCount(): Int {
            return NUM_DATA_VALUES
        }
    }

    private val recipesUsed = Object2IntOpenHashMap<ResourceLocation>()
    private val quickCheck = RecipeManager.createCheck(WitcheryRecipeTypes.OVEN_RECIPE_TYPE.get())
    private val quickCookCheck = RecipeManager.createCheck(RecipeType.SMOKING)

    private fun isLit(): Boolean {
        return this.litTime > 0
    }

    override fun tick(level: Level, pos: BlockPos, state: BlockState) {
        super.tick(level, pos, state)
        val bl: Boolean = isLit()
        var bl2 = false
        if (isLit()) {
            litTime--
        }

        val itemStack: ItemStack = items[SLOT_FUEL]
        val itemStack2: ItemStack = items[SLOT_INPUT]
        val bl3 = !itemStack2.isEmpty
        val bl4 = !itemStack.isEmpty
        if (isLit() || bl4 && bl3) {
            val recipeHolder = if (bl3) {
                quickCheck.getRecipeFor(SingleRecipeInput(itemStack2), level).orElse(null)
            } else {
                null
            }
            val recipeCookHolder = if (bl3) {
                quickCookCheck.getRecipeFor(SingleRecipeInput(itemStack2), level).orElse(null)
            } else {
                null
            }

            val i: Int = maxStackSize
            if (!isLit() && canBurn(level.registryAccess(), recipeHolder ?: recipeCookHolder, items, i)) {
                litTime = getBurnDuration(itemStack)
                litDuration = litTime
                if (isLit()) {
                    bl2 = true
                    if (bl4) {
                        val item = itemStack.item
                        itemStack.shrink(1)
                        if (itemStack.isEmpty) {
                            val item2 = item.craftingRemainingItem
                            items[SLOT_FUEL] = if (item2 == null) ItemStack.EMPTY else ItemStack(item2)
                        }
                    }
                }
            }

            if (isLit() && canBurn(level.registryAccess(), recipeHolder ?: recipeCookHolder, items, i)) {
                cookingProgress++
                if (cookingProgress == cookingTotalTime) {
                    cookingProgress = 0
                    cookingTotalTime = getTotalCookTime(level)
                    if (burn(level.registryAccess(), recipeHolder ?: recipeCookHolder, items, i)) {
                        recipeUsed = recipeHolder ?: recipeCookHolder
                    }

                    bl2 = true
                }
            } else {
                cookingProgress = 0
            }
        } else if (!isLit() && cookingProgress > 0) {
            cookingProgress = Mth.clamp(cookingProgress - BURN_COOL_SPEED, 0, cookingTotalTime)
        }

        if (bl != isLit()) {
            bl2 = true
            val newState = state.setValue(AbstractFurnaceBlock.LIT, isLit())
            level.setBlock(pos, newState, 3)
        }

        if (bl2) {
            setChanged(level, pos, state)
        }
    }

    override fun onUseWithoutItem(pPlayer: Player): InteractionResult {

        if (pPlayer is ServerPlayer) {
            openMenu(pPlayer)
            return InteractionResult.SUCCESS
        }

        return super.onUseWithoutItem(pPlayer)
    }

    private fun openMenu(player: ServerPlayer){
        MenuRegistry.openExtendedMenu(player, object : ExtendedMenuProvider {
            override fun createMenu(id: Int, inventory: Inventory, player: Player): AbstractContainerMenu {
                val buf = FriendlyByteBuf(Unpooled.buffer())
                saveExtraData(buf)
                return OvenMenu(id, inventory, buf)
            }

            override fun getDisplayName(): Component {
                return Component.translatable("container.witchery.oven_menu")
            }

            override fun saveExtraData(buf: FriendlyByteBuf) {
                buf.writeBlockPos(blockPos)
            }
        })
    }

    private fun canBurn(
        registryAccess: RegistryAccess,
        recipe: RecipeHolder<*>?,
        inventory: NonNullList<ItemStack>,
        maxStackSize: Int
    ): Boolean {
        if (!inventory[SLOT_INPUT].isEmpty && recipe != null) {
            val itemStack = recipe.value().getResultItem(registryAccess)
            if (itemStack.isEmpty) {
                return false
            } else {
                val itemStack2 = inventory[SLOT_RESULT]
                return if (itemStack2.isEmpty) {
                    true
                } else if (!ItemStack.isSameItemSameComponents(itemStack2, itemStack)) {
                    false
                } else {
                    if (itemStack2.count < maxStackSize && itemStack2.count < itemStack2.maxStackSize
                    ) true
                    else itemStack2.count < itemStack.maxStackSize
                }
            }
        } else {
            return false
        }
    }

    private fun burn(
        registryAccess: RegistryAccess,
        recipe: RecipeHolder<*>?,
        inventory: NonNullList<ItemStack>,
        maxStackSize: Int
    ): Boolean {
        if (recipe != null && canBurn(registryAccess, recipe, inventory, maxStackSize)) {
            val itemStack = inventory[SLOT_INPUT]
            val itemStack2 = recipe.value().getResultItem(registryAccess)
            val itemStack3 = inventory[SLOT_RESULT]
            if (itemStack3.isEmpty) {
                inventory[SLOT_RESULT] = itemStack2.copy()
            } else if (ItemStack.isSameItemSameComponents(itemStack3, itemStack2)) {
                itemStack3.grow(1)
            }

            if (itemStack.`is`(Blocks.WET_SPONGE.asItem()) && !inventory[SLOT_FUEL].isEmpty && inventory[SLOT_FUEL].`is`(Items.BUCKET)) {
                inventory[SLOT_FUEL] = ItemStack(Items.WATER_BUCKET)
            }

            itemStack.shrink(1)
            return true
        } else {
            return false
        }
    }

    private fun getBurnDuration(fuel: ItemStack): Int {
        if (fuel.isEmpty) {
            return 0
        } else {
            val item = fuel.item
            return AbstractFurnaceBlockEntity.getFuel().getOrDefault(item, 0) as Int
        }
    }

    private fun getTotalCookTime(level: Level): Int {
        val singleRecipeInput = SingleRecipeInput(getItem(SLOT_INPUT))

        val cookquick = quickCookCheck
            .getRecipeFor(singleRecipeInput, level)
            .map { recipeHolder: RecipeHolder<SmokingRecipe?> -> (recipeHolder.value() as SmokingRecipe).cookingTime }
            .orElse(BURN_TIME_STANDARD)

        return quickCheck
            .getRecipeFor(singleRecipeInput, level)
            .map { recipeHolder: RecipeHolder<OvenCookingRecipe?> -> (recipeHolder.value() as OvenCookingRecipe).cookingTime }
            .orElse(cookquick)
    }

    fun isFuel(stack: ItemStack): Boolean {
        return AbstractFurnaceBlockEntity.getFuel().containsKey(stack.item)
    }

    override fun getSlotsForFace(side: Direction): IntArray {
        return if (side == Direction.DOWN) {
            SLOTS_FOR_DOWN
        } else {
            if (side == Direction.UP) SLOTS_FOR_UP else SLOTS_FOR_SIDES
        }
    }

    override fun canPlaceItemThroughFace(index: Int, itemStack: ItemStack, direction: Direction?): Boolean {
        return this.canPlaceItem(index, itemStack)
    }

    override fun canTakeItemThroughFace(index: Int, stack: ItemStack, direction: Direction): Boolean {
        return if (direction == Direction.DOWN && index == SLOT_FUEL) stack.`is`(Items.WATER_BUCKET) || stack.`is`(Items.BUCKET) else true
    }

    override fun getContainerSize(): Int {
        return items.size
    }

    override fun setItem(slot: Int, stack: ItemStack) {
        val itemStack = items[slot]
        val bl = !stack.isEmpty && ItemStack.isSameItemSameComponents(itemStack, stack)
        items[slot] = stack
        stack.limitSize(this.getMaxStackSize(stack))
        if (slot == SLOT_INPUT && !bl) {
            this.cookingTotalTime = getTotalCookTime(this.level!!)
            this.cookingProgress = 0
            this.setChanged()
        }
    }

    override fun canPlaceItem(slot: Int, stack: ItemStack): Boolean {
        if (slot == SLOT_RESULT) {
            return false
        } else if (slot != SLOT_FUEL) {
            return true
        } else {
            val itemStack = items[SLOT_FUEL]
            return AbstractFurnaceBlockEntity.isFuel(stack) || stack.`is`(Items.BUCKET) && !itemStack.`is`(Items.BUCKET)
        }
    }

    override fun setRecipeUsed(recipe: RecipeHolder<*>?) {
        if (recipe != null) {
            val resourceLocation = recipe.id()
            recipesUsed.addTo(resourceLocation, 1)
        }
    }

    override fun getRecipeUsed(): RecipeHolder<*>? {
        return null
    }

    override fun fillStackedContents(contents: StackedContents) {
        for (itemStack in this.items) {
            contents.accountStack(itemStack)
        }
    }

    override fun loadAdditional(tag: CompoundTag, pRegistries: HolderLookup.Provider) {
        super.loadAdditional(tag, pRegistries)
        this.items = NonNullList.withSize(this.containerSize, ItemStack.EMPTY)
        ContainerHelper.loadAllItems(tag, this.items, pRegistries)
        this.litTime = tag.getShort("BurnTime").toInt()
        this.cookingProgress = tag.getShort("CookTime").toInt()
        this.cookingTotalTime = tag.getShort("CookTimeTotal").toInt()
        this.litDuration = this.getBurnDuration(items[SLOT_FUEL])
        val compoundTag = tag.getCompound("RecipesUsed")

        for (string in compoundTag.allKeys) {
            recipesUsed.put(ResourceLocation.parse(string), compoundTag.getInt(string))
        }
    }

    override fun saveAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.saveAdditional(tag, registries)
        tag.putShort("BurnTime", litTime.toShort())
        tag.putShort("CookTime", cookingProgress.toShort())
        tag.putShort("CookTimeTotal", cookingTotalTime.toShort())
        ContainerHelper.saveAllItems(tag, this.items, registries)
        val compoundTag = CompoundTag()
        recipesUsed.forEach { (resourceLocation: ResourceLocation, integer: Int?) ->
            compoundTag.putInt(
                resourceLocation.toString(),
                integer!!
            )
        }
        tag.put("RecipesUsed", compoundTag)
    }

    override fun isEmpty(): Boolean {
        for (itemStack in this.items) {
            if (!itemStack.isEmpty) {
                return false
            }
        }

        return true
    }

    override fun getItem(slot: Int): ItemStack {
        return this.items[slot]
    }

    override fun removeItem(slot: Int, amount: Int): ItemStack {
        val itemStack = ContainerHelper.removeItem(this.items, slot, amount)
        if (!itemStack.isEmpty) {
            this.setChanged()
        }

        return itemStack
    }

    override fun removeItemNoUpdate(slot: Int): ItemStack {
        return ContainerHelper.takeItem(this.items, slot)
    }

    override fun stillValid(player: Player): Boolean {
        return Container.stillValidBlockEntity(this, player)
    }

    override fun clearContent() {
        this.items.clear()
    }

    companion object {
        const val SLOT_INPUT: Int = 0
        const val SLOT_FUEL: Int = 1
        const val SLOT_RESULT: Int = 2
        val SLOTS_FOR_UP: IntArray = intArrayOf(0)
        val SLOTS_FOR_DOWN: IntArray = intArrayOf(2, 1)
        val SLOTS_FOR_SIDES: IntArray = intArrayOf(1)
        const val DATA_LIT_TIME: Int = 0
        const val DATA_LIT_DURATION: Int = 1
        const val DATA_COOKING_PROGRESS: Int = 2
        const val DATA_COOKING_TOTAL_TIME: Int = 3
        const val NUM_DATA_VALUES: Int = 4
        const val BURN_TIME_STANDARD: Int = 200
        const val BURN_COOL_SPEED: Int = 2
    }
}