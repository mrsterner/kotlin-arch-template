package dev.sterner.witchery.platform.fabric

import dev.sterner.witchery.api.attachment.MutandisData
import dev.sterner.witchery.api.attachment.MutandisLevelAttachment
import dev.sterner.witchery.platform.MutandisLevelDataAttachmentPlatform
import dev.sterner.witchery.platform.MutandisLevelDataAttachmentPlatform.CACHE_LIFETIME
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry
import net.fabricmc.fabric.api.attachment.v1.AttachmentType
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block

object MutandisLevelDataAttachmentPlatformImpl {

    @JvmStatic
    @Suppress("UnstableApiUsage")
    fun getMap(level: ServerLevel): MutableMap<BlockPos, MutandisData> {
        return  level.getAttachedOrCreate(LEVEL_DATA_TYPE).mutandisCacheMap
    }

    @JvmStatic
    @Suppress("UnstableApiUsage")
    fun getTagForBlockPos(level: ServerLevel, pos: BlockPos): TagKey<Block>? {
        val attachments = level.getAttachedOrCreate(LEVEL_DATA_TYPE)
        return attachments.mutandisCacheMap[pos]?.tag
    }

    @JvmStatic
    @Suppress("UnstableApiUsage")
    fun setTagForBlockPos(level: ServerLevel, pos: BlockPos, tag: TagKey<Block>)  {
        level.getAttachedOrCreate(LEVEL_DATA_TYPE).mutandisCacheMap[pos] = MutandisData(tag, CACHE_LIFETIME)
    }

    @JvmStatic
    @Suppress("UnstableApiUsage")
    fun removeTagForBlockPos(level: ServerLevel, pos: BlockPos)  {
        level.getAttachedOrCreate(LEVEL_DATA_TYPE).mutandisCacheMap.remove(pos)
    }

    @JvmStatic
    @Suppress("UnstableApiUsage")
    fun updateTimeForTagBlockPos(level: ServerLevel, pos: BlockPos)  {
        val data = level.getAttachedOrCreate(LEVEL_DATA_TYPE).mutandisCacheMap[pos]
        if (data != null) {
            level.getAttachedOrCreate(LEVEL_DATA_TYPE).mutandisCacheMap[pos] = MutandisData(data.tag, data.time - 1)
        }
    }

    @JvmStatic
    @Suppress("UnstableApiUsage")
    fun resetTimeForTagBlockPos(level: ServerLevel, pos: BlockPos) {
        val data = level.getAttachedOrCreate(LEVEL_DATA_TYPE).mutandisCacheMap[pos]
        if (data != null) {
            level.getAttachedOrCreate(LEVEL_DATA_TYPE).mutandisCacheMap[pos] = MutandisData(data.tag, CACHE_LIFETIME)
        }
    }

    @JvmStatic
    @Suppress("UnstableApiUsage")
    val LEVEL_DATA_TYPE: AttachmentType<MutandisLevelAttachment> =
        AttachmentRegistry.builder<MutandisLevelAttachment>()
            .persistent(MutandisLevelDataAttachmentPlatform.CODEC)
            .initializer { MutandisLevelAttachment() }
            .buildAndRegister(MutandisLevelDataAttachmentPlatform.ID)
}