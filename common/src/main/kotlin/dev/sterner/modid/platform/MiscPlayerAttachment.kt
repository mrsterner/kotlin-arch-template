package dev.sterner.modid.platform

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.architectury.injectables.annotations.ExpectPlatform
import dev.sterner.modid.ModMain
import dev.sterner.modid.payload.SyncMiscS2CPacket
import dev.sterner.modid.registry.ModPayloads
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.player.Player

object MiscPlayerAttachment {

    @ExpectPlatform
    @JvmStatic
    fun getData(player: Player): Data {
        throw AssertionError()
    }

    @ExpectPlatform
    @JvmStatic
    fun setData(player: Player, data: Data) {
        throw AssertionError()
    }

    fun sync(player: Player, data: Data) {
        if (player.level() is ServerLevel) {
            ModPayloads.sendToPlayers(player.level(), player.blockPosition(), SyncMiscS2CPacket(player, data))
        }
    }

    class Data(var testBool: Boolean = false) {

        companion object {
            val CODEC: Codec<Data> = RecordCodecBuilder.create { instance ->
                instance.group(
                    Codec.BOOL.fieldOf("testBool").forGetter { it.testBool },
                ).apply(instance, ::Data)
            }

            val ID: ResourceLocation = ModMain.id("misc_player_data")
        }
    }
}