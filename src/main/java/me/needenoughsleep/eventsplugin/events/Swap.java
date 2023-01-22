package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.Timer;
import me.needenoughsleep.eventsplugin.gui.PlayerSelector;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Swap implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.GENERAL;
    }

    @Override
    public String getName() {
        return "Swap";
    }

    @Override
    public int getPrice() {
        return 160;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Свапнуться с игроком");
        return description;
    }

    @Override
    public void run(Player player) {
        PlayerSelector playerSelector = new PlayerSelector((p) -> {
            Timer timer = new Timer(
                    () -> {
                        player.sendMessage(ChatColor.YELLOW + "You will be swapped with " + p.getName() + " in 5 seconds.");
                        p.sendMessage(ChatColor.YELLOW + "You will be swapped with " + player.getName() + " in 5 seconds.");
                    },
                    (t) -> {
                        player.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + t +
                                ChatColor.RESET + ChatColor.RED + " seconds left before swap.");
                        p.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + t +
                                ChatColor.RESET + ChatColor.RED + " seconds left before swap.");
                    },
                    () -> {
                        Location loc = player.getLocation();
                        player.teleport(p);
                        p.teleport(loc);

                        player.sendMessage(ChatColor.GREEN + "Successful swap");
                        p.sendMessage(ChatColor.GREEN + "Successful swap");
                    },
                    5, 20L
            );
            timer.start();
        });
        player.openInventory(playerSelector.getInventory());
    }
}
