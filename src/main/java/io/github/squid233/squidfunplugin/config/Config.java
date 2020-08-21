package io.github.squid233.squidfunplugin.config;

import io.github.squid233.squidfunplugin.SquidFunPlugin;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.asset.Asset;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * @author squid233
 */
public class Config {

    private CommentedConfigurationNode root;

    private final ConfigurationLoader<CommentedConfigurationNode> loader;

    private static Config instance;

    public Config() {
        Path path = Sponge.getConfigManager().getPluginConfig(SquidFunPlugin.plugin).getConfigPath();
        Optional<Asset> asset = Sponge.getAssetManager().getAsset(SquidFunPlugin.plugin, "squidfunplugin.conf");
        if (Files.notExists(path)) {
            asset.ifPresent(a -> {
                try {
                    a.copyToFile(path, false, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        Optional<Asset> asset1 = SquidFunPlugin.plugin.getAsset("squidfunplugin.conf");
        if (asset1.isPresent()) {
            try {
                root.mergeValuesFrom(HoconConfigurationLoader.builder().setURL(asset1.get().getUrl()).build().load(ConfigurationOptions.defaults()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        loader = HoconConfigurationLoader.builder().setPath(path).build();
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.println("An error occurred while loading this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
            System.exit(1);
        }
        ConfigurationNodes.getInstance(root);
    }

    public void setAndSave(@NonNull ConfigurationNode node, @Nullable Object value) {
        ConfigModifier.setAndSave(node, value, loader, root);
    }

    public void reload() {
        ConfigModifier.reload();
    }

    public void setAndSaveThenReload(@NonNull ConfigurationNode node, @Nullable Object value) {
        setAndSave(node, value);
        reload();
    }

    public String getString(@NonNull ConfigurationNode node, String path, String def) {
        return node.getNode(path).getString(def);
    }

    public CommentedConfigurationNode getRoot() {
        return root;
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }
}
