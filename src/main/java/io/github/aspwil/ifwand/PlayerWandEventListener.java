package io.github.aspwil.ifwand;

import io.papermc.paper.event.player.PlayerItemFrameChangeEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import static net.kyori.adventure.text.Component.*;

public class PlayerWandEventListener implements Listener {
    private NamespacedKey isWandKey;

    public PlayerWandEventListener(IFWand pluginReference) {
        this.isWandKey = new NamespacedKey(pluginReference, "isIFWand");;
    }

    @EventHandler
    public void onPlayerItemFrameChangeEvent(PlayerItemFrameChangeEvent event){
        //get the player
        Player player = event.getPlayer();
        //check if player is holding valid wand
        if(player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(isWandKey)){
            //check if player has hit item frame
            if(event.getAction() == PlayerItemFrameChangeEvent.ItemFrameChangeAction.REMOVE){
                //stop the removal of the item from the item frame
                event.setCancelled(true);
                //get the item frame entity
                ItemFrame frame = event.getItemFrame();
                //check if frame is not fixed and invunrable
                if(!frame.isFixed() && !frame.isInvulnerable()) {
                    //lock frame
                    frame.setInvulnerable(true);
                    frame.setFixed(true);
                    //send message to player
                    player.sendMessage(text("Item Frame Locked", NamedTextColor.RED));
                }
            }
        }
    }
}