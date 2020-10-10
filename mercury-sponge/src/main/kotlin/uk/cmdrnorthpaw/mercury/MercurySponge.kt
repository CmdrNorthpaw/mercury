package uk.cmdrnorthpaw.mercury;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;

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
}

