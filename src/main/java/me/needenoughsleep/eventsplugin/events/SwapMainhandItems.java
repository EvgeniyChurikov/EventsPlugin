package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.gui.PlayerSelector;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SwapMainhandItems implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.COMPARATOR);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.GENERAL;
    }

    @Override
    public String getName() {
        return "Swap mainhand items";
    }

    @Override
    public int getPrice() {
        return 65;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Свапнуть предмет в руке с предметом в руке другого игрока");
        return description;
    }

    @Override
    public void run(Player player) {
        PlayerSelector playerSelector = new PlayerSelector((p) -> {
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1, 2);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 1, 2);
            ItemStack item = player.getInventory().getItemInMainHand();
            player.getInventory().setItemInMainHand(p.getInventory().getItemInMainHand());
            p.getInventory().setItemInMainHand(item);
            p.sendMessage(ChatColor.AQUA + "Предметы свапнуты :D");
            player.sendMessage(ChatColor.AQUA + "Предметы свапнуты :D");
        });
        player.openInventory(playerSelector.getInventory());
    }
}
