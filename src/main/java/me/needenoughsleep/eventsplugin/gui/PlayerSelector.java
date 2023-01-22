package me.needenoughsleep.eventsplugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerSelector implements NonClosingGui{
    public interface PlayerAction {
        void run(Player player);
    }

    final List<Player> players;
    final PlayerAction action;

    public PlayerSelector(PlayerAction action) {
        players = new ArrayList<>();
        this.action = action;
    }

    @Override
    public void onClick(Player whoClicked, int slot, ClickType clickType) {
        if (slot < players.size()) {
            action.run(players.get(slot));
            whoClicked.closeInventory();
        }
    }

    @Override
    public Inventory getInventory() {
        players.clear();
        players.addAll(Bukkit.getOnlinePlayers());
        Inventory inventory = Bukkit.createInventory(this, 27, "Select player");
        for (Player player : players) {
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) skull.getItemMeta();
            meta.setOwningPlayer(player);
            meta.setDisplayName(ChatColor.YELLOW + player.getName());
            skull.setItemMeta(meta);
            inventory.addItem(skull);
        }
        return inventory;
    }
}
