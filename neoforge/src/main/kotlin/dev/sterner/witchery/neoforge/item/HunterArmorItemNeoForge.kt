package dev.sterner.witchery.neoforge.item

import dev.sterner.witchery.Witchery
import dev.sterner.witchery.client.model.HunterArmorModel
import dev.sterner.witchery.client.model.WitchesRobesModel
import dev.sterner.witchery.registry.WitcheryItems
import net.minecraft.client.Minecraft
import net.minecraft.client.model.HumanoidModel
import net.minecraft.core.Holder
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.*
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions
import org.jetbrains.annotations.NotNull
import java.awt.Color
import javax.annotation.Nullable


open class HunterArmorItemNeoForge(material: Holder<ArmorMaterial>, type: Type, properties: Item.Properties) :
    ArmorItem(material, type, properties) {
    override fun isRepairable(arg: ItemStack): Boolean {
        return false
    }


    @Nullable
    override fun getArmorTexture(
        stack: ItemStack,
        entity: Entity,
        slot: EquipmentSlot,
        layer: ArmorMaterial.Layer,
        innerModel: Boolean
    ): ResourceLocation? {
        return if (layer.dyeable()) {
            ResourceLocation.fromNamespaceAndPath(
                Witchery.MODID,
                "textures/models/armor/witch_hunter_armor.png"
            )
        } else {
            ResourceLocation.fromNamespaceAndPath(
                Witchery.MODID,
                "textures/models/armor/witch_hunter_armor_overlay.png"
            )
        }
    }

    class ArmorRender : IClientItemExtensions {
        @NotNull
        override fun getHumanoidArmorModel(
            living: LivingEntity,
            stack: ItemStack,
            slot: EquipmentSlot,
            model: HumanoidModel<*>?
        ): HumanoidModel<*> {
            val models = Minecraft.getInstance().entityModels
            val root = models.bakeLayer(HunterArmorModel.LAYER_LOCATION)
            val armor = HunterArmorModel(root)
            armor.setAllVisible(false)


            armor.head.visible = slot == EquipmentSlot.HEAD
            armor.body.visible = slot == EquipmentSlot.CHEST
            armor.leftArm.visible = slot == EquipmentSlot.CHEST
            armor.rightArm.visible = slot == EquipmentSlot.CHEST
            armor.leftLeg.visible = slot == EquipmentSlot.FEET
            armor.rightLeg.visible = slot == EquipmentSlot.FEET

            return armor
        }

        override fun getArmorLayerTintColor(
            stack: ItemStack,
            entity: LivingEntity,
            layer: ArmorMaterial.Layer,
            layerIdx: Int,
            fallbackColor: Int
        ): Int {
            if (layer.dyeable()) {
                return DyeColor.BLACK.textureDiffuseColor
            }
            return -0x1
        }



        companion object {
            val INSTANCE: ArmorRender = ArmorRender()
        }
    }
}