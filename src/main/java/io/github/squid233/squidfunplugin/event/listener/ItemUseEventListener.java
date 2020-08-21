package io.github.squid233.squidfunplugin.event.listener;

import com.flowpowered.math.vector.Vector3d;
import io.github.squid233.squidfunplugin.api.EntityApi;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.DyeColors;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.item.inventory.UseItemStackEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Random;

/**
 * @author squid233
 */
public class ItemUseEventListener {
    @Listener
    public void onUseItem(UseItemStackEvent e) {
        ItemStack itemStack = e.getItemStackInUse().createStack();
        if (itemStack.equalTo(ItemStack.of(ItemTypes.DYE))
                && itemStack.getValue(Keys.DYE_COLOR).isPresent()
                && itemStack.getValue(Keys.DYE_COLOR).get().get().equals(DyeColors.WHITE)) {
            ItemStack stack = ItemStack.builder().itemType(ItemTypes.DYE).add(Keys.DYE_COLOR, DyeColors.WHITE).build();
            stack.offer(Keys.DISPLAY_NAME, Text.of(TextColors.GOLD, TextStyles.RESET, "金坷垃"));
            stack.offer(Keys.UNBREAKABLE, true);
            if (new Random().nextInt(100) < 10) {
                if (e.getSource() instanceof Player) {
                    itemStack.setQuantity(itemStack.getQuantity() - 1);
                    Location<World> loc = ((Player) e.getSource()).getLocation();
                    loc.setPosition(new Vector3d(loc.getX(), loc.getY() + 2, loc.getZ()));
                    EntityApi.spawnItem(stack, loc);
                }
            }
        }
    }
}
