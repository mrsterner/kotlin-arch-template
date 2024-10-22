package dev.sterner.witchery.client.renderer

import com.mojang.blaze3d.vertex.PoseStack
import dev.sterner.witchery.Witchery
import dev.sterner.witchery.client.model.ImpEntityModel
import dev.sterner.witchery.client.model.OwlEntityModel
import dev.sterner.witchery.entity.ImpEntity
import dev.sterner.witchery.entity.OwlEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.resources.ResourceLocation

class OwlEntityRenderer(context: EntityRendererProvider.Context) :
    MobRenderer<OwlEntity, OwlEntityModel>(
        context,
        OwlEntityModel(context.bakeLayer(OwlEntityModel.LAYER_LOCATION)),
        0.1f
    ) {

    override fun getTextureLocation(entity: OwlEntity): ResourceLocation {
        return Witchery.id("textures/entity/owl/owl_little_0.png")
    }
}