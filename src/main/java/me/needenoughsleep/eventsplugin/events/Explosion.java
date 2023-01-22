package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.gui.PlayerSelector;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Explosion implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.TNT_MINECART);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.HOSTILE;
    }

    @Override
    public String getName() {
        return "Explosion";
    }

    @Override
    public int getPrice() {
        return 35;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Взорвать игрока");
        return description;
    }

    @Override
    public void run(Player player) {
        PlayerSelector playerSelector = new PlayerSelector((p) -> {
            p.getWorld().createExplosion(p.getLocation(), 1);
        });
        player.openInventory(playerSelector.getInventory());
    }
}
