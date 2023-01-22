package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.gui.PlayerSelector;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DropItem implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.STRUCTURE_VOID);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.HOSTILE;
    }

    @Override
    public String getName() {
        return "Drop item";
    }

    @Override
    public int getPrice() {
        return 20;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Заставить игрока упустить предмет из руки");
        return description;
    }

    @Override
    public void run(Player player) {
        PlayerSelector playerSelector = new PlayerSelector((p) -> {
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 0);
            p.dropItem(true);
            p.getInventory().setItemInMainHand(new ItemStack(Material.POISONOUS_POTATO));
            p.sendMessage(ChatColor.AQUA + ":D");
        });
        player.openInventory(playerSelector.getInventory());
    }
}
