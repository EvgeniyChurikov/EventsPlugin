package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.Timer;
import me.needenoughsleep.eventsplugin.gui.PlayerSelector;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Listener: me.quelleen.eventsplugin.listeners.StopPlayerListener
 */

public class StopPlayer implements Event {
    public static Collection<Player> stoppedPlayers = new ArrayList<>();

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.COBWEB);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.HOSTILE;
    }

    @Override
    public String getName() {
        return "Stop";
    }

    @Override
    public int getPrice() {
        return 25;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Застопорить игрока на 4 секунды");
        return description;
    }

    @Override
    public void run(Player player) {
        PlayerSelector playerSelector = new PlayerSelector((p) -> {
            Timer timer = new Timer(
                    () -> {
                        stoppedPlayers.add(p);
                        p.sendMessage(ChatColor.RED + "Тебя застопорили на 4 секунды");
                        p.playSound(p.getLocation(), Sound.BLOCK_HONEY_BLOCK_STEP, 1, 0);
                    },
                    (t) -> {
                        p.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + t);
                    },
                    () -> {
                        stoppedPlayers.remove(p);
                        p.sendMessage(ChatColor.GREEN + "Теперь ты свободен");
                    },
                    4, 20L
            );
            timer.start();
        });
        player.openInventory(playerSelector.getInventory());
    }
}
