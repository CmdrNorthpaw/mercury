package uk.cmdrnorthpaw.mercury;

import com.google.inject.Inject
import ninja.leaping.configurate.commented.CommentedConfigurationNode
import ninja.leaping.configurate.hocon.HoconConfigurationLoader
import ninja.leaping.configurate.loader.ConfigurationLoader
import org.spongepowered.api.config.DefaultConfig
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GameStartedServerEvent
import org.spongepowered.api.plugin.Plugin
import java.nio.file.Path

@Plugin(
        id = "mercury-sponge",
        name = "Mercury",
        description = "Mercury is a plugin install and update management system for Minecraft servers",
        url = "https://github.com/CmdrNorthpaw/Mercury",
        authors = ["CmdrNorthpaw"]
)
object MercurySponge {

    @Listener
    fun onEnable(event: GameStartedServerEvent) {

    }

    @Inject
    @DefaultConfig(sharedRoot = true)
    private val configFile: Path? = null

    @Inject
    @DefaultConfig(sharedRoot = true)
    private val configNode: ConfigurationLoader<CommentedConfigurationNode>? = null

    val config: Config
    get() {
        val configLoader = HoconConfigurationLoader.builder()
                .setPath(configFile!!)
                .build()
        val configNode = configLoader.load()
        return Config.loadFrom(configNode)
    }
    val MOD_LOCATION = config.pluginPath

}

