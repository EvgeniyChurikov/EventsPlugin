package me.needenoughsleep.eventsplugin.listeners;

import me.needenoughsleep.eventsplugin.EventsPlugin;
import me.needenoughsleep.eventsplugin.gui.Gui;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Complements me.quelleen.eventsplugin.gui.Gui
 */

public class GuiListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTopInventory().getHolder() instanceof Gui) {
            e.setCancelled(true);
            if (e.getClickedInventory() != null
                    && e.getClickedInventory().getHolder() instanceof Gui
                    && e.getWhoClicked() instanceof Player) {
                Gui gui = (Gui) e.getClickedInventory().getHolder();
                Bukkit.getServer().getScheduler().runTask(EventsPlugin.getInstance(),
                        () -> gui.onClick((Player) e.getWhoClicked(), e.getSlot(), e.getClick()));
            }
        }
    }
}
