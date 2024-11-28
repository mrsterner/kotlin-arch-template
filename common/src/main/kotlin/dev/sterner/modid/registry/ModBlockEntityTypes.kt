package dev.sterner.modid.registry

import dev.architectury.registry.registries.DeferredRegister
import dev.sterner.modid.ModMain
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.entity.BlockEntityType

object ModBlockEntityTypes {

    val BLOCK_ENTITY_TYPES: DeferredRegister<BlockEntityType<*>> =
        DeferredRegister.create(ModMain.MODID, Registries.BLOCK_ENTITY_TYPE)


}