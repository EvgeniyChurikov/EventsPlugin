package me.needenoughsleep.eventsplugin.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface Event {
    enum ColorScheme {
        OWN(ChatColor.DARK_GREEN, ChatColor.GOLD, ChatColor.AQUA),
        GENERAL(ChatColor.YELLOW, ChatColor.GOLD, ChatColor.DARK_AQUA),
        HOSTILE(ChatColor.DARK_PURPLE, ChatColor.GOLD, ChatColor.BLUE);

        private final ChatColor nameColor;
        private final ChatColor priceColor;
        private final ChatColor descriptionColor;

        ColorScheme(
                ChatColor nameColor,
                ChatColor priceColor,
                ChatColor descriptionColor) {
            this.nameColor = nameColor;
            this.priceColor = priceColor;
            this.descriptionColor = descriptionColor;
        }

        public ChatColor getNameColor() {
            return nameColor;
        }

        public ChatColor getPriceColor() {
            return priceColor;
        }

        public ChatColor getDescriptionColor() {
            return descriptionColor;
        }
    }

    ItemStack getIcon();

    ColorScheme getColorScheme();

    String getName();

    int getPrice();

    List<String> getDescription();

    void run(Player player);
}
