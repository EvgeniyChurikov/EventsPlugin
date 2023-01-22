package me.needenoughsleep.eventsplugin.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Unbind implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.ARMOR_STAND);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.OWN;
    }

    @Override
    public String getName() {
        return "Unbind";
    }

    @Override
    public int getPrice() {
        return 55;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Снять с себя всё неснимаемое");
        return description;
    }

    @Override
    public void run(Player player) {
        for (ItemStack item : player.getInventory().getArmorContents()) {
            if (item != null && item.containsEnchantment(Enchantment.BINDING_CURSE)) {
                player.getWorld().dropItemNaturally(player.getLocation(), item);
                item.setAmount(0);
            }
        }
        player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 0);
    }
}
