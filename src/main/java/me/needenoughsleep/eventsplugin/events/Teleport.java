package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.gui.PlayerSelector;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Teleport implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.ENDER_PEARL);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.OWN;
    }

    @Override
    public String getName() {
        return "Teleport";
    }

    @Override
    public int getPrice() {
        return 100;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Телепортироваться к игроку");
        return description;
    }

    @Override
    public void run(Player player) {
        PlayerSelector playerSelector = new PlayerSelector((p) -> {
            player.teleport(p);
            player.sendMessage(ChatColor.YELLOW + "You were successfully teleported to the player " + p.getName());
            p.sendMessage( ChatColor.YELLOW + "The player " + player.getName() + " was teleported to you");
        });
        player.openInventory(playerSelector.getInventory());
    }
}
