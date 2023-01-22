package me.needenoughsleep.eventsplugin.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GoldenApple implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.GOLDEN_APPLE);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.OWN;
    }

    @Override
    public String getName() {
        return "Golden apple";
    }

    @Override
    public int getPrice() {
        return 30;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Получить золотое яблоко");
        return description;
    }

    @Override
    public void run(Player player) {
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BURP, 1, 1);
        player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
    }
}
