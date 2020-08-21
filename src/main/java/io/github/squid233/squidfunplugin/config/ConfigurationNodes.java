package io.github.squid233.squidfunplugin.config;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;

/**
 * @author squid233
 */
public class ConfigurationNodes {
    public final ConfigurationNode langNode;

    private static ConfigurationNodes instance;

    private ConfigurationNodes(CommentedConfigurationNode root) {
        langNode = root.getNode("lang");
    }

    public static ConfigurationNodes getInstance(CommentedConfigurationNode root) {
        if (instance == null) {
            instance = new ConfigurationNodes(root);
        }
        return instance;
    }

    public static ConfigurationNodes getInstance() {
        return getInstance(Config.getInstance().getRoot());
    }
}
