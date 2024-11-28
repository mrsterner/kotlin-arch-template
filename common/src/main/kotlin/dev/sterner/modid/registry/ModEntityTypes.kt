package dev.sterner.modid.registry

import dev.architectury.registry.registries.DeferredRegister
import dev.sterner.modid.ModMain
import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.EntityType

object ModEntityTypes {

    val ENTITY_TYPES: DeferredRegister<EntityType<*>> = DeferredRegister.create(ModMain.MODID, Registries.ENTITY_TYPE)

}