package io.github.squid233.squidfunplugin;

import com.google.inject.Inject;
import io.github.squid233.squidfunplugin.metadata.Metadata;
import io.github.squid233.squidfunplugin.proxy.InitEventProxy;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author squid233
 */
@Plugin(id = Metadata.ID, name = Metadata.NAME, version = Metadata.VERSION, description = Metadata.DESCRIPTION, url = Metadata.URL, authors = {"squid233"})
public class SquidFunPlugin implements PluginContainer {

    @Inject
    private Logger logger;

    public static PluginContainer plugin;

    /**
     * At the same time, modify the annotation!!!
     */
    public static final String[] AUTHORS = {"squid233"};

    private static InitEventProxy proxy;

    public SquidFunPlugin() {
        plugin = this;
        proxy = new InitEventProxy();
    }

    @Listener
    public void onPreInit(GamePreInitializationEvent e) {
        proxy.onPreInit(e);
    }

    @Listener
    public void onInit(GameInitializationEvent e) {
        proxy.onInit(e);
    }

    @Listener
    public void onPostInit(GamePostInitializationEvent e) {
        proxy.onPostInit(e);
    }

    @Listener
    public void onServerStart(GameStartedServerEvent e) {
        logger.info("Successfully running " + Metadata.NAME + "!!!");
    }

    @Override
    public @NotNull String getId() {
        return Metadata.ID;
    }

    @Override
    public @NotNull String getName() {
        return Metadata.NAME;
    }

    @Override
    public @NotNull Optional<String> getVersion() {
        return Optional.of(Metadata.VERSION);
    }

    @Override
    public @NotNull Optional<String> getDescription() {
        return Optional.of(Metadata.DESCRIPTION);
    }

    @Override
    public @NotNull Optional<String> getUrl() {
        return Optional.of(Metadata.URL);
    }

    @Override
    public @NotNull List<String> getAuthors() {
        return Arrays.asList(AUTHORS);
    }

    @Override
    public @NotNull Optional<?> getInstance() {
        return Optional.of(plugin);
    }
}
