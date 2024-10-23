package dev.sterner.witchery.platform.infusion.neoforge

import dev.sterner.witchery.neoforge.WitcheryNeoForge.OTHERWHERE_INFUSION_PLAYER_DATA_ATTACHMENT
import dev.sterner.witchery.platform.infusion.OtherwhereInfusionData
import dev.sterner.witchery.platform.infusion.OtherwhereInfusionDataAttachment
import net.minecraft.world.entity.player.Player

object OtherwhereInfusionDataAttachmentImpl {

    @JvmStatic
    fun setInfusion(player: Player, teleportHoldTicks: Int, teleportCooldown: Int){
        val data = OtherwhereInfusionData(teleportHoldTicks, teleportCooldown)
        player.setData(OTHERWHERE_INFUSION_PLAYER_DATA_ATTACHMENT, data)
        OtherwhereInfusionDataAttachment.sync(player, data)
    }

    @JvmStatic
    fun getInfusion(player: Player): OtherwhereInfusionData {
       return player.getData(OTHERWHERE_INFUSION_PLAYER_DATA_ATTACHMENT)
    }
}