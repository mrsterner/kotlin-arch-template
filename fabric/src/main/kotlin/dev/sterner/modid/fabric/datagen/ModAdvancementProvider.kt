package dev.sterner.modid.fabric.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider
import net.minecraft.advancements.*
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class ModAdvancementProvider(output: FabricDataOutput, registryLookup: CompletableFuture<HolderLookup.Provider>) :
    FabricAdvancementProvider(output, registryLookup) {

    override fun generateAdvancement(registryLookup: HolderLookup.Provider?, consumer: Consumer<AdvancementHolder>) {

    }

    companion object {

    }

}