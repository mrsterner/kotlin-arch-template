package dev.sterner.witchery.fabric.datagen

import dev.sterner.witchery.registry.WitcheryItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.models.model.ModelTemplates

class WitcheryModelProvider(output: FabricDataOutput?) : FabricModelProvider(output) {

    override fun generateBlockStateModels(generator: BlockModelGenerators) {

    }

    override fun generateItemModels(genetaror: ItemModelGenerators) {
        genetaror.generateFlatItem(WitcheryItems.GUIDEBOOK.get(), ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.MUTANDIS.get(), ModelTemplates.FLAT_ITEM)

        genetaror.generateFlatItem(WitcheryItems.MANDRAKE_ROOT.get(), ModelTemplates.FLAT_ITEM)

        genetaror.generateFlatItem(WitcheryItems.CLAY_JAR.get(), ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.JAR.get(), ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.BREATH_OF_THE_GODDESS.get(), ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.WHIFF_OF_MAGIC.get(), ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.FOUL_FUME.get(), ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.TEAR_OF_THE_GODDESS.get(), ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.OIL_OF_VITRIOL.get(), ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.EXHALE_OF_THE_HORNED_ONE.get(), ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.HINT_OF_REBIRTH.get(), ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.REEK_OF_MISFORTUNE.get(), ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.ODOR_OF_PURITY.get(), ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.DROP_OF_LUCK.get(), ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.ENDER_DEW.get(), ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.DEMON_BLOOD.get(), ModelTemplates.FLAT_ITEM)

        genetaror.generateFlatItem(WitcheryItems.RITUAL_CHALK.get(), ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.INFERNAL_CHALK.get(), ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.OTHERWHERE_CHALK.get(), ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.GOLDEN_CHALK.get(), ModelTemplates.FLAT_ITEM)

        genetaror.generateFlatItem(WitcheryItems.BELLADONNA_FLOWER.get(),  ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.WATER_ARTICHOKE_GLOBE.get(),  ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.BONE_NEEDLE.get(),  ModelTemplates.FLAT_ITEM)
        genetaror.generateFlatItem(WitcheryItems.DEMON_HEART.get(),  ModelTemplates.FLAT_ITEM)
    }
}