package dev.sterner.modid.neoforge

import dev.sterner.modid.ModMain
import dev.sterner.modid.neoforge.event.ModNeoForgeClientEvent
import net.minecraft.client.Minecraft
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.runForDist


@Mod(ModMain.MODID)
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
object ModNeoForge {

    init {
        ModNeoForgeAttachmentRegistry.ATTACHMENT_TYPES.register(MOD_BUS)
        ModMain.init()

        runForDist(
            clientTarget = {
                MOD_BUS.addListener(::onClientSetup)
                MOD_BUS.register(ModNeoForgeClientEvent)
                Minecraft.getInstance()
            },
            serverTarget = {
                MOD_BUS.addListener(::onServerSetup)
                "test"
            }
        )
    }

    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {

    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        ModMain.initClient()
    }


    @SubscribeEvent
    private fun registerScreens(event: RegisterMenuScreensEvent) {

    }

    @SubscribeEvent
    fun onRegisterCapabilities(event: RegisterCapabilitiesEvent) {

    }

    @SubscribeEvent
    fun modifyAttributes(event: EntityAttributeModificationEvent) {

    }
}
