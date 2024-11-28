package dev.sterner.modid.neoforge.event

import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent
import net.neoforged.neoforge.client.event.RegisterShadersEvent
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent


object ModNeoForgeClientEvent {

    @SubscribeEvent
    fun onEntityRendererRegistry(event: EntityRenderersEvent.RegisterRenderers) {

    }

    @SubscribeEvent
    private fun registerLayerDefinitions(event: EntityRenderersEvent.RegisterLayerDefinitions) {

    }

    @SubscribeEvent
    fun registerParticle(event: RegisterParticleProvidersEvent) {

    }

    @SubscribeEvent
    fun registerClientExtensions(event: RegisterClientExtensionsEvent) {

    }

    @SubscribeEvent
    fun registerShader(event: RegisterShadersEvent){

    }
}