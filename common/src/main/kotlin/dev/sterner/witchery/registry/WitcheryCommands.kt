package dev.sterner.witchery.registry

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.arguments.BoolArgumentType
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import dev.architectury.registry.registries.DeferredRegister
import dev.sterner.witchery.Witchery
import dev.sterner.witchery.commands.CurseArgumentType
import dev.sterner.witchery.commands.InfusionArgumentType
import dev.sterner.witchery.platform.CursePlayerAttachment
import dev.sterner.witchery.platform.FamiliarLevelAttachment
import dev.sterner.witchery.platform.PlayerManifestationDataAttachment
import dev.sterner.witchery.platform.infusion.InfusionData
import dev.sterner.witchery.platform.infusion.InfusionType
import dev.sterner.witchery.platform.infusion.PlayerInfusionDataAttachment
import net.minecraft.commands.CommandBuildContext
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.commands.synchronization.ArgumentTypeInfo
import net.minecraft.commands.synchronization.ArgumentTypeInfos
import net.minecraft.commands.synchronization.SingletonArgumentInfo
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.EntityType


object WitcheryCommands {

    val COMMAND_ARGUMENTS: DeferredRegister<ArgumentTypeInfo<*, *>> = DeferredRegister.create(Witchery.MODID, Registries.COMMAND_ARGUMENT_TYPE)

    val INFUSION_TYPE = COMMAND_ARGUMENTS.register("infusion_type") {
        registerByClass(InfusionArgumentType::class.java, SingletonArgumentInfo.contextFree(::InfusionArgumentType))
    }

    val CURSE_TYPE = COMMAND_ARGUMENTS.register("curse_type") {
        registerByClass(CurseArgumentType::class.java, SingletonArgumentInfo.contextFree(::CurseArgumentType))
    }

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
        dispatcher.register(
            Commands.literal("witchery")
                .then(registerInfusionCommands())
                .then(registerManifestationCommands())
                .then(registerCurseCommands())
        )
    }

    private fun registerInfusionCommands(): LiteralArgumentBuilder<CommandSourceStack> {
        return Commands.literal("infusion")
            .requires { it.hasPermission(2) }
            .then(
                Commands.literal("set")
                    .then(
                        Commands.argument("player", EntityArgument.player())
                            .then(
                                Commands.argument("infusionType", InfusionArgumentType.infusionType())
                                    .executes { ctx ->
                                        val player = EntityArgument.getPlayer(ctx, "player")
                                        val infusionType = InfusionArgumentType.getInfusionType(ctx, "infusionType")
                                        PlayerInfusionDataAttachment.setPlayerInfusion(player, InfusionData(infusionType))
                                        1
                                    }
                            )
                    )
            )
            .then(
                Commands.literal("get")
                    .then(
                        Commands.argument("player", EntityArgument.player())
                            .executes { ctx ->
                                val player = EntityArgument.getPlayer(ctx, "player")
                                val currentInfusion = PlayerInfusionDataAttachment.getPlayerInfusion(player)
                                ctx.source.sendSuccess(
                                    { Component.literal("Current infusion type: ${currentInfusion.type.serializedName} for player ${player.name.string}") },
                                    false
                                )
                                1
                            }
                    )
            )
            .then(
                Commands.literal("increase")
                    .then(
                        Commands.argument("player", EntityArgument.player())
                            .then(
                                Commands.argument("amount", IntegerArgumentType.integer(1))
                                    .executes { ctx ->
                                        val player = EntityArgument.getPlayer(ctx, "player")
                                        val amount = IntegerArgumentType.getInteger(ctx, "amount")
                                        if (PlayerInfusionDataAttachment.getPlayerInfusion(player).type != InfusionType.NONE) {
                                            PlayerInfusionDataAttachment.increaseInfusionCharge(player, amount)
                                        }
                                        1
                                    }
                            )
                    )
            )
            .then(
                Commands.literal("setAndKill")
                    .then(
                        Commands.argument("player", EntityArgument.player())
                            .then(
                                Commands.argument("infusionType", InfusionArgumentType.infusionType())
                                    .executes { ctx ->
                                        val player = EntityArgument.getPlayer(ctx, "player")
                                        val infusionType = InfusionArgumentType.getInfusionType(ctx, "infusionType")
                                        player.hurt(player.level().damageSources().magic(), 100f)
                                        if (player.health > 0) {
                                            PlayerInfusionDataAttachment.setPlayerInfusion(player, InfusionData(infusionType))
                                        }
                                        1
                                    }
                            )
                    )
            )
    }

    private fun registerManifestationCommands(): LiteralArgumentBuilder<CommandSourceStack> {
        return Commands.literal("manifestation")
            .requires { it.hasPermission(2) }
            .then(
                Commands.literal("set")
                    .then(
                        Commands.argument("player", EntityArgument.player())
                            .then(
                                Commands.argument("status", BoolArgumentType.bool())
                                    .executes { ctx ->
                                        val player = EntityArgument.getPlayer(ctx, "player")
                                        val status = BoolArgumentType.getBool(ctx, "status")
                                        PlayerManifestationDataAttachment.setHasRiteOfManifestation(player, status)
                                        1
                                    }
                            )
                    )
            )
            .then(
                Commands.literal("get")
                    .then(
                        Commands.argument("player", EntityArgument.player())
                            .executes { ctx ->
                                val player = EntityArgument.getPlayer(ctx, "player")
                                val status = PlayerManifestationDataAttachment.getData(player).hasRiteOfManifestation
                                ctx.source.sendSuccess(
                                    { Component.literal("Current manifestation status: $status for player ${player.name.string}") },
                                    false
                                )
                                1
                            }
                    )
            )
    }

    private fun registerCurseCommands(): LiteralArgumentBuilder<CommandSourceStack> {
        return Commands.literal("curse")
            .requires { it.hasPermission(2) }
            .then(
                Commands.literal("apply")
                    .then(
                        Commands.argument("player", EntityArgument.player())
                            .then(
                                Commands.argument("curseType", CurseArgumentType.curseType())
                                    .executes { ctx ->
                                        val player = EntityArgument.getPlayer(ctx, "player")
                                        val curseType = CurseArgumentType.getCurse(ctx, "curseType")
                                        val commandSender = ctx.source.player
                                        val cat = if (commandSender != null) {
                                            FamiliarLevelAttachment.getFamiliarEntityType(commandSender.uuid, commandSender.serverLevel()) == EntityType.CAT
                                        } else {
                                            false
                                        }
                                        CursePlayerAttachment.addCurse(player, WitcheryCurseRegistry.CURSES.getId(curseType)!!, cat)
                                        1
                                    }
                            )
                    )
            )
            .then(
                Commands.literal("remove")
                    .then(
                        Commands.argument("player", EntityArgument.player())
                            .then(
                                Commands.argument("curseType", CurseArgumentType.curseType())
                                    .executes { ctx ->
                                        val player = EntityArgument.getPlayer(ctx, "player")
                                        val curseType = CurseArgumentType.getCurse(ctx, "curseType")
                                        CursePlayerAttachment.removeCurse(player, curseType)
                                        1
                                    }
                            )
                    )
            )
    }
}