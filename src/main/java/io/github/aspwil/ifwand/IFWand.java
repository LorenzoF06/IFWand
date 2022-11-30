package io.github.aspwil.ifwand;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class IFWand extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        //register the listener to respond when someone hits an item frame
        Bukkit.getPluginManager().registerEvents(new PlayerWandEventListener(this), this);
        //set up the command to spawn a wand
        getCommand("ifwand").setExecutor(new IFWandCommandExecutor(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
