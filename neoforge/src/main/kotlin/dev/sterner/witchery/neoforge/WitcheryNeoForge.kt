package dev.sterner.witchery.neoforge

import dev.sterner.witchery.Witchery
import dev.sterner.witchery.client.model.*
import dev.sterner.witchery.client.particle.ColorBubbleParticle
import dev.sterner.witchery.client.particle.ZzzParticle
import dev.sterner.witchery.client.renderer.*
import dev.sterner.witchery.client.screen.AltarScreen
import dev.sterner.witchery.client.screen.DistilleryScreen
import dev.sterner.witchery.client.screen.OvenScreen
import dev.sterner.witchery.client.screen.SpinningWheelScreen
import dev.sterner.witchery.entity.BroomEntity
import dev.sterner.witchery.neoforge.client.BroomBlockEntityWithoutLevelRenderer
import dev.sterner.witchery.neoforge.client.DreamWeaverBlockEntityWithoutLevelRenderer
import dev.sterner.witchery.neoforge.client.SpinningWheelBlockEntityWithoutLevelRenderer
import dev.sterner.witchery.neoforge.client.WitcheryBlockEntityWithoutLevelRendererInstance
import dev.sterner.witchery.neoforge.item.HunterArmorItemNeoForge
import dev.sterner.witchery.neoforge.item.WitchesRobesItemNeoForge
import dev.sterner.witchery.registry.*
import dev.sterner.witchery.registry.WitcheryItems.BABA_YAGAS_HAT
import dev.sterner.witchery.registry.WitcheryItems.HUNTER_BOOTS
import dev.sterner.witchery.registry.WitcheryItems.HUNTER_CHESTPLATE
import dev.sterner.witchery.registry.WitcheryItems.HUNTER_HELMET
import dev.sterner.witchery.registry.WitcheryItems.HUNTER_LEGGINGS
import dev.sterner.witchery.registry.WitcheryItems.WITCHES_HAT
import dev.sterner.witchery.registry.WitcheryItems.WITCHES_ROBES
import dev.sterner.witchery.registry.WitcheryItems.WITCHES_SLIPPERS
import net.minecraft.client.Minecraft
import net.minecraft.client.model.BoatModel
import net.minecraft.client.renderer.entity.BoatRenderer
import net.minecraft.client.renderer.entity.ThrownItemRenderer
import net.minecraft.core.HolderLookup
import net.minecraft.core.NonNullList
import net.minecraft.core.RegistryAccess
import net.minecraft.network.syncher.EntityDataSerializer
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent
import net.neoforged.neoforge.event.LootTableLoadEvent
import net.neoforged.neoforge.event.entity.EntityMountEvent
import net.neoforged.neoforge.registries.DataPackRegistryEvent
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NeoForgeRegistries
import net.neoforged.neoforge.server.ServerLifecycleHooks
import org.jetbrains.annotations.NotNull
import thedarkcolour.kotlinforforge.neoforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.runForDist


@Mod(Witchery.MODID)
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
object WitcheryNeoForge {


    val DATA_SERIALIZER_REGISTER: DeferredRegister<EntityDataSerializer<*>> =
        DeferredRegister.create(NeoForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, Witchery.MODID)


    private inline fun <reified T : EntityDataSerializer<*>> DeferredRegister<EntityDataSerializer<*>>.registerSerializer(name: String, noinline supplier: () -> T): DeferredHolder<EntityDataSerializer<*>, T> {
        return register(name, supplier)
    }

    val INVENTORY_SERIALIZER: DeferredHolder<EntityDataSerializer<*>, EntityDataSerializer<NonNullList<ItemStack>>> =
        DATA_SERIALIZER_REGISTER.registerSerializer("inventory") { WitcheryEntityDataSerializers.INVENTORY }


    init {
        WitcheryNeoForgeAttachmentRegistry.ATTACHMENT_TYPES.register(MOD_BUS)
        Witchery.init()

        DATA_SERIALIZER_REGISTER.register(MOD_BUS)

        runForDist(
            clientTarget = {
                MOD_BUS.addListener(::onClientSetup)
                Minecraft.getInstance()
            },
            serverTarget = {
                MOD_BUS.addListener(::onServerSetup)
                MOD_BUS.addListener(::onLoadComplete)
                FORGE_BUS.addListener(::dismountBroom)
                "test"
            }
        )
    }

    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {

    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        Witchery.initClient()
    }

    private fun onLoadComplete(event: FMLLoadCompleteEvent) {
        WitcheryFlammability.register()
    }

    @SubscribeEvent
    private fun registerScreens(event: RegisterMenuScreensEvent) {
        event.register(WitcheryMenuTypes.OVEN_MENU_TYPE.get(), ::OvenScreen)
        event.register(WitcheryMenuTypes.ALTAR_MENU_TYPE.get(), ::AltarScreen)
        event.register(WitcheryMenuTypes.DISTILLERY_MENU_TYPE.get(), ::DistilleryScreen)
        event.register(WitcheryMenuTypes.SPINNING_WHEEL_MENU_TYPE.get(), ::SpinningWheelScreen)
    }

    @SubscribeEvent
    fun createDataPackRegistries(event: DataPackRegistryEvent.NewRegistry) {
        event.dataPackRegistry(WitcheryRitualRegistry.RITUAL_KEY, WitcheryRitualRegistry.CODEC, WitcheryRitualRegistry.CODEC)
    }

    @SubscribeEvent
    fun onEntityRendererRegistry(event: EntityRenderersEvent.RegisterRenderers) {
        event.registerEntityRenderer(WitcheryEntityTypes.CUSTOM_BOAT.get()) { context -> BoatRenderer(context, false) }
        event.registerEntityRenderer(WitcheryEntityTypes.CUSTOM_CHEST_BOAT.get()) { context -> BoatRenderer(context, true) }

        event.registerEntityRenderer(
            WitcheryEntityTypes.BROOM.get(),
            ::BroomEntityRenderer
        )
        event.registerEntityRenderer(
            WitcheryEntityTypes.IMP.get(),
            ::ImpEntityRenderer
        )
        event.registerEntityRenderer(
            WitcheryEntityTypes.MANDRAKE.get(),
            ::MandrakeEntityRenderer
        )
        event.registerEntityRenderer(
            WitcheryEntityTypes.OWL.get(),
            ::OwlEntityRenderer
        )
        event.registerEntityRenderer(
            WitcheryEntityTypes.FLOATING_ITEM.get(),
            ::FloatingItemEntityRenderer
        )
        event.registerEntityRenderer(
            WitcheryEntityTypes.THROWN_BREW.get(),
            ::ThrownItemRenderer
        )
        event.registerEntityRenderer(
            WitcheryEntityTypes.DEMON.get(),
            ::DemonEntityRenderer
        )
        event.registerEntityRenderer(
            WitcheryEntityTypes.ENT.get(),
            ::EntEntityRenderer
        )
        event.registerEntityRenderer(
            WitcheryEntityTypes.SLEEPING_PLAYER.get(),
            ::SleepingPlayerEntityRenderer
        )
    }


    @SubscribeEvent
    private fun registerLayerDefinitions(event: EntityRenderersEvent.RegisterLayerDefinitions) {
        event.registerLayerDefinition(BoatModels.ROWAN_BOAT_LAYER, BoatModel::createBodyModel)
        event.registerLayerDefinition(BoatModels.ALDER_BOAT_LAYER, BoatModel::createBodyModel)
        event.registerLayerDefinition(BoatModels.HAWTHORN_BOAT_LAYER, BoatModel::createBodyModel)
        event.registerLayerDefinition(BoatModels.ROWAN_CHEST_BOAT_LAYER, BoatModel::createBodyModel)
        event.registerLayerDefinition(BoatModels.ALDER_CHEST_BOAT_LAYER, BoatModel::createBodyModel)
        event.registerLayerDefinition(BoatModels.HAWTHORN_CHEST_BOAT_LAYER, BoatModel::createBodyModel)

        event.registerLayerDefinition(
            AltarBlockEntityModel.LAYER_LOCATION,
            AltarBlockEntityModel::createBodyLayer)
        event.registerLayerDefinition(
            AltarClothBlockEntityModel.LAYER_LOCATION,
            AltarClothBlockEntityModel::createBodyLayer)
        event.registerLayerDefinition(
            JarModel.LAYER_LOCATION,
            JarModel::createBodyLayer)
        event.registerLayerDefinition(
            PoppetModel.LAYER_LOCATION,
            PoppetModel::createBodyLayer)
        event.registerLayerDefinition(
            WitchesRobesModel.LAYER_LOCATION,
            WitchesRobesModel::createBodyLayer)
        event.registerLayerDefinition(
            SpinningWheelWheelBlockEntityModel.LAYER_LOCATION,
            SpinningWheelWheelBlockEntityModel::createBodyLayer)
        event.registerLayerDefinition(
            SpinningWheelBlockEntityModel.LAYER_LOCATION,
            SpinningWheelBlockEntityModel::createBodyLayer)
        event.registerLayerDefinition(
            DistilleryGemModel.LAYER_LOCATION,
            DistilleryGemModel::createBodyLayer)

        event.registerLayerDefinition(
            MandrakeEntityModel.LAYER_LOCATION) { MandrakeEntityModel.createBodyLayer() }


        event.registerLayerDefinition(
            ImpEntityModel.LAYER_LOCATION,
            ImpEntityModel::createBodyLayer)
        event.registerLayerDefinition(
            OwlEntityModel.LAYER_LOCATION,
            OwlEntityModel::createBodyLayer)
        event.registerLayerDefinition(
            BroomEntityModel.LAYER_LOCATION,
            BroomEntityModel::createBodyLayer)
        event.registerLayerDefinition(
            HunterArmorModel.LAYER_LOCATION,
            HunterArmorModel::createBodyLayer)
        event.registerLayerDefinition(
            DreamWeaverBlockEntityModel.LAYER_LOCATION,
            DreamWeaverBlockEntityModel::createBodyLayer)
        event.registerLayerDefinition(
            DemonEntityModel.LAYER_LOCATION,
            DemonEntityModel::createBodyLayer)
        event.registerLayerDefinition(
            EntEntityModel.LAYER_LOCATION,
            EntEntityModel::createBodyLayer)
    }

    @SubscribeEvent
    fun registerParticle(event: RegisterParticleProvidersEvent){
        event.registerSpriteSet(WitcheryParticleTypes.COLOR_BUBBLE.get()){ o ->
            ColorBubbleParticle.Provider(o)
        }
        event.registerSpriteSet(WitcheryParticleTypes.ZZZ.get()){ o ->
            ZzzParticle.Provider(o)
        }
    }

    @SubscribeEvent
    fun registerClientExtensions(event: RegisterClientExtensionsEvent) {
        event.registerItem(WitchesRobesItemNeoForge.ArmorRender.INSTANCE, WITCHES_ROBES.get(), WITCHES_HAT.get(), WITCHES_SLIPPERS.get(), BABA_YAGAS_HAT.get())
        event.registerItem(HunterArmorItemNeoForge.ArmorRender.INSTANCE, HUNTER_HELMET.get(), HUNTER_CHESTPLATE.get(), HUNTER_LEGGINGS.get(), HUNTER_BOOTS.get())

        event.registerItem(WitcheryBlockEntityWithoutLevelRendererInstance(SpinningWheelBlockEntityWithoutLevelRenderer()), WitcheryItems.SPINNING_WHEEL.get())
        event.registerItem(WitcheryBlockEntityWithoutLevelRendererInstance(BroomBlockEntityWithoutLevelRenderer()), WitcheryItems.BROOM.get())
        event.registerItem(WitcheryBlockEntityWithoutLevelRendererInstance(DreamWeaverBlockEntityWithoutLevelRenderer()), WitcheryItems.DREAM_WEAVER_OF_FLEET_FOOT.get())
        event.registerItem(WitcheryBlockEntityWithoutLevelRendererInstance(DreamWeaverBlockEntityWithoutLevelRenderer()), WitcheryItems.DREAM_WEAVER_OF_FASTING.get())
        event.registerItem(WitcheryBlockEntityWithoutLevelRendererInstance(DreamWeaverBlockEntityWithoutLevelRenderer()), WitcheryItems.DREAM_WEAVER_OF_INTENSITY.get())
        event.registerItem(WitcheryBlockEntityWithoutLevelRendererInstance(DreamWeaverBlockEntityWithoutLevelRenderer()), WitcheryItems.DREAM_WEAVER_OF_NIGHTMARES.get())
        event.registerItem(WitcheryBlockEntityWithoutLevelRendererInstance(DreamWeaverBlockEntityWithoutLevelRenderer()), WitcheryItems.DREAM_WEAVER_OF_IRON_ARM.get())
        event.registerItem(WitcheryBlockEntityWithoutLevelRendererInstance(DreamWeaverBlockEntityWithoutLevelRenderer()), WitcheryItems.DREAM_WEAVER.get())

        @Suppress("UNRESOLVED_REFERENCE")
        WitcheryFluids.FLUIDS_INFOS.forEach { attributes ->
            event.registerFluidType(object : IClientFluidTypeExtensions {
                @NotNull
                override fun getStillTexture(): ResourceLocation {
                    return attributes.sourceTexture
                }

                @NotNull
                override fun getFlowingTexture(): ResourceLocation {
                    return attributes.flowingTexture
                }
            }, attributes.flowingFluid.getFluidType())
        }
    }

    @SubscribeEvent
    fun modifyExistingTabs(event: BuildCreativeModeTabContentsEvent) {
        WitcheryCreativeModeTabs.modifyExistingTabs(event.tab, event)
    }

    fun dismountBroom(event: EntityMountEvent) {
        if (event.entityBeingMounted is BroomEntity) {
            event.isCanceled = true
        }
    }
}
