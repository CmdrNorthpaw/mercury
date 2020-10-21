package uk.cmdrnorthpaw.mercury;

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.inject.Inject
import ninja.leaping.configurate.commented.CommentedConfigurationNode
import ninja.leaping.configurate.hocon.HoconConfigurationLoader
import ninja.leaping.configurate.loader.ConfigurationLoader
import org.spongepowered.api.Sponge
import org.spongepowered.api.config.DefaultConfig
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GameStartedServerEvent
import org.spongepowered.api.plugin.Plugin
import uk.cmdrnorthpaw.mercury.commands.registerCommands
import java.io.File
import java.nio.file.Path

@Plugin(
        id = "mercury-sponge",
        name = "Mercury",
        description = "Mercury is a plugin install and update management system for Minecraft servers",
        url = "https://github.com/CmdrNorthpaw/Mercury",
        authors = ["CmdrNorthpaw"]
)
object MercurySponge {

    val dataFolder = File(Sponge.getGame().gameDirectory.toFile(), "mercury-data")
    @Listener
    fun onEnable(event: GameStartedServerEvent) {
        // Create data folder if it does not exist
        dataFolder.mkdirs()

        // Register commands (duh)
        registerCommands()
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

    private val versionsFile = File(dataFolder, "versions.json")
    var versions: JsonObject
    get() = JsonParser().parse(versionsFile.readText()).asJsonObject
    set(value) {
        versionsFile.writeText(value.asString)
    }

}

