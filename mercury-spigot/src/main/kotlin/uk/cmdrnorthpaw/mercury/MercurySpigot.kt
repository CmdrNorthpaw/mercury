package uk.cmdrnorthpaw.mercury;

import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin;

class MercurySpigot : JavaPlugin() {
    override fun onEnable() {

    }

    override fun onDisable() {

    }
    companion object {
        val plugin: Plugin
        get() = getPlugin(MercurySpigot::class.java)
    }
}
