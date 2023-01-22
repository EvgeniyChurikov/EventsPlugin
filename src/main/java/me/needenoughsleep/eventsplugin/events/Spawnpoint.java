package me.needenoughsleep.eventsplugin.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Spawnpoint implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.BEACON);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.OWN;
    }

    @Override
    public String getName() {
        return "Spawnpoint";
    }

    @Override
    public int getPrice() {
        return 60;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Поставить спаунпоинт");
        return description;
    }

    @Override
    public void run(Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 1);
        player.setBedSpawnLocation(player.getLocation(), true);
    }
}
