package uk.cmdrnorthpaw.mercury

import ninja.leaping.configurate.objectmapping.Setting
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable
import org.spongepowered.api.Sponge

@ConfigSerializable
class Config {
    @Setting(comment = "Sets the path where plugins should be installed. Defaults to the default mod")
    val pluginPath = Sponge.getGame().gameDirectory.toString() + "/mods"

}