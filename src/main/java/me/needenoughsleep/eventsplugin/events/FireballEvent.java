package me.needenoughsleep.eventsplugin.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FireballEvent implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.FIRE_CHARGE);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.OWN;
    }

    @Override
    public String getName() {
        return "Fireball";
    }

    @Override
    public int getPrice() {
        return 20;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Призвать файербол");
        return description;
    }

    @Override
    public void run(Player player) {
        Location loc = player.getEyeLocation();
        loc.add(loc.getDirection());
        Fireball fireball = (org.bukkit.entity.Fireball) player.getWorld().spawnEntity(loc, EntityType.FIREBALL);
        fireball.setYield(5);
        player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 0);
    }
}
