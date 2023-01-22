package me.needenoughsleep.eventsplugin.listeners;

import me.needenoughsleep.eventsplugin.EventsPlugin;
import me.needenoughsleep.eventsplugin.gui.NonClosingGui;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * Complements me.quelleen.eventsplugin.gui.NonClosingGui
 */

public class NonClosingGuiListener implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getView().getTopInventory().getHolder() instanceof NonClosingGui
                && e.getReason() == InventoryCloseEvent.Reason.PLAYER) {
            Bukkit.getServer().getScheduler().runTask(
                    EventsPlugin.getInstance(),
                    () -> e.getPlayer().openInventory(e.getInventory())
            );
        }
    }

}
