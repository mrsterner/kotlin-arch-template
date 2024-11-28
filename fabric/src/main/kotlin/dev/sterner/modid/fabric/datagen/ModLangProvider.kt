package dev.sterner.modid.fabric.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class ModLangProvider(dataOutput: FabricDataOutput, registryLookup: CompletableFuture<HolderLookup.Provider>) :
    FabricLanguageProvider(dataOutput, registryLookup) {

    override fun generateTranslations(registryLookup: HolderLookup.Provider?, builder: TranslationBuilder) {

    }
}