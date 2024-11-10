package dev.sterner.witchery.payload

import dev.architectury.networking.NetworkManager
import dev.sterner.witchery.Witchery
import dev.sterner.witchery.platform.PlayerMiscDataAttachment
import net.minecraft.client.Minecraft
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.world.entity.player.Player

class SyncMiscS2CPacket(val nbt: CompoundTag) : CustomPacketPayload {

    constructor(friendlyByteBuf: RegistryFriendlyByteBuf) : this(friendlyByteBuf.readNbt()!!)

    constructor(player: Player, data: PlayerMiscDataAttachment.Data) : this(CompoundTag().apply {
        putUUID("Id", player.uuid)
        putBoolean("isWitcheryAligned", data.isWitcheryAligned)
    })

    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> {
        return ID
    }

    private fun write(friendlyByteBuf: RegistryFriendlyByteBuf?) {
        friendlyByteBuf?.writeNbt(nbt)
    }

    fun handleS2C(payload: SyncMiscS2CPacket, context: NetworkManager.PacketContext) {
        val client = Minecraft.getInstance()

        val id = payload.nbt.getUUID("Id")
        val isWitcheryAligned = payload.nbt.getBoolean("isWitcheryAligned")

        val player = client.level?.getPlayerByUUID(id)

        client.execute {
            if (player != null) {
                PlayerMiscDataAttachment.setData(player, PlayerMiscDataAttachment.Data(isWitcheryAligned))
            }
        }
    }

    companion object {
        val ID: CustomPacketPayload.Type<SyncMiscS2CPacket> =
            CustomPacketPayload.Type(Witchery.id("sync_misc_player"))

        val STREAM_CODEC: StreamCodec<in RegistryFriendlyByteBuf?, SyncMiscS2CPacket> =
            CustomPacketPayload.codec(
                { payload, buf -> payload.write(buf) },
                { buf -> SyncMiscS2CPacket(buf!!) }
            )
    }
}