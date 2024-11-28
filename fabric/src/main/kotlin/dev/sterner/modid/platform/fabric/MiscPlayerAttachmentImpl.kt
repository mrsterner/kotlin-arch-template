package dev.sterner.modid.platform.fabric

import dev.sterner.modid.fabric.registry.ModFabricAttachmentRegistry
import dev.sterner.modid.platform.MiscPlayerAttachment
import net.minecraft.world.entity.player.Player

object MiscPlayerAttachmentImpl {

    @JvmStatic
    fun getData(player: Player): MiscPlayerAttachment.Data {
        return player.getAttachedOrCreate(ModFabricAttachmentRegistry.MISC_PLAYER_DATA_ATTACHMENT)
    }

    @JvmStatic
    fun setData(player: Player, data: MiscPlayerAttachment.Data) {
        player.setAttached(ModFabricAttachmentRegistry.MISC_PLAYER_DATA_ATTACHMENT, data)
        MiscPlayerAttachment.sync(player, data)
    }
}