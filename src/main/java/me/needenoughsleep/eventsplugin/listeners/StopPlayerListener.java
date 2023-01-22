package me.needenoughsleep.eventsplugin.listeners;

import me.needenoughsleep.eventsplugin.events.StopPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Complements me.quelleen.eventsplugin.events.StopPlayer
 */

public class StopPlayerListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (StopPlayer.stoppedPlayers.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        StopPlayer.stoppedPlayers.remove(e.getPlayer());
    }
}
