package io.github.squid233.squidfunplugin.event.listener;

import io.github.squid233.squidfunplugin.api.EntityApi;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.cause.entity.spawn.SpawnTypes;

import java.util.Random;

/**
 * @author squid233
 */
public class BlockRightClickEventListener {
    @Listener
    public void onRightClickBlock(InteractBlockEvent.Secondary.MainHand e) {
        if (e.getTargetBlock().equals(BlockSnapshot.builder().blockState(BlockState.builder().blockType(BlockTypes.SPONGE).build()).build())) {
            if (e.getSource() instanceof Player) {
                for (int i = new Random().nextInt(6); i < 5; i++) {
                    EntityApi.spawnEntity(((Player) e.getSource()).getLocation(), EntityTypes.SQUID, SpawnTypes.PLUGIN);
                }
            }
        }
    }
}
