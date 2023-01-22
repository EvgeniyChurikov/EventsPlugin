package me.needenoughsleep.eventsplugin.gui;

import me.needenoughsleep.eventsplugin.events.*;
import me.needenoughsleep.eventsplugin.EventsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

/**
 * Listener: Listener: me.needenoughsleep.eventsplugin.listeners.EventMenuListener
 */

public class EventMenu implements Gui {
    final private Player player;
    final private Inventory inventory;
    final private HashMap<Integer, Event> events;

    public static final Collection<Player> cooldownPlayers = new ArrayList<>();

    public EventMenu(Player player) {
        this.player = player;
        inventory = Bukkit.createInventory(this, 36, "Events menu");
        events = new HashMap<>();
        initialize();
        refresh();
    }

    private void initialize() {
        setButton(0, new AddHeart());
        setButton(1, new EnderChest());
        setButton(2, new FireballEvent());
        setButton(3, new GoldenApple());
        setButton(4, new KeepInventoryCoupon());
        setButton(5, new LocateFortress());
        setButton(6, new Return());
        setButton(7, new Spawnpoint());
        setButton(8, new Spectator());
        setButton(9, new Teleport());
        setButton(10, new Unbind());
        setButton(11, new DarkWalker());
        setButton(12, new FillVoid());
        setButton(13, new Levitation());
        setButton(14, new Sonic());
        setButton(15, new Swap());
        setButton(16, new SwapMainhandItems());
        setButton(17, new ChargedCreeper());
        setButton(18, new DropItem());
        setButton(19, new Explosion());
        setButton(20, new Lightning());
        setButton(21, new PiglinBruteEvent());
        setButton(22, new Psssss());
        setButton(23, new Pumpkin());
        setButton(24, new StopPlayer());
        setButton(25, new TakeHeart());
        setButton(26, new Undress());
    }

    private void setButton(int slot, Event event) {
        Event.ColorScheme scheme = event.getColorScheme();
        ItemStack itemStack = event.getIcon();
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(scheme.getNameColor() + event.getName());
        List<String> lore = new ArrayList<>();
        lore.add(scheme.getPriceColor() + "Price: " + event.getPrice());
        lore.add(null);
        List<String> description = event.getDescription();
        for (String str : description) {
            lore.add(scheme.getDescriptionColor() + str);
        }
        meta.setLore(lore);
        meta.addItemFlags(
                ItemFlag.HIDE_ENCHANTS,
                ItemFlag.HIDE_POTION_EFFECTS,
                ItemFlag.HIDE_DYE,
                ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(meta);
        inventory.setItem(slot, itemStack);
        events.put(slot, event);
    }

    public void refresh() {
        for (Map.Entry<Integer, Event> entry : events.entrySet()) {
            int slot = entry.getKey();
            Event event = entry.getValue();
            int exp = player.getTotalExperience();
            ItemStack item = inventory.getItem(entry.getKey());
            ItemMeta meta = item.getItemMeta();
            List<String> lore = meta.getLore();
            if (exp >= event.getPrice()) {
                lore.set(1, ChatColor.GREEN + "You have " + exp + " points");
                meta.addEnchant(Enchantment.OXYGEN, 1, false);
            }
            else {
                lore.set(1, ChatColor.DARK_RED + "You have " + exp + " points");
                meta.removeEnchant(Enchantment.OXYGEN);
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
    }

    @Override
    public void onClick(Player whoClicked, int slot, ClickType clickType) {
        Event event = events.get(slot);
        if (event != null) {
            if (player.getTotalExperience() >= event.getPrice()) {
                player.closeInventory();
                cooldownPlayers.add(player);
                Bukkit.getScheduler().scheduleSyncDelayedTask(EventsPlugin.getInstance(), () -> {
                    cooldownPlayers.remove(player);
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                }, 100L);
                player.giveExp(-event.getPrice());
                event.run(whoClicked);
                player.sendMessage(ChatColor.GREEN + "Event \"" + event.getName() + "\" completed successfully.");
            }
            else {
                player.sendMessage(ChatColor.RED + "You do not have enough points.");
            }
        }
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
