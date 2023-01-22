package me.needenoughsleep.eventsplugin.events;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class LocateFortress implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.NETHER_BRICKS);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.OWN;
    }

    @Override
    public String getName() {
        return "Locate fortress";
    }

    public int getPrice() {
        return 350;
    }

    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Узнать координаты фортресса");
        return description;
    }

    public void run(Player player) {
        Location loc = player.getWorld().locateNearestStructure(
                player.getLocation(),
                StructureType.NETHER_FORTRESS,
                256,
                false);
        if (loc != null) {
            player.sendMessage("The nearest fortress is at "
                    + ChatColor.GREEN + "[" + loc.getBlockX() + ", ~, " + loc.getBlockZ() + "] "
                    + ChatColor.WHITE + "(" + (int) player.getLocation().distance(loc) + "blocks away)");
            player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1, 0);
        }
        else {
            player.sendMessage(ChatColor.RED + "Could not find the fortress nearby.");
        }
    }
}
