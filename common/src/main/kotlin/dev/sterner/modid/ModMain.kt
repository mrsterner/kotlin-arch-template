package dev.sterner.modid

import com.mojang.logging.LogUtils
import dev.sterner.modid.registry.*
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.resources.ResourceLocation
import org.slf4j.Logger

object ModMain {

    const val MODID: String = "modid"

    val LOGGER: Logger = LogUtils.getLogger()

    @JvmStatic
    fun init() {
        ModBlocks.BLOCKS.register()
        ModBlockEntityTypes.BLOCK_ENTITY_TYPES.register()
        ModItems.ITEMS.register()
        ModEntityTypes.ENTITY_TYPES.register()
        ModSounds.SOUNDS.register()
        ModCreativeModeTabs.TABS.register()
        ModParticleTypes.PARTICLES.register()
        ModRecipeTypes.RECIPE_TYPES.register()
        ModRecipeSerializers.RECIPE_SERIALIZERS.register()
        ModMenuTypes.MENU_TYPES.register()
        ModDataComponents.DATA.register()
        ModCommands.COMMAND_ARGUMENTS.register()
        ModFeatures.FEATURES.register()
        ModAttributes.MANAGER.get()

        ModPayloads.register()
    }

    @JvmStatic
    @Environment(EnvType.CLIENT)
    fun initClient() {

    }

    fun id(name: String): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(MODID, name)
    }
}
