package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.gui.PlayerSelector;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Psssss implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.GUNPOWDER);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.HOSTILE;
    }

    @Override
    public String getName() {
        return "Psssss...";
    }

    @Override
    public int getPrice() {
        return 10;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Проиграть звук крипера другому игроку");
        return description;
    }

    @Override
    public void run(Player player) {
        PlayerSelector playerSelector = new PlayerSelector((p) -> {
            p.playSound(p.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 1, 0);
        });
        player.openInventory(playerSelector.getInventory());
    }
}
