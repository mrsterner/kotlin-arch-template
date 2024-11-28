package dev.sterner.modid.fabric.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.core.HolderLookup
import net.minecraft.data.recipes.*
import java.util.concurrent.CompletableFuture

class ModRecipeProvider(output: FabricDataOutput, val registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricRecipeProvider(output, registriesFuture) {

    override fun buildRecipes(exporter: RecipeOutput) {


    }
}