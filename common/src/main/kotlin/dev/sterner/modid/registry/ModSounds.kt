package dev.sterner.modid.registry

import dev.architectury.registry.registries.DeferredRegister
import dev.sterner.modid.ModMain
import net.minecraft.core.registries.Registries
import net.minecraft.sounds.SoundEvent

object ModSounds {

    val SOUNDS: DeferredRegister<SoundEvent> = DeferredRegister.create(ModMain.MODID, Registries.SOUND_EVENT)

}