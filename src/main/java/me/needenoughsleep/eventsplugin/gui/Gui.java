package me.needenoughsleep.eventsplugin.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryHolder;

public interface Gui extends InventoryHolder {
    void onClick(Player whoClicked, int slot, ClickType clickType);
}
