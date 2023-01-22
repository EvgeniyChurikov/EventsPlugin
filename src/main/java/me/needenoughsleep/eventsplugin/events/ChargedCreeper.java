package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.Timer;
import me.needenoughsleep.eventsplugin.gui.PlayerSelector;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ChargedCreeper implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.CREEPER_SPAWN_EGG);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.HOSTILE;
    }

    @Override
    public String getName() {
        return "Charged creeper";
    }

    @Override
    public int getPrice() {
        return 22;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Через пять секунд после вызова ивента на месте,");
        description.add("где стоял игрок на момент вызова ивента,");
        description.add("появится заряженный крипер.");
        return description;
    }

    @Override
    public void run(Player player) {
        PlayerSelector playerSelector = new PlayerSelector((p) -> {
            Location loc = p.getLocation();
            Timer timer = new Timer(
                    () -> p.sendMessage(ChatColor.DARK_GREEN
                            + "Осторожно! До спавна заряженного крипера осталось 5 секунд!"),
                    (t) -> p.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + t),
                    () -> {
                        p.sendMessage(ChatColor.DARK_GREEN + "Psssss...");
                        p.playSound(p.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 1, 0);
                        Creeper creeper = (Creeper) p.getWorld().spawnEntity(loc, EntityType.CREEPER);
                        creeper.setPowered(true);
                    },
                    5, 20L
            );
            timer.start();
        });
        player.openInventory(playerSelector.getInventory());
    }
}
