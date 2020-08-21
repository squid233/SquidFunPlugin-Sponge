package io.github.squid233.squidfunplugin.api;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.event.CauseStackManager;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.event.cause.entity.spawn.SpawnType;
import org.spongepowered.api.event.cause.entity.spawn.SpawnTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

/**
 * @author squid233
 */
public class EntityApi {
    public static void spawnEntity(Location<World> spawnLocation, EntityType entityType, SpawnType spawnType) {
        // We need to push a new cause StackFrame to the stack so we can add our own causes
        // In previous versions of the API you had to submit a Cause parameter
        // that would often not contain the real root cause
        // By default the current plugin's PluginContainer is already pushed to the stack.
        try (CauseStackManager.StackFrame frame = Sponge.getCauseStackManager().pushCauseFrame()) {
            frame.addContext(EventContextKeys.SPAWN_TYPE, spawnType);
            spawnLocation.getExtent().spawnEntity(spawnLocation.getExtent().createEntity(entityType, spawnLocation.getPosition()));
        }
    }

    public static void spawnItem(ItemStack itemStack, Location<World> spawnLocation) {
        Entity item = spawnLocation.getExtent().createEntity(EntityTypes.ITEM, spawnLocation.getPosition());
        item.offer(Keys.REPRESENTED_ITEM, itemStack.createSnapshot());

        try (CauseStackManager.StackFrame frame = Sponge.getCauseStackManager().pushCauseFrame()) {
            frame.addContext(EventContextKeys.SPAWN_TYPE, SpawnTypes.PLACEMENT);
            spawnLocation.getExtent().spawnEntity(item);
        }
    }
}
