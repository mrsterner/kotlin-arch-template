package dev.sterner.modid.registry

import dev.architectury.registry.registries.DeferredRegister
import dev.sterner.modid.ModMain
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.Registries


object ModDataComponents {

    val DATA: DeferredRegister<DataComponentType<*>> = DeferredRegister.create(ModMain.MODID, Registries.DATA_COMPONENT_TYPE)

}