package uk.cmdrnorthpaw.mercury.commands

import apis.NotFoundException
import apis.ore.OreResource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.CommandSource
import org.spongepowered.api.command.args.CommandContext
import org.spongepowered.api.command.spec.CommandExecutor
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.format.TextColors
import uk.cmdrnorthpaw.mercury.MercurySponge
import java.io.File

class InstallCommand: CommandExecutor {
    override fun execute(src: CommandSource, args: CommandContext): CommandResult {
        val pluginId = args.getOne<String>("plugin")
        if (!pluginId.isPresent) {
            src.sendMessage(Text.builder("ERROR! You need to specify a plugin id!").color(TextColors.RED).build())
            return CommandResult.empty()
        }

        val plugin: OreResource?
        try {
            plugin = runBlocking { OreResource.get(pluginId.get())!! }
        } catch (error: NotFoundException) {
            src.sendMessage(Text.builder("Plugin not found on Ore").color(TextColors.RED).build())
            return CommandResult.empty()
        }

        val version = args.getOne<Float>("version")

        val targetFile = File(MercurySponge.config.pluginPath + "/${plugin?.id}")

        GlobalScope.launch {
            val localVersions = MercurySponge.versions

            if (version.isPresent) {
                plugin.download(targetFile)
                localVersions.addProperty(plugin.id, plugin.file.version)
            } else {
                plugin.download(targetFile, version.get())
                localVersions.addProperty(plugin.id, version.get())
            }
        }
        return CommandResult.success()
    }
}