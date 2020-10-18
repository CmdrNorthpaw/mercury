package uk.cmdrnorthpaw.mercury.commands.handlers

import org.spongepowered.api.Sponge
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.CommandSource
import org.spongepowered.api.command.args.CommandContext
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.format.TextColors
import uk.cmdrnorthpaw.mercury.MercurySponge
import java.io.File
import java.io.FileNotFoundException

fun remove(src: CommandSource, args: CommandContext): CommandResult {
    val pluginId = args.getOne<String>("plugin")
    if (!pluginId.isPresent) {
        src.sendMessage(Text.builder("ERROR! You need to specify a plugin id!").color(TextColors.RED).build())
        return CommandResult.empty()
    }

    return try {
        File(MercurySponge.config.pluginPath + "/${pluginId.get()}").delete()
        CommandResult.success()
    } catch (error: FileNotFoundException) {
        src.sendMessage(Text.of("This plugin does not exist! Please note that the plugin needs to be installed by mercury to be managed by it."))
        CommandResult.empty()
    }

}