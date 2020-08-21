package io.github.squid233.squidfunplugin.event.listener;

import io.github.squid233.squidfunplugin.lang.Language;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;

/**
 * @author squid233
 */
public class GameReloadEventListener {
    @Listener
    public void onReload(GameReloadEvent e) {
        Language.loadLangFile();
    }
}
