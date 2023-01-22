package me.needenoughsleep.eventsplugin.listeners;

import me.needenoughsleep.eventsplugin.BlockBlacklist;
import me.needenoughsleep.eventsplugin.events.DarkWalker;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Complements me.quelleen.eventsplugin.events.DarkWalker
 */

public class DarkWalkerListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (DarkWalker.walkers.contains(e.getPlayer())) {
            Block block = e.getPlayer().getLocation().add(0, -1, 0).getBlock();
            if (!BlockBlacklist.contains(block.getType()))
                block.setType(Material.BEDROCK);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        DarkWalker.walkers.remove(e.getPlayer());
    }
}
