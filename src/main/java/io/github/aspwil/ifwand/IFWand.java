package io.github.aspwil.ifwand;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class IFWand extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Save the default config if it doesn't exist
        saveDefaultConfig();
        // Register the listener to respond when someone hits an Item Frame
        Bukkit.getPluginManager().registerEvents(new PlayerWandEventListener(this), this);
        // Set up the command to spawn an Item Frame Wand, otherwise "/ifwand" is used
        getCommand(getConfig().getString("command.wand", "ifwand")).setExecutor(new IFWandCommandExecutor(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}