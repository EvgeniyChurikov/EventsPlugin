package me.needenoughsleep.eventsplugin.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AddHeart implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.BEETROOT);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.OWN;
    }

    @Override
    public String getName() {
        return "Add heart";
    }

    @Override
    public int getPrice() {
        return 75;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Добавить себе 1 сердечко");
        return description;
    }

    @Override
    public void run(Player player) {
        player.playSound(player.getLocation(), Sound.ITEM_HONEY_BOTTLE_DRINK, 1, 2);
        AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        maxHealth.setBaseValue(maxHealth.getBaseValue() + 2);
        Bukkit.broadcastMessage(ChatColor.YELLOW + "Игрок " + player.getName() + " восполнил себе 1 сердечко. Теперь его максимальное здоровье " + ChatColor.RED + (int) maxHealth.getBaseValue()/2 + "❤");
    }
}
