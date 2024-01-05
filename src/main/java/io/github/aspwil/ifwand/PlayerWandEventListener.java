package io.github.aspwil.ifwand;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

public class PlayerWandEventListener implements Listener {
    private final NamespacedKey isWandKey;
    private final FileConfiguration config;

    public PlayerWandEventListener(IFWand pluginReference) {
        this.isWandKey = new NamespacedKey(pluginReference, "isIFWand");
        this.config = pluginReference.getConfig();
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        // Get the player
        Player player = event.getPlayer();
        // Check if player is holding a valid Item Frame Wand
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        if (mainHandItem.hasItemMeta() && mainHandItem.getItemMeta().getPersistentDataContainer().has(isWandKey)) {
            Entity entity = event.getRightClicked();
            // Check if the entity is an Item Frame
            if (entity instanceof ItemFrame) {
                ItemFrame frame = (ItemFrame) entity;
                // Check if Item Frame is fixed and invulnerable
                if (frame.isFixed() && frame.isInvulnerable()) {
                    // Unlock Item Frame
                    frame.setInvulnerable(false);
                    frame.setFixed(false);
                    // Send message to player in the action bar
                    sendActionBarMessage(player, config.getString("messages.itemFrameUnlocked"));
                } else {
                    // If not fixed and not invulnerable, lock Item Frame
                    frame.setInvulnerable(true);
                    frame.setFixed(true);
                    // Send message to player in the action bar
                    sendActionBarMessage(player, config.getString("messages.itemFrameLocked"));
                }
            }
        }
    }

    // Helper method to send a message to the player in the action bar
    private void sendActionBarMessage(Player player, String message) {
        TextComponent textComponent = Component.text(message);
        player.sendActionBar(textComponent);
    }
}
