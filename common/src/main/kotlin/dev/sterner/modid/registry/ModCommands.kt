package dev.sterner.modid.registry

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.ArgumentType
import dev.architectury.registry.registries.DeferredRegister
import dev.sterner.modid.ModMain
import net.minecraft.commands.CommandBuildContext
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.commands.synchronization.ArgumentTypeInfo
import net.minecraft.commands.synchronization.ArgumentTypeInfos
import net.minecraft.core.registries.Registries


object ModCommands {

    val COMMAND_ARGUMENTS: DeferredRegister<ArgumentTypeInfo<*, *>> = DeferredRegister.create(ModMain.MODID, Registries.COMMAND_ARGUMENT_TYPE)

    private fun <A : ArgumentType<*>?, T : ArgumentTypeInfo.Template<A>?, I : ArgumentTypeInfo<A, T>?> registerByClass(
        infoClass: Class<A>?,
        argumentTypeInfo: I
    ): I {
        ArgumentTypeInfos.BY_CLASS[infoClass] = argumentTypeInfo
        return argumentTypeInfo
    }

    fun register(
        dispatcher: CommandDispatcher<CommandSourceStack>,
        context: CommandBuildContext,
        selection: Commands.CommandSelection
    ) {

    }
}