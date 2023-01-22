package me.needenoughsleep.eventsplugin.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class SaveExperienceListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.setKeepLevel(true);
        e.setDroppedExp(0);
    }
}
