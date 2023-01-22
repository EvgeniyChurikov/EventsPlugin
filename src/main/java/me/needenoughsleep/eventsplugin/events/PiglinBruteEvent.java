package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.gui.PlayerSelector;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PiglinBruteEvent implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.GOLDEN_AXE);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.HOSTILE;
    }

    @Override
    public String getName() {
        return "Piglin brute";
    }

    @Override
    public int getPrice() {
        return 25;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Внезапно заспавнить брутального пиглина в игроке");
        return description;
    }

    @Override
    public void run(Player player) {
        PlayerSelector playerSelector = new PlayerSelector((p) -> {
            p.getWorld().spawnEntity(p.getLocation(), EntityType.PIGLIN_BRUTE);
        });
        player.openInventory(playerSelector.getInventory());
    }
}
