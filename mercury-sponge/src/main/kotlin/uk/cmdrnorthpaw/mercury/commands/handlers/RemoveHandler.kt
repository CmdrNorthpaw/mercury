package uk.cmdrnorthpaw.mercury.commands.handlers

import org.spongepowered.api.Sponge
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.CommandSource
import org.spongepowered.api.command.args.CommandContext
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.format.TextColors
import java.io.File

fun remove(src: CommandSource, args: CommandContext): CommandResult {
    val pluginId = args.getOne<String>("plugin")
    if (!pluginId.isPresent) {
        src.sendMessage(Text.builder("ERROR! You need to specify a plugin id!").color(TextColors.RED).build())
        return CommandResult.empty()
    }

    File(Sponge.getGame().gameDirectory.toFile(), "mods/${pluginId.get()}").delete()
    return CommandResult.success()
}