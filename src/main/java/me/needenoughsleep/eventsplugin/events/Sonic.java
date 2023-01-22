package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.Timer;
import me.needenoughsleep.eventsplugin.gui.PlayerSelector;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Sonic implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.SUGAR);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.GENERAL;
    }

    @Override
    public String getName() {
        return "Sonic";
    }

    @Override
    public int getPrice() {
        return 35;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Разогнать кого-то на 5 секунд");
        return description;
    }

    @Override
    public void run(Player player) {
        PlayerSelector playerSelector = new PlayerSelector((p) -> {
            Timer timer = new Timer(
                    () -> {
                        p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.5);
                        p.sendMessage(ChatColor.DARK_GREEN + "Щекастик, мчись как ветер!");
                        p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_DEATH, 1, 2);
                    },
                    (t) -> {
                        p.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + t);
                    },
                    () -> {
                        p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1);
                        p.sendMessage(ChatColor.DARK_GREEN + "Ааа.. та ты просто безмозглый дурачёк :)");
                    },
                    5, 20L
            );
            timer.start();
        });
        player.openInventory(playerSelector.getInventory());
    }
}
