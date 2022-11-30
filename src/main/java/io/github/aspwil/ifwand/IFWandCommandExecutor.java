package io.github.aspwil.ifwand;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class IFWandCommandExecutor implements CommandExecutor {
    private NamespacedKey isWandKey;

    public IFWandCommandExecutor(IFWand pluginReference) {
        this.isWandKey = new NamespacedKey(pluginReference, "isIFWand");;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //check if not sent from player
        if (!(sender instanceof Player)) {
            //end
            return false;
        }
        //get player object
        Player player = (Player) sender;
        //check if correct amount of arguments
        if(args.length == 0){
            //build wand
            ItemStack wand = new ItemStack(Material.BLAZE_ROD);
            ItemMeta meta = wand.getItemMeta();
            meta.displayName(Component.text("Item Frame Wand", NamedTextColor.DARK_AQUA));
            meta.lore(Arrays.asList(
                    Component.text("[ITEM FRAME WAND]", NamedTextColor.RED),
                    Component.text("Hit An Item Frame To lock it", NamedTextColor.RED)
            ));
            meta.getPersistentDataContainer().set(isWandKey, PersistentDataType.INTEGER, 1);
            //update the wands item meta
            wand.setItemMeta(meta);
            //give the player the item
            player.getInventory().addItem(wand);
            //end
            return true;
        }
        //end
        return false;
    }
}
