package dev.sterner.witchery.registry

import dev.architectury.registry.CreativeTabRegistry
import dev.architectury.registry.registries.DeferredRegister
import dev.sterner.witchery.Witchery
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.CreativeModeTabs
import kotlin.jvm.optionals.getOrNull

object WitcheryCreativeModeTabs {

    val TABS: DeferredRegister<CreativeModeTab> = DeferredRegister.create(Witchery.MODID, Registries.CREATIVE_MODE_TAB)

    val MAIN = TABS.register("main") {
        CreativeTabRegistry.create {
            it.title(Component.translatable("witchery.main"))
            it.icon { WitcheryItems.GUIDEBOOK.get().defaultInstance }
            it.displayItems { _, output ->
                output.accept(WitcheryItems.GUIDEBOOK.get())
                output.accept(WitcheryItems.MUTANDIS.get())
                output.accept(WitcheryItems.MUTANDIS_EXTREMIS.get())
                output.accept(WitcheryItems.DEEPSLATE_ALTAR_BLOCK.get())
                output.accept(WitcheryItems.CAULDRON.get())
                output.accept(WitcheryItems.COPPER_CAULDRON.get())
                output.accept(WitcheryItems.EXPOSED_COPPER_CAULDRON.get())
                output.accept(WitcheryItems.WEATHERED_COPPER_CAULDRON.get())
                output.accept(WitcheryItems.OXIDIZED_COPPER_CAULDRON.get())
                output.accept(WitcheryItems.IRON_WITCHES_OVEN.get())
                output.accept(WitcheryItems.IRON_WITCHES_OVEN_FUME_EXTENSION.get())
                output.accept(WitcheryItems.COPPER_WITCHES_OVEN.get())
                output.accept(WitcheryItems.EXPOSED_COPPER_WITCHES_OVEN.get())
                output.accept(WitcheryItems.WEATHERED_COPPER_WITCHES_OVEN.get())
                output.accept(WitcheryItems.OXIDIZED_COPPER_WITCHES_OVEN.get())
                output.accept(WitcheryItems.COPPER_WITCHES_OVEN_FUME_EXTENSION.get())
                output.accept(WitcheryItems.EXPOSED_COPPER_WITCHES_OVEN_FUME_EXTENSION.get())
                output.accept(WitcheryItems.WEATHERED_COPPER_WITCHES_OVEN_FUME_EXTENSION.get())
                output.accept(WitcheryItems.OXIDIZED_COPPER_WITCHES_OVEN_FUME_EXTENSION.get())
                output.accept(WitcheryItems.DISTILLERY.get())
                output.accept(WitcheryItems.SPINNING_WHEEL.get())
                output.accept(WitcheryItems.BRAZIER.get())

                output.accept(WitcheryItems.WAYSTONE.get())
                output.accept(WitcheryItems.GLINTWEED.get())
                output.accept(WitcheryItems.EMBER_MOSS.get())
                output.accept(WitcheryItems.SPANISH_MOSS.get())
                output.accept(WitcheryItems.MANDRAKE_SEEDS.get())
                output.accept(WitcheryItems.MANDRAKE_ROOT.get())
                output.accept(WitcheryItems.SNOWBELL_SEEDS.get())
                output.accept(WitcheryItems.ICY_NEEDLE.get())
                output.accept(WitcheryItems.BELLADONNA_SEEDS.get())
                output.accept(WitcheryItems.BELLADONNA_FLOWER.get())
                output.accept(WitcheryItems.WATER_ARTICHOKE_SEEDS.get())
                output.accept(WitcheryItems.WATER_ARTICHOKE_GLOBE.get())
                output.accept(WitcheryItems.WOLFSBANE_SEEDS.get())
                output.accept(WitcheryItems.WOLFSBANE.get())
                output.accept(WitcheryItems.WORMWOOD_SEEDS.get())
                output.accept(WitcheryItems.WORMWOOD.get())
                output.accept(WitcheryItems.GARLIC.get())
                output.accept(WitcheryItems.WOOD_ASH.get())
                output.accept(WitcheryItems.BONE_NEEDLE.get())
                output.accept(WitcheryItems.TAGLOCK.get())
                output.accept(WitcheryItems.ATTUNED_STONE.get())
                output.accept(WitcheryItems.BROOM.get())

                val broom = WitcheryItems.BROOM.get().defaultInstance
                broom.set(WitcheryDataComponents.HAS_OINTMENT.get(), true)

                output.accept(broom)
                output.accept(WitcheryItems.DEMON_HEART.get())
                output.accept(WitcheryItems.GYPSUM.get())
                output.accept(WitcheryItems.TONGUE_OF_DOG.get())
                output.accept(WitcheryItems.TOE_OF_FROG.get())
                output.accept(WitcheryItems.OWLETS_WING.get())
                output.accept(WitcheryItems.WOOL_OF_BAT.get())
                output.accept(WitcheryItems.ENT_TWIG.get())
                output.accept(WitcheryItems.MUTATING_SPRING.get())
                output.accept(WitcheryItems.ROWAN_BERRIES.get())
                output.accept(WitcheryItems.BLOOD_POPPY.get())
                output.accept(WitcheryItems.REFINED_EVIL.get())
                output.accept(WitcheryItems.REDSTONE_SOUP.get())
                output.accept(WitcheryItems.FLYING_OINTMENT.get())
                output.accept(WitcheryItems.GHOST_OF_THE_LIGHT.get())
                output.accept(WitcheryItems.SOUL_OF_THE_WORLD.get())
                output.accept(WitcheryItems.INFERNAL_ANIMUS.get())
                output.accept(WitcheryItems.SPIRIT_OF_OTHERWHERE.get())
                output.accept(WitcheryItems.WITCHES_HAND.get())
                output.accept(WitcheryItems.POPPET.get())
                output.accept(WitcheryItems.ARMOR_PROTECTION_POPPET.get())
                output.accept(WitcheryItems.HUNGER_PROTECTION_POPPET.get())
                output.accept(WitcheryItems.DEATH_PROTECTION_POPPET.get())
                output.accept(WitcheryItems.VAMPIRIC_POPPET.get())
                output.accept(WitcheryItems.VOODOO_POPPET.get())
                output.accept(WitcheryItems.VOODOO_PROTECTION_POPPET.get())

                output.accept(WitcheryItems.CLAY_JAR.get())
                output.accept(WitcheryItems.JAR.get())
                output.accept(WitcheryItems.BREATH_OF_THE_GODDESS.get())
                output.accept(WitcheryItems.WHIFF_OF_MAGIC.get())
                output.accept(WitcheryItems.FOUL_FUME.get())
                output.accept(WitcheryItems.TEAR_OF_THE_GODDESS.get())
                output.accept(WitcheryItems.OIL_OF_VITRIOL.get())
                output.accept(WitcheryItems.EXHALE_OF_THE_HORNED_ONE.get())
                output.accept(WitcheryItems.HINT_OF_REBIRTH.get())
                output.accept(WitcheryItems.PHANTOM_VAPOR.get())
                output.accept(WitcheryItems.REEK_OF_MISFORTUNE.get())
                output.accept(WitcheryItems.ODOR_OF_PURITY.get())
                output.accept(WitcheryItems.DROP_OF_LUCK.get())
                output.accept(WitcheryItems.ENDER_DEW.get())
                output.accept(WitcheryItems.DEMONS_BLOOD.get())
                output.accept(WitcheryItems.MELLIFLUOUS_HUNGER.get())
                output.accept(WitcheryItems.FOCUSED_WILL.get())
                output.accept(WitcheryItems.CONDENSED_FEAR.get())
                output.accept(WitcheryItems.RITUAL_CHALK.get())
                output.accept(WitcheryItems.GOLDEN_CHALK.get())
                output.accept(WitcheryItems.INFERNAL_CHALK.get())
                output.accept(WitcheryItems.OTHERWHERE_CHALK.get())
                output.accept(WitcheryItems.GOLDEN_THREAD.get())
                output.accept(WitcheryItems.IMPREGNATED_FABRIC.get())
                output.accept(WitcheryItems.DREAM_WEAVER.get())
                output.accept(WitcheryItems.DREAM_WEAVER_OF_FLEET_FOOT.get())
                output.accept(WitcheryItems.DREAM_WEAVER_OF_INTENSITY.get())
                output.accept(WitcheryItems.DREAM_WEAVER_OF_NIGHTMARES.get())
                output.accept(WitcheryItems.DREAM_WEAVER_OF_FASTING.get())
                output.accept(WitcheryItems.DREAM_WEAVER_OF_IRON_ARM.get())

                output.accept(WitcheryItems.WITCHES_HAT.get())
                output.accept(WitcheryItems.WITCHES_ROBES.get())
                output.accept(WitcheryItems.WITCHES_SLIPPERS.get())
                output.accept(WitcheryItems.BABA_YAGAS_HAT.get())

                output.accept(WitcheryItems.HUNTER_HELMET.get())
                output.accept(WitcheryItems.HUNTER_CHESTPLATE.get())
                output.accept(WitcheryItems.HUNTER_LEGGINGS.get())
                output.accept(WitcheryItems.HUNTER_BOOTS.get())
                output.accept(WitcheryItems.WOODEN_OAK_STAKE.get())
                output.accept(WitcheryItems.WOODEN_HAWTHORN_STAKE.get())

                output.accept(WitcheryItems.INFINITY_EGG.get())
                output.accept(WitcheryItems.ARTHANA.get())
                output.accept(WitcheryItems.CHALICE.get())
                output.accept(WitcheryItems.PENTACLE.get())
                output.accept(WitcheryItems.WINE_GLASS.get())
                output.accept(WitcheryItems.GRAVESTONE.get())
                output.accept(WitcheryItems.TORN_PAGE.get())
                output.accept(WitcheryItems.QUARTZ_SPHERE.get())
                output.accept(WitcheryItems.SUN_COLLECTOR.get())
                output.accept(WitcheryItems.BLOOD_STAINED_WOOL.get())
                output.accept(WitcheryItems.WOVEN_CRUOR.get())
                output.accept(WitcheryItems.TOP_HAT.get())
                output.accept(WitcheryItems.DRESS_COAT.get())
                output.accept(WitcheryItems.TROUSERS.get())
                output.accept(WitcheryItems.OXFORD_BOOTS.get())
                output.accept(WitcheryItems.CANE_SWORD.get())
                output.accept(WitcheryItems.BATWING_PENDANT.get())

                output.accept(WitcheryItems.DISTURBED_COTTON.get())
                output.accept(WitcheryItems.WISPY_COTTON.get())
                output.accept(WitcheryItems.TORMENTED_TWINE.get())
                output.accept(WitcheryItems.FANCIFUL_THREAD.get())
                output.accept(WitcheryItems.NECROMANTIC_STONE.get())
                output.accept(WitcheryItems.SPECTRAL_DUST.get())

                output.accept(WitcheryItems.BREW_OF_LOVE.get())
                output.accept(WitcheryItems.BREW_OF_INK.get())
                output.accept(WitcheryItems.BREW_OF_REVEALING.get())
                output.accept(WitcheryItems.BREW_OF_EROSION.get())
                output.accept(WitcheryItems.BREW_OF_WEBS.get())
                output.accept(WitcheryItems.BREW_OF_THE_DEPTHS.get())
                output.accept(WitcheryItems.BREW_OF_WASTING.get())
                output.accept(WitcheryItems.BREW_OF_FROST.get())
                output.accept(WitcheryItems.BREW_OF_RAISING.get())
                output.accept(WitcheryItems.BREW_OF_SLEEPING.get())
                output.accept(WitcheryItems.BREW_FLOWING_SPIRIT.get())

                output.accept(WitcheryItems.FLOWING_SPIRIT_BUCKET.get())

                output.accept(WitcheryItems.IRON_CANDELABRA.get())
                output.accept(WitcheryItems.WHITE_IRON_CANDELABRA.get())
                output.accept(WitcheryItems.LIGHT_GRAY_IRON_CANDELABRA.get())
                output.accept(WitcheryItems.GRAY_IRON_CANDELABRA.get())
                output.accept(WitcheryItems.BLACK_IRON_CANDELABRA.get())
                output.accept(WitcheryItems.BROWN_IRON_CANDELABRA.get())
                output.accept(WitcheryItems.RED_IRON_CANDELABRA.get())
                output.accept(WitcheryItems.ORANGE_IRON_CANDELABRA.get())
                output.accept(WitcheryItems.YELLOW_IRON_CANDELABRA.get())
                output.accept(WitcheryItems.LIME_IRON_CANDELABRA.get())
                output.accept(WitcheryItems.GREEN_IRON_CANDELABRA.get())
                output.accept(WitcheryItems.CYAN_IRON_CANDELABRA.get())
                output.accept(WitcheryItems.LIGHT_BLUE_IRON_CANDELABRA.get())
                output.accept(WitcheryItems.BLUE_IRON_CANDELABRA.get())
                output.accept(WitcheryItems.PURPLE_IRON_CANDELABRA.get())
                output.accept(WitcheryItems.MAGENTA_IRON_CANDELABRA.get())
                output.accept(WitcheryItems.PINK_IRON_CANDELABRA.get())

                output.accept(WitcheryItems.ROWAN_LOG.get())
                output.accept(WitcheryItems.ROWAN_WOOD.get())
                output.accept(WitcheryItems.STRIPPED_ROWAN_LOG.get())
                output.accept(WitcheryItems.STRIPPED_ROWAN_WOOD.get())
                output.accept(WitcheryItems.ROWAN_LEAVES.get())
                output.accept(WitcheryItems.ROWAN_BERRY_LEAVES.get())
                output.accept(WitcheryItems.ROWAN_PLANKS.get())
                output.accept(WitcheryItems.ROWAN_STAIRS.get())
                output.accept(WitcheryItems.ROWAN_SLAB.get())
                output.accept(WitcheryItems.ROWAN_FENCE.get())
                output.accept(WitcheryItems.ROWAN_FENCE_GATE.get())
                output.accept(WitcheryItems.ROWAN_DOOR.get())
                output.accept(WitcheryItems.ROWAN_TRAPDOOR.get())
                output.accept(WitcheryItems.ROWAN_PRESSURE_PLATE.get())
                output.accept(WitcheryItems.ROWAN_BUTTON.get())
                output.accept(WitcheryItems.ROWAN_SAPLING.get())
                output.accept(WitcheryItems.ROWAN_SIGN.get())
                output.accept(WitcheryItems.ROWAN_HANGING_SIGN.get())
                output.accept(WitcheryItems.ROWAN_BOAT.get())
                output.accept(WitcheryItems.ROWAN_CHEST_BOAT.get())

                output.accept(WitcheryItems.ALDER_LOG.get())
                output.accept(WitcheryItems.ALDER_WOOD.get())
                output.accept(WitcheryItems.STRIPPED_ALDER_LOG.get())
                output.accept(WitcheryItems.STRIPPED_ALDER_WOOD.get())
                output.accept(WitcheryItems.ALDER_LEAVES.get())
                output.accept(WitcheryItems.ALDER_PLANKS.get())
                output.accept(WitcheryItems.ALDER_STAIRS.get())
                output.accept(WitcheryItems.ALDER_SLAB.get())
                output.accept(WitcheryItems.ALDER_FENCE.get())
                output.accept(WitcheryItems.ALDER_FENCE_GATE.get())
                output.accept(WitcheryItems.ALDER_DOOR.get())
                output.accept(WitcheryItems.ALDER_TRAPDOOR.get())
                output.accept(WitcheryItems.ALDER_PRESSURE_PLATE.get())
                output.accept(WitcheryItems.ALDER_BUTTON.get())
                output.accept(WitcheryItems.ALDER_SAPLING.get())
                output.accept(WitcheryItems.ALDER_SIGN.get())
                output.accept(WitcheryItems.ALDER_HANGING_SIGN.get())
                output.accept(WitcheryItems.ALDER_BOAT.get())
                output.accept(WitcheryItems.ALDER_CHEST_BOAT.get())

                output.accept(WitcheryItems.HAWTHORN_LOG.get())
                output.accept(WitcheryItems.HAWTHORN_WOOD.get())
                output.accept(WitcheryItems.STRIPPED_HAWTHORN_LOG.get())
                output.accept(WitcheryItems.STRIPPED_HAWTHORN_WOOD.get())
                output.accept(WitcheryItems.HAWTHORN_LEAVES.get())
                output.accept(WitcheryItems.HAWTHORN_PLANKS.get())
                output.accept(WitcheryItems.HAWTHORN_STAIRS.get())
                output.accept(WitcheryItems.HAWTHORN_SLAB.get())
                output.accept(WitcheryItems.HAWTHORN_FENCE.get())
                output.accept(WitcheryItems.HAWTHORN_FENCE_GATE.get())
                output.accept(WitcheryItems.HAWTHORN_DOOR.get())
                output.accept(WitcheryItems.HAWTHORN_TRAPDOOR.get())
                output.accept(WitcheryItems.HAWTHORN_PRESSURE_PLATE.get())
                output.accept(WitcheryItems.HAWTHORN_BUTTON.get())
                output.accept(WitcheryItems.HAWTHORN_SAPLING.get())
                output.accept(WitcheryItems.HAWTHORN_SIGN.get())
                output.accept(WitcheryItems.HAWTHORN_HANGING_SIGN.get())
                output.accept(WitcheryItems.HAWTHORN_BOAT.get())
                output.accept(WitcheryItems.HAWTHORN_CHEST_BOAT.get())
            }
        }
    }

    fun modifyExistingTabs(tab: CreativeModeTab, output: CreativeModeTab.Output) {
        val key = BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(tab).getOrNull()

        if (key == CreativeModeTabs.BUILDING_BLOCKS) {
            output.accept(WitcheryItems.ROWAN_LOG.get())
            output.accept(WitcheryItems.ROWAN_WOOD.get())
            output.accept(WitcheryItems.STRIPPED_ROWAN_LOG.get())
            output.accept(WitcheryItems.STRIPPED_ROWAN_WOOD.get())
            output.accept(WitcheryItems.ROWAN_PLANKS.get())
            output.accept(WitcheryItems.ROWAN_STAIRS.get())
            output.accept(WitcheryItems.ROWAN_SLAB.get())
            output.accept(WitcheryItems.ROWAN_FENCE.get())
            output.accept(WitcheryItems.ROWAN_FENCE_GATE.get())
            output.accept(WitcheryItems.ROWAN_DOOR.get())
            output.accept(WitcheryItems.ROWAN_TRAPDOOR.get())
            output.accept(WitcheryItems.ROWAN_PRESSURE_PLATE.get())
            output.accept(WitcheryItems.ROWAN_BUTTON.get())


            output.accept(WitcheryItems.ALDER_LOG.get())
            output.accept(WitcheryItems.ALDER_WOOD.get())
            output.accept(WitcheryItems.STRIPPED_ALDER_LOG.get())
            output.accept(WitcheryItems.STRIPPED_ALDER_WOOD.get())
            output.accept(WitcheryItems.ALDER_PLANKS.get())
            output.accept(WitcheryItems.ALDER_STAIRS.get())
            output.accept(WitcheryItems.ALDER_SLAB.get())
            output.accept(WitcheryItems.ALDER_FENCE.get())
            output.accept(WitcheryItems.ALDER_FENCE_GATE.get())
            output.accept(WitcheryItems.ALDER_DOOR.get())
            output.accept(WitcheryItems.ALDER_TRAPDOOR.get())
            output.accept(WitcheryItems.ALDER_PRESSURE_PLATE.get())
            output.accept(WitcheryItems.ALDER_BUTTON.get())


            output.accept(WitcheryItems.HAWTHORN_LOG.get())
            output.accept(WitcheryItems.HAWTHORN_WOOD.get())
            output.accept(WitcheryItems.STRIPPED_HAWTHORN_LOG.get())
            output.accept(WitcheryItems.STRIPPED_HAWTHORN_WOOD.get())
            output.accept(WitcheryItems.HAWTHORN_PLANKS.get())
            output.accept(WitcheryItems.HAWTHORN_STAIRS.get())
            output.accept(WitcheryItems.HAWTHORN_SLAB.get())
            output.accept(WitcheryItems.HAWTHORN_FENCE.get())
            output.accept(WitcheryItems.HAWTHORN_FENCE_GATE.get())
            output.accept(WitcheryItems.HAWTHORN_DOOR.get())
            output.accept(WitcheryItems.HAWTHORN_TRAPDOOR.get())
            output.accept(WitcheryItems.HAWTHORN_PRESSURE_PLATE.get())
            output.accept(WitcheryItems.HAWTHORN_BUTTON.get())
        } else if (key == CreativeModeTabs.COLORED_BLOCKS) {
            output.accept(WitcheryItems.IRON_CANDELABRA.get())
            output.accept(WitcheryItems.WHITE_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.LIGHT_GRAY_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.GRAY_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.BLACK_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.BROWN_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.RED_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.ORANGE_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.YELLOW_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.LIME_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.GREEN_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.CYAN_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.LIGHT_BLUE_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.BLUE_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.PURPLE_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.MAGENTA_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.PINK_IRON_CANDELABRA.get())
        } else if (key == CreativeModeTabs.NATURAL_BLOCKS) {
            output.accept(WitcheryItems.ROWAN_LOG.get())
            output.accept(WitcheryItems.ALDER_LOG.get())
            output.accept(WitcheryItems.HAWTHORN_LOG.get())

            output.accept(WitcheryItems.ROWAN_LEAVES.get())
            output.accept(WitcheryItems.ROWAN_BERRY_LEAVES.get())
            output.accept(WitcheryItems.ALDER_LEAVES.get())
            output.accept(WitcheryItems.HAWTHORN_LEAVES.get())

            output.accept(WitcheryItems.ROWAN_SAPLING.get())
            output.accept(WitcheryItems.ALDER_SAPLING.get())
            output.accept(WitcheryItems.HAWTHORN_SAPLING.get())

            output.accept(WitcheryItems.GLINTWEED.get())
            output.accept(WitcheryItems.EMBER_MOSS.get())
            output.accept(WitcheryItems.BLOOD_POPPY.get())
            output.accept(WitcheryItems.SPANISH_MOSS.get())

            output.accept(WitcheryItems.MANDRAKE_SEEDS.get())
            output.accept(WitcheryItems.SNOWBELL_SEEDS.get())
            output.accept(WitcheryItems.BELLADONNA_SEEDS.get())
            output.accept(WitcheryItems.WATER_ARTICHOKE_SEEDS.get())
            output.accept(WitcheryItems.WOLFSBANE_SEEDS.get())
            output.accept(WitcheryItems.WORMWOOD_SEEDS.get())
            output.accept(WitcheryItems.GARLIC.get())

            output.accept(WitcheryItems.ROWAN_BERRIES.get())
        } else if (key == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            output.accept(WitcheryItems.IRON_CANDELABRA.get())
            output.accept(WitcheryItems.WHITE_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.LIGHT_GRAY_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.GRAY_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.BLACK_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.BROWN_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.RED_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.ORANGE_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.YELLOW_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.LIME_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.GREEN_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.CYAN_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.LIGHT_BLUE_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.BLUE_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.PURPLE_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.MAGENTA_IRON_CANDELABRA.get())
            output.accept(WitcheryItems.PINK_IRON_CANDELABRA.get())

            output.accept(WitcheryItems.DEEPSLATE_ALTAR_BLOCK.get())
            output.accept(WitcheryItems.CAULDRON.get())
            output.accept(WitcheryItems.COPPER_CAULDRON.get())
            output.accept(WitcheryItems.EXPOSED_COPPER_CAULDRON.get())
            output.accept(WitcheryItems.WEATHERED_COPPER_CAULDRON.get())
            output.accept(WitcheryItems.OXIDIZED_COPPER_CAULDRON.get())
            output.accept(WitcheryItems.IRON_WITCHES_OVEN.get())
            output.accept(WitcheryItems.IRON_WITCHES_OVEN_FUME_EXTENSION.get())
            output.accept(WitcheryItems.COPPER_WITCHES_OVEN.get())
            output.accept(WitcheryItems.EXPOSED_COPPER_WITCHES_OVEN.get())
            output.accept(WitcheryItems.WEATHERED_COPPER_WITCHES_OVEN.get())
            output.accept(WitcheryItems.OXIDIZED_COPPER_WITCHES_OVEN.get())
            output.accept(WitcheryItems.COPPER_WITCHES_OVEN_FUME_EXTENSION.get())
            output.accept(WitcheryItems.EXPOSED_COPPER_WITCHES_OVEN_FUME_EXTENSION.get())
            output.accept(WitcheryItems.WEATHERED_COPPER_WITCHES_OVEN_FUME_EXTENSION.get())
            output.accept(WitcheryItems.OXIDIZED_COPPER_WITCHES_OVEN_FUME_EXTENSION.get())
            output.accept(WitcheryItems.DISTILLERY.get())

            output.accept(WitcheryItems.ARTHANA.get())
            output.accept(WitcheryItems.CHALICE.get())
            output.accept(WitcheryItems.PENTACLE.get())

            output.accept(WitcheryItems.ROWAN_SIGN.get())
            output.accept(WitcheryItems.ROWAN_HANGING_SIGN.get())
            output.accept(WitcheryItems.ALDER_SIGN.get())
            output.accept(WitcheryItems.ALDER_HANGING_SIGN.get())
            output.accept(WitcheryItems.HAWTHORN_SIGN.get())
            output.accept(WitcheryItems.HAWTHORN_HANGING_SIGN.get())
        } else if (key == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            output.accept(WitcheryItems.GUIDEBOOK.get())
            output.accept(WitcheryItems.MUTANDIS.get())
            output.accept(WitcheryItems.MUTANDIS_EXTREMIS.get())
            output.accept(WitcheryItems.WAYSTONE.get())
            output.accept(WitcheryItems.BONE_NEEDLE.get())
            output.accept(WitcheryItems.ICY_NEEDLE.get())
            output.accept(WitcheryItems.TAGLOCK.get())

            output.accept(WitcheryItems.GOLDEN_CHALK.get())
            output.accept(WitcheryItems.RITUAL_CHALK.get())
            output.accept(WitcheryItems.INFERNAL_CHALK.get())
            output.accept(WitcheryItems.OTHERWHERE_CHALK.get())

            output.accept(WitcheryItems.ROWAN_BOAT.get())
            output.accept(WitcheryItems.ROWAN_CHEST_BOAT.get())
            output.accept(WitcheryItems.ALDER_BOAT.get())
            output.accept(WitcheryItems.ALDER_CHEST_BOAT.get())
            output.accept(WitcheryItems.HAWTHORN_BOAT.get())
            output.accept(WitcheryItems.HAWTHORN_CHEST_BOAT.get())
        } else if (key == CreativeModeTabs.COMBAT) {
            output.accept(WitcheryItems.ARTHANA.get())
        } else if (key == CreativeModeTabs.FOOD_AND_DRINKS) {
            output.accept(WitcheryItems.ROWAN_BERRIES.get())
        } else if (key == CreativeModeTabs.INGREDIENTS) {
            output.accept(WitcheryItems.ICY_NEEDLE.get())
            output.accept(WitcheryItems.WOOD_ASH.get())
            output.accept(WitcheryItems.ATTUNED_STONE.get())
            output.accept(WitcheryItems.DEMON_HEART.get())
            output.accept(WitcheryItems.GYPSUM.get())
            output.accept(WitcheryItems.TONGUE_OF_DOG.get())
            output.accept(WitcheryItems.TOE_OF_FROG.get())
            output.accept(WitcheryItems.OWLETS_WING.get())
            output.accept(WitcheryItems.WOOL_OF_BAT.get())
            output.accept(WitcheryItems.ENT_TWIG.get())
            output.accept(WitcheryItems.REFINED_EVIL.get())
            output.accept(WitcheryItems.REDSTONE_SOUP.get())
            output.accept(WitcheryItems.FLYING_OINTMENT.get())
            output.accept(WitcheryItems.GHOST_OF_THE_LIGHT.get())
            output.accept(WitcheryItems.SOUL_OF_THE_WORLD.get())
            output.accept(WitcheryItems.INFERNAL_ANIMUS.get())
            output.accept(WitcheryItems.SPIRIT_OF_OTHERWHERE.get())
            output.accept(WitcheryItems.WITCHES_HAND.get())
            output.accept(WitcheryItems.CLAY_JAR.get())
            output.accept(WitcheryItems.JAR.get())
            output.accept(WitcheryItems.BREATH_OF_THE_GODDESS.get())
            output.accept(WitcheryItems.WHIFF_OF_MAGIC.get())
            output.accept(WitcheryItems.FOUL_FUME.get())
            output.accept(WitcheryItems.TEAR_OF_THE_GODDESS.get())
            output.accept(WitcheryItems.OIL_OF_VITRIOL.get())
            output.accept(WitcheryItems.EXHALE_OF_THE_HORNED_ONE.get())
            output.accept(WitcheryItems.HINT_OF_REBIRTH.get())
            output.accept(WitcheryItems.PHANTOM_VAPOR.get())
            output.accept(WitcheryItems.REEK_OF_MISFORTUNE.get())
            output.accept(WitcheryItems.ODOR_OF_PURITY.get())
            output.accept(WitcheryItems.DROP_OF_LUCK.get())
            output.accept(WitcheryItems.ENDER_DEW.get())
            output.accept(WitcheryItems.DEMONS_BLOOD.get())
            output.accept(WitcheryItems.MELLIFLUOUS_HUNGER.get())
            output.accept(WitcheryItems.FOCUSED_WILL.get())
            output.accept(WitcheryItems.CONDENSED_FEAR.get())
        }
    }
}