package dev.sterner.modid.registry

import dev.architectury.registry.registries.DeferredRegister
import dev.sterner.modid.ModMain
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.registries.Registries


object ModParticleTypes {

    var PARTICLES: DeferredRegister<ParticleType<*>> = DeferredRegister.create(ModMain.MODID, Registries.PARTICLE_TYPE)

}