package io.github.squid233.squidfunplugin.lang;

import io.github.squid233.squidfunplugin.SquidFunPlugin;
import io.github.squid233.squidfunplugin.config.Config;
import io.github.squid233.squidfunplugin.config.ConfigurationNodes;
import org.jetbrains.annotations.Contract;
import org.spongepowered.api.Sponge;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * @author squid233
 */
public class Language {
    private static final Properties properties = new Properties();
    private static String currentLang = Config.getInstance().getString(ConfigurationNodes.getInstance().langNode, "lang", "en_us");

    static {
        loadLangFile();
    }

    public static void loadLangFile() {
        SquidFunPlugin.plugin.getAsset("lang/" + currentLang + ".lang").ifPresent(langFile -> {
            try {
                langFile.copyToFile(
                        Paths.get(
                                Sponge.getConfigManager().getPluginConfig(SquidFunPlugin.plugin).getConfigPath().normalize().toString() + currentLang + ".lang"),
                        false,
                        true);
                properties.load(new FileReader(langFile.getFileName()));
            } catch (IOException e) {
                try {
                    properties.load(new FileReader("en_us.lang"));
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
            }
        });
    }

    public static String get(String key, String def) {
        return properties.getProperty(key, def);
    }

    public static String get(String translationKey) {
        return get(translationKey, "null");
    }

    @Contract(pure = true)
    public static Properties getProperties() {
        return properties;
    }

    public static void setCurrentLang(String currentLang) {
        Language.currentLang = currentLang;
    }

    public static void setCurrentLangAndReload(String currentLang) {
        setCurrentLang(currentLang);
        Config.getInstance().setAndSaveThenReload(ConfigurationNodes.getInstance().langNode, currentLang);
    }
}
