package me.needenoughsleep.eventsplugin.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Return implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.CAMPFIRE);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.OWN;
    }

    @Override
    public String getName() {
        return "Return";
    }

    @Override
    public int getPrice() {
        return 80;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Телепортироваться на свой спаунпоинт");
        return description;
    }

    @Override
    public void run(Player player) {
        Location bedSpawnLocation = player.getBedSpawnLocation();
        if (bedSpawnLocation != null) {
            player.teleport(bedSpawnLocation);
            player.playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 1, 2);
        }
        else {
            player.sendMessage(ChatColor.RED + "У вас нет спаунпоинта");
        }
    }
}
