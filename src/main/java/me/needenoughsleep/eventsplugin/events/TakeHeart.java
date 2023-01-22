package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.gui.PlayerSelector;
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

public class TakeHeart implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.SPIDER_EYE);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.HOSTILE;
    }

    @Override
    public String getName() {
        return "Take heart";
    }

    @Override
    public int getPrice() {
        return 65;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Отнять у кого-то 1 сердечко");
        return description;
    }

    @Override
    public void run(Player player) {
        PlayerSelector playerSelector = new PlayerSelector((p) -> {
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BREATH, 1, 2);
            AttributeInstance maxHealth = p.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            maxHealth.setBaseValue(maxHealth.getBaseValue() - 2);
            Bukkit.broadcastMessage(ChatColor.YELLOW + "У игрока " + p.getName() + " отняли 1 сердечко. Теперь его максимальное здоровье " + ChatColor.RED + (int) maxHealth.getBaseValue()/2 + "❤");
        });
        player.openInventory(playerSelector.getInventory());
    }
}
