package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.gui.PlayerSelector;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Pumpkin implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.CARVED_PUMPKIN);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.HOSTILE;
    }

    @Override
    public String getName() {
        return "Pumpkin";
    }

    @Override
    public int getPrice() {
        return 45;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Надеть неснимаемую тыкву на жертву");
        return description;
    }

    @Override
    public void run(Player player) {
        PlayerSelector playerSelector = new PlayerSelector((p) -> {
            ItemStack helmet = p.getInventory().getHelmet();
            if (helmet != null)
                p.getWorld().dropItemNaturally(p.getLocation(), helmet);
            ItemStack pumpkin = new ItemStack(Material.CARVED_PUMPKIN);
            pumpkin.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
            p.getInventory().setHelmet(pumpkin);
            p.playSound(p.getLocation(), Sound.ENTITY_WITCH_CELEBRATE, 1, 1.5f);
        });
        player.openInventory(playerSelector.getInventory());
    }
}
