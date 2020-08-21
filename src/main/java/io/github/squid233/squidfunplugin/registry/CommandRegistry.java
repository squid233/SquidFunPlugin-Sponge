package io.github.squid233.squidfunplugin.registry;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandCallable;

/**
 * @author squid233
 */
public class CommandRegistry {
    public static void register(Object plugin, CommandCallable callable, @NotNull String... alias) {
        Sponge.getCommandManager().register(plugin, callable, alias);
    }

    public static void registers(Object plugin, @NotNull String[][] alias, CommandCallable... callables) {
        for (int i = 0; i < callables.length; i++) {
            register(plugin, callables[i], alias[i]);
        }
    }
}
