package dev.sterner.modid.registry

import dev.architectury.registry.registries.DeferredRegister
import dev.sterner.modid.ModMain
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.*


object ModItems {

    val ITEMS: DeferredRegister<Item> = DeferredRegister.create(ModMain.MODID, Registries.ITEM)

}