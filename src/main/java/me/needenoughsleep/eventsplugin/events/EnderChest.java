package me.needenoughsleep.eventsplugin.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EnderChest implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.ENDER_EYE);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.OWN;
    }

    @Override
    public String getName() {
        return "Ender chest";
    }

    @Override
    public int getPrice() {
        return 60;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Открыть инвентарь ендер сундука");
        return description;
    }

    @Override
    public void run(Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1, 1);
        player.openInventory(player.getEnderChest());
    }
}
