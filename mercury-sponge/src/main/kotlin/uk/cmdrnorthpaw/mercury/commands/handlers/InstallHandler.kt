package uk.cmdrnorthpaw.mercury.commands.handlers

import apis.ore.OreResource
import kotlinx.coroutines.runBlocking
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.CommandSource
import org.spongepowered.api.command.args.CommandContext
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.format.TextColor
import org.spongepowered.api.text.format.TextColors

fun install(src: CommandSource, args: CommandContext): CommandResult {
    val pluginId = args.getOne<String>("plugin")
    if (!pluginId.isPresent) {
        src.sendMessage(Text.builder("ERROR! You need to specify a plugin id!").color(TextColors.RED).build())
        return CommandResult.empty()
    }
    val plugin = runBlocking { OreResource.get(pluginId.get()) }
    val version = args.getOne<Float>("version")

    runBlocking { if (version.isPresent) plugin?.download() else plugin?.download(version.get()) }
    return CommandResult.success()
}