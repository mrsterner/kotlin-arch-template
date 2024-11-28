package dev.sterner.modid.fabric.registry

import dev.sterner.modid.platform.*
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry
import net.fabricmc.fabric.api.attachment.v1.AttachmentType

object ModFabricAttachmentRegistry {

    @Suppress("UnstableApiUsage")
    val MISC_PLAYER_DATA_ATTACHMENT: AttachmentType<MiscPlayerAttachment.Data> =
        AttachmentRegistry.builder<MiscPlayerAttachment.Data>()
            .persistent(MiscPlayerAttachment.Data.CODEC)
            .initializer { MiscPlayerAttachment.Data() }
            .buildAndRegister(MiscPlayerAttachment.Data.ID)

    fun init() {

    }
}