package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.gui.PlayerSelector;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Levitation implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.PHANTOM_MEMBRANE);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.GENERAL;
    }

    @Override
    public String getName() {
        return "Levitation";
    }

    @Override
    public int getPrice() {
        return 55;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Наложить эффект левитации второго уровня");
        return description;
    }

    @Override
    public void run(Player player) {
        PlayerSelector playerSelector = new PlayerSelector((p) -> {
            p.playSound(p.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 1, 0);
            p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 160, 2));
        });
        player.openInventory(playerSelector.getInventory());
    }
}
