package dev.sterner.modid.registry

import dev.architectury.registry.registries.DeferredRegister
import dev.sterner.modid.ModMain
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.crafting.RecipeSerializer


object ModRecipeSerializers {

    val RECIPE_SERIALIZERS: DeferredRegister<RecipeSerializer<*>> =
        DeferredRegister.create(ModMain.MODID, Registries.RECIPE_SERIALIZER)

}