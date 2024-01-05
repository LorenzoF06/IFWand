package io.github.aspwil.ifwand;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import static net.kyori.adventure.text.Component.*;

import java.util.Arrays;

public class IFWandCommandExecutor implements CommandExecutor {
    private NamespacedKey isWandKey;
    private FileConfiguration config;

    public IFWandCommandExecutor(IFWand pluginReference) {
        this.isWandKey = new NamespacedKey(pluginReference, "isIFWand");
        this.config = pluginReference.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if not sent from player
        if (!(sender instanceof Player)) {
            // End
            return false;
        }
        // Get player object
        Player player = (Player) sender;
        // Check if the player is holding 1 Blaze Rod
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        if (heldItem.getType() == Material.BLAZE_ROD && heldItem.getAmount() == 1) {
            // Build Item Frame Wand
            ItemStack wand = new ItemStack(Material.BLAZE_ROD);
            ItemMeta meta = wand.getItemMeta();
            meta.displayName(text(config.getString("wand.name")));
            meta.lore(Arrays.asList(text(config.getString("wand.lore"))));
            meta.getPersistentDataContainer().set(isWandKey, PersistentDataType.INTEGER, 1);
            // Update item meta of Item Frame Wand
            wand.setItemMeta(meta);
            // Replace held Blaze Rod with Item Frame Wand
            player.getInventory().setItemInMainHand(wand);
            // End
            return true;
        } else {
            // Send error message
            player.sendMessage(text(config.getString("messages.noBlazeRod")));
            return true;
        }
    }
}