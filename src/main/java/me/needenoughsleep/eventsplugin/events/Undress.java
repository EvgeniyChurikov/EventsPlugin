package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.gui.PlayerSelector;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Undress implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.IRON_CHESTPLATE);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.HOSTILE;
    }

    @Override
    public String getName() {
        return "Undress player";
    }

    @Override
    public int getPrice() {
        return 20;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Снять броню с игрока");
        return description;
    }

    @Override
    public void run(Player player) {
        PlayerSelector playerSelector = new PlayerSelector((p) -> {
            p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_DIAMOND, 1, 0);
            for (ItemStack item : p.getInventory().getArmorContents()) {
                if (item != null && !item.containsEnchantment(Enchantment.BINDING_CURSE)) {
                    p.getWorld().dropItemNaturally(p.getLocation(), item);
                    item.setAmount(0);
                }
            }
        });
        player.openInventory(playerSelector.getInventory());
    }
}
