package me.needenoughsleep.eventsplugin.listeners;

import me.needenoughsleep.eventsplugin.EventsPlugin;
import me.needenoughsleep.eventsplugin.gui.EventMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Complements me.quelleen.eventsplugin.EventMenu
 */

public class EventMenuListener implements Listener {
    @EventHandler
    public void onExpChange(PlayerExpChangeEvent e) {
        Player player = e.getPlayer();
        if (player.getOpenInventory().getTopInventory().getHolder() instanceof EventMenu) {
            EventMenu menu = (EventMenu) player.getOpenInventory().getTopInventory().getHolder();
            Bukkit.getServer().getScheduler().runTask(EventsPlugin.getInstance(), menu::refresh);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        EventMenu.cooldownPlayers.remove(e.getPlayer());
    }
}
