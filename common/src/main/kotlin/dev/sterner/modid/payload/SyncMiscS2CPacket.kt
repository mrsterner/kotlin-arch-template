package dev.sterner.modid.payload

import dev.architectury.networking.NetworkManager
import dev.sterner.modid.ModMain
import dev.sterner.modid.platform.MiscPlayerAttachment
import net.minecraft.client.Minecraft
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.NbtOps
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.world.entity.player.Player

class SyncMiscS2CPacket(val nbt: CompoundTag) : CustomPacketPayload {

    constructor(friendlyByteBuf: RegistryFriendlyByteBuf) : this(friendlyByteBuf.readNbt()!!)

    constructor(player: Player, data: MiscPlayerAttachment.Data) : this(CompoundTag().apply {
        putUUID("Id", player.uuid)

        MiscPlayerAttachment.Data.CODEC.encodeStart(NbtOps.INSTANCE, data).resultOrPartial().let {
            put("MiscData", it.get())
        }
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

        val player = client.level?.getPlayerByUUID(id)

        val dataTag = payload.nbt.getCompound("VampData")
        val miscData = MiscPlayerAttachment.Data.CODEC.parse(NbtOps.INSTANCE, dataTag).resultOrPartial()

        client.execute {
            if (player != null && miscData.isPresent) {
                MiscPlayerAttachment.setData(player, miscData.get())
            }
        }
    }

    companion object {
        val ID: CustomPacketPayload.Type<SyncMiscS2CPacket> =
            CustomPacketPayload.Type(ModMain.id("sync_misc_player"))

        val STREAM_CODEC: StreamCodec<in RegistryFriendlyByteBuf?, SyncMiscS2CPacket> =
            CustomPacketPayload.codec(
                { payload, buf -> payload.write(buf) },
                { buf -> SyncMiscS2CPacket(buf!!) }
            )
    }
}