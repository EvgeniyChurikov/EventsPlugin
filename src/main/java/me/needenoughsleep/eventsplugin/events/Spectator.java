package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.Timer;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Spectator implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.SOUL_LANTERN);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.OWN;
    }

    @Override
    public String getName() {
        return "Spectator";
    }

    @Override
    public int getPrice() {
        return 60;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Спектатор на 5 секунд");
        return description;
    }

    @Override
    public void run(Player player) {
        Timer timer = new Timer(
                () -> {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.playSound(player.getLocation(), Sound.UI_TOAST_OUT, 1, 2);
                },
                (t) -> player.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + t),
                () -> {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.playSound(player.getLocation(), Sound.UI_TOAST_IN, 1, 2);
                },
                3, 20L
        );
        timer.start();
    }
}
