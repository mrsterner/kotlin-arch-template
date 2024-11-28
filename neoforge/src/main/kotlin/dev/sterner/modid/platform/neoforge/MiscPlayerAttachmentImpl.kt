package dev.sterner.modid.platform.neoforge

import dev.sterner.modid.neoforge.ModNeoForgeAttachmentRegistry
import dev.sterner.modid.platform.MiscPlayerAttachment
import net.minecraft.world.entity.player.Player

object MiscPlayerAttachmentImpl {

    @JvmStatic
    fun getData(player: Player): MiscPlayerAttachment.Data {
        return player.getData(ModNeoForgeAttachmentRegistry.MISC_PLAYER_DATA_ATTACHMENT)
    }

    @JvmStatic
    fun setData(player: Player, data: MiscPlayerAttachment.Data) {
        player.setData(ModNeoForgeAttachmentRegistry.MISC_PLAYER_DATA_ATTACHMENT, data)
        MiscPlayerAttachment.sync(player, data)
    }
}