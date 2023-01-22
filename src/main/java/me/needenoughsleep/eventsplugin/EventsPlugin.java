package me.needenoughsleep.eventsplugin;

import me.needenoughsleep.eventsplugin.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public class EventsPlugin extends JavaPlugin {
    private static JavaPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new DarkWalkerListener(), this);
        getServer().getPluginManager().registerEvents(new DisableCouponCraftingListener(), this);
        getServer().getPluginManager().registerEvents(new EventMenuListener(), this);
        getServer().getPluginManager().registerEvents(new GuiListener(), this);
        getServer().getPluginManager().registerEvents(new KeepInventoryCouponListener(), this);
        getServer().getPluginManager().registerEvents(new NonClosingGuiListener(), this);
        getServer().getPluginManager().registerEvents(new SaveExperienceListener(), this);
        getServer().getPluginManager().registerEvents(new StopPlayerListener(), this);
        getCommand("events").setExecutor(new EventsCommand());
    }

    public static JavaPlugin getInstance() {
        return instance;
    }
}
