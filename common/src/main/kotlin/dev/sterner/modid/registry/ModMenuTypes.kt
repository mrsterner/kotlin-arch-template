package dev.sterner.modid.registry

import dev.architectury.registry.registries.DeferredRegister
import dev.sterner.modid.ModMain
import net.minecraft.core.registries.Registries
import net.minecraft.world.inventory.MenuType


object ModMenuTypes {

    val MENU_TYPES: DeferredRegister<MenuType<*>> = DeferredRegister.create(ModMain.MODID, Registries.MENU)

}