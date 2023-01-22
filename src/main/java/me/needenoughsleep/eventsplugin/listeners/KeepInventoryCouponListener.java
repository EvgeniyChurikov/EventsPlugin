package me.needenoughsleep.eventsplugin.listeners;

import me.needenoughsleep.eventsplugin.Coupon;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

/**
 * Complements me.quelleen.eventsplugin.events.KeepInventoryCoupon
 */

public class KeepInventoryCouponListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        for (ItemStack item : e.getEntity().getInventory()) {
            if (item != null && Objects.equals(Coupon.getLabel(item), "KeepInventory")) {
                e.getEntity().sendMessage(item.getItemMeta().getDisplayName() + ChatColor.WHITE + " has been used");
                item.setAmount(item.getAmount() - 1);
                e.setKeepInventory(true);
                e.getDrops().clear();
                e.setKeepLevel(true);
                e.setDroppedExp(0);
                break;
            }
        }
    }
}
