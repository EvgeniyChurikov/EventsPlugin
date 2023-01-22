package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.Timer;
import me.needenoughsleep.eventsplugin.gui.PlayerSelector;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Listener: me.quelleen.eventsplugin.listeners.DarkWalkerListener
 */

public class DarkWalker implements Event {
    public static Collection<Player> walkers = new ArrayList<>();

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.NETHERITE_BOOTS);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.GENERAL;
    }

    @Override
    public String getName() {
        return "Dark walker";
    }

    @Override
    public int getPrice() {
        return 35;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("На протяжении 5-ти секунд под выбранным игроком");
        description.add("появляется бедрок.");
        return description;
    }

    @Override
    public void run(Player player) {
        PlayerSelector playerSelector = new PlayerSelector((p) -> {
            Timer timer = new Timer(
                    () -> {
                        walkers.add(p);
                        p.sendMessage(ChatColor.DARK_PURPLE + "Теперь ты повелитель бедрока!");
                        p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1, 0);
                    },
                    (t) -> {
                        p.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + t);
                    },
                    () -> {
                        walkers.remove(p);
                        p.sendMessage(ChatColor.DARK_PURPLE + "Ты больше не повелитель бедрока...");
                    },
                    5, 20L
            );
            timer.start();
        });
        player.openInventory(playerSelector.getInventory());
    }
}
