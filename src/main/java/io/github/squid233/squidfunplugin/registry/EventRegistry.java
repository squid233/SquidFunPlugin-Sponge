package io.github.squid233.squidfunplugin.registry;

import io.github.squid233.squidfunplugin.SquidFunPlugin;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;

/**
 * @author squid233
 */
public class EventRegistry {
    /**
     * Register listeners contain an object
     *
     * @param listeners An object include {@link Listener}
     */
    public static void register(Object listeners) {
        Sponge.getEventManager().registerListeners(SquidFunPlugin.plugin, listeners);
    }

    public static void registers(Object... listeners) {
        for (Object listener : listeners) {
            register(listener);
        }
    }
}
