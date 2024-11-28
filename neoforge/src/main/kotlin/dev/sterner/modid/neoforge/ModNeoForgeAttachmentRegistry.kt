package dev.sterner.modid.neoforge

import dev.sterner.modid.ModMain
import dev.sterner.modid.platform.*
import net.neoforged.neoforge.attachment.AttachmentType
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NeoForgeRegistries
import java.util.function.Supplier

object ModNeoForgeAttachmentRegistry {

    val ATTACHMENT_TYPES: DeferredRegister<AttachmentType<*>> =
        DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, ModMain.MODID)

    @JvmStatic
    val MISC_PLAYER_DATA_ATTACHMENT: Supplier<AttachmentType<MiscPlayerAttachment.Data>> =
        ATTACHMENT_TYPES.register(
            "misc_player",
            Supplier {
                AttachmentType.builder(Supplier { MiscPlayerAttachment.Data() })
                    .serialize(MiscPlayerAttachment.Data.CODEC)
                    .copyOnDeath()
                    .build()
            }
        )
}