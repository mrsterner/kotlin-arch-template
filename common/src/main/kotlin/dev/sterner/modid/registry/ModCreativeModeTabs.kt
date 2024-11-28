package dev.sterner.modid.registry

import dev.architectury.registry.CreativeTabRegistry
import dev.architectury.registry.registries.DeferredRegister
import dev.sterner.modid.ModMain
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Items

object ModCreativeModeTabs {

    val TABS: DeferredRegister<CreativeModeTab> = DeferredRegister.create(ModMain.MODID, Registries.CREATIVE_MODE_TAB)

    val MAIN = TABS.register("main") {
        CreativeTabRegistry.create {
            it.title(Component.translatable("${ModMain.MODID}.main"))
            it.icon { Items.STICK.defaultInstance }
            it.displayItems { _, output ->

            }
        }
    }
}