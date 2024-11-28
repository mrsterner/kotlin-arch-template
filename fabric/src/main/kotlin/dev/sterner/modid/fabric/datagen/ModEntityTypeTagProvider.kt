package dev.sterner.modid.fabric.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.EntityTypeTagProvider
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class ModEntityTypeTagProvider(
    output: FabricDataOutput?,
    completableFuture: CompletableFuture<HolderLookup.Provider>?
) : EntityTypeTagProvider(output, completableFuture) {

    override fun addTags(wrapperLookup: HolderLookup.Provider) {

    }
}