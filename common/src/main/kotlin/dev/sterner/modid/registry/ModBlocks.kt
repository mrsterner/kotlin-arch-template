package dev.sterner.modid.registry

import dev.architectury.registry.registries.DeferredRegister
import dev.sterner.modid.ModMain.MODID
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.*

object ModBlocks {

    val BLOCKS: DeferredRegister<Block> = DeferredRegister.create(MODID, Registries.BLOCK)

}