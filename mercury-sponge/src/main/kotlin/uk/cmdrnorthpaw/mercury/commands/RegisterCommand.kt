package uk.cmdrnorthpaw.mercury.commands

import org.spongepowered.api.Sponge
import org.spongepowered.api.command.args.GenericArguments
import org.spongepowered.api.command.spec.CommandSpec
import org.spongepowered.api.text.Text
import uk.cmdrnorthpaw.mercury.MercurySponge

fun registerCommands() {
    val commandManager = Sponge.getCommandManager()

    val installCommand = CommandSpec.builder()
            .permission("mercury.install")
            .description(Text.of("Installs plugins from Ore"))
            .executor(InstallCommand())
            .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("pluginId"))))
            .build()

    val removeCommand = CommandSpec.builder()
            .permission("mercury.remove")
            .description(Text.of("Removes plugins that Mercury has installed"))
            .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("pluginId"))))
            .executor(RemoveCommand())
            .build()

    val rootCommand = CommandSpec.builder()
            .child(installCommand, "install")
            .child(removeCommand, "remove")
            .description(Text.of("Mercury's base command"))
            .extendedDescription(Text.of("Use to install, remove and upgrade plugins"))
            .build()

    commandManager.register(MercurySponge, rootCommand, "mercury")
}