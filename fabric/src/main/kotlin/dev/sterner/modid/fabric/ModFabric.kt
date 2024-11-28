package dev.sterner.modid.fabric

import dev.sterner.modid.ModMain
import dev.sterner.modid.fabric.registry.ModFabricAttachmentRegistry
import dev.sterner.modid.registry.*
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.CoreShaderRegistrationCallback
import java.io.IOException


class ModFabric : ModInitializer, ClientModInitializer {

    override fun onInitialize() {
        ModFabricAttachmentRegistry.init()
        ModMain.init()
    }

    override fun onInitializeClient() {
        ModMain.initClient()

        CoreShaderRegistrationCallback.EVENT.register(this::registerShaders)
    }

    @Throws(IOException::class)
    private fun registerShaders(ctx: CoreShaderRegistrationCallback.RegistrationContext) {

    }
}


