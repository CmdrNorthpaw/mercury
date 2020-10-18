package uk.cmdrnorthpaw.mercury

import ninja.leaping.configurate.ConfigurationNode
import ninja.leaping.configurate.objectmapping.ObjectMapper
import ninja.leaping.configurate.objectmapping.ObjectMappingException
import ninja.leaping.configurate.objectmapping.Setting
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable
import org.spongepowered.api.Sponge
import kotlin.jvm.Throws

@ConfigSerializable
class Config {
    @Setting(comment = "Sets the path where plugins should be installed. Defaults to the default mod")
    val pluginPath = Sponge.getGame().gameDirectory.toString() + "/mods"

    companion object {
        private val mapper = ObjectMapper.forClass(Config::class.java)

        @Throws(ObjectMappingException::class)
        fun loadFrom(node: ConfigurationNode): Config = mapper.bindToNew().populate(node)
    }
}