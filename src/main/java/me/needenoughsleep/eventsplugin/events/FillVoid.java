package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.BlockBlacklist;
import me.needenoughsleep.eventsplugin.gui.PlayerSelector;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FillVoid implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.GLASS);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.GENERAL;
    }

    @Override
    public String getName() {
        return "Fill void";
    }

    @Override
    public int getPrice() {
        return 15;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("В радиусе 3 метров от игрока исчезнут все блоки");
        return description;
    }

    @Override
    public void run(Player player) {
        PlayerSelector playerSelector = new PlayerSelector((p) -> {
            Location loc = p.getLocation();
            p.playSound(loc, Sound.ENTITY_ENDER_EYE_DEATH, 1, 0);
            for (int x = loc.getBlockX() - 2; x <= loc.getBlockX() + 2; x++)
                for (int z = loc.getBlockZ() - 2; z <= loc.getBlockZ() + 2; z++)
                    for (int y = loc.getBlockY() + 2; y >= loc.getBlockY() - 2; y--) {
                        Block block = loc.getWorld().getBlockAt(x, y, z);
                        if (!BlockBlacklist.contains(block.getType()))
                            block.setType(Material.AIR);
                    }
        });
        player.openInventory(playerSelector.getInventory());
    }
}
