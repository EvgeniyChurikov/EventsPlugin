package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.gui.PlayerSelector;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Lightning implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.PRISMARINE_SHARD);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.HOSTILE;
    }

    @Override
    public String getName() {
        return "Lightning";
    }

    @Override
    public int getPrice() {
        return 25;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Ударить молнией игрока");
        return description;
    }

    @Override
    public void run(Player player) {
        PlayerSelector playerSelector = new PlayerSelector((p) -> {
            p.getWorld().strikeLightning(p.getLocation());
        });
        player.openInventory(playerSelector.getInventory());
    }
}
