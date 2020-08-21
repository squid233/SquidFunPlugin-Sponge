package io.github.squid233.squidfunplugin.config;

import io.github.squid233.squidfunplugin.SquidFunPlugin;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.SpongeEventFactory;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.event.cause.EventContextKeys;

import java.io.IOException;

/**
 * @author squid233
 */
public class ConfigModifier {
    public static void setValue(ConfigurationNode node, @Nullable Object value) {
        node.setValue(value);
    }

    public static void setAndSave(@NonNull ConfigurationNode node,
                                  @Nullable Object value,
                                  ConfigurationLoader<CommentedConfigurationNode> loader,
                                  CommentedConfigurationNode root) {
        setValue(node, value);
        try {
            loader.save(root);
        } catch (IOException e) {
            System.err.println("Unable to save your messages configuration! Sorry! " + e.getMessage());
        }
    }

    public static void reload() {
        Sponge.getEventManager().post(SpongeEventFactory.createGameReloadEvent(
                Cause.of(EventContext.builder().add(EventContextKeys.PLUGIN, SquidFunPlugin.plugin).build(), SquidFunPlugin.plugin)));
    }

    public static void setAndSaveThenReload(@NonNull ConfigurationNode node,
                                            @Nullable Object value,
                                            ConfigurationLoader<CommentedConfigurationNode> loader,
                                            CommentedConfigurationNode root) {
        setAndSave(node, value, loader, root);
        reload();
    }
}
