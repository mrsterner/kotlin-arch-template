package dev.sterner.modid.registry

import dev.architectury.registry.registries.DeferredRegister
import dev.sterner.modid.ModMain
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeType


object ModRecipeTypes {

    val RECIPE_TYPES: DeferredRegister<RecipeType<*>> =
        DeferredRegister.create(ModMain.MODID, Registries.RECIPE_TYPE)


    private fun <T : Recipe<*>> registerRecipeType(identifier: String): RecipeType<T> {
        return object : RecipeType<T> {
            override fun toString(): String {
                return ModMain.MODID + ":" + identifier
            }
        }
    }
}