package dev.sterner.modid.registry

import dev.architectury.registry.registries.DeferredRegister
import dev.sterner.modid.ModMain
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.feature.Feature

object ModFeatures {

    val FEATURES: DeferredRegister<Feature<*>> =
        DeferredRegister.create(ModMain.MODID, Registries.FEATURE)

}