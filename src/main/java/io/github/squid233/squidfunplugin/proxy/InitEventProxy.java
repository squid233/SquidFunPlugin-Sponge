package io.github.squid233.squidfunplugin.proxy;

import io.github.squid233.squidfunplugin.SquidFunPlugin;
import io.github.squid233.squidfunplugin.command.SquidFunPluginCommand;
import io.github.squid233.squidfunplugin.event.listener.BlockRightClickEventListener;
import io.github.squid233.squidfunplugin.event.listener.GameReloadEventListener;
import io.github.squid233.squidfunplugin.event.listener.ItemUseEventListener;
import io.github.squid233.squidfunplugin.lang.Language;
import io.github.squid233.squidfunplugin.registry.CommandRegistry;
import io.github.squid233.squidfunplugin.registry.EventRegistry;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStateEvent;

/**
 * @author squid233
 */
public class InitEventProxy {

    public void onPreInit(GamePreInitializationEvent e) {
        state(e);
    }

    public void onInit(GameInitializationEvent e) {
        state(e);
        CommandRegistry.registers(this,
                new String[][]
                        {
                                {"squidfunplugin", "sfp"}
                        },
                SquidFunPluginCommand.SQUID_FUN_PLUGIN_COMMAND);
        EventRegistry.registers(new BlockRightClickEventListener(), new ItemUseEventListener(), new GameReloadEventListener());
        // Registry more event...
    }

    public void onPostInit(GamePostInitializationEvent e) {
        state(e);
    }

    public void state(GameStateEvent e) {
        SquidFunPlugin.plugin.getLogger().info(Language.get("msg.current_state", "Current State: ") + e.getState());
    }
}
