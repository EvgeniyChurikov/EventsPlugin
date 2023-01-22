package me.needenoughsleep.eventsplugin.listeners;

import me.needenoughsleep.eventsplugin.Coupon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Complements me.quelleen.eventsplugin.Coupon
 */

public class DisableCouponCraftingListener implements Listener {
    @EventHandler
    public void onCraftItem(CraftItemEvent e) {
        for (ItemStack item : e.getInventory()) {
            if (item != null && Coupon.getLabel(item) != null) {
                e.setCancelled(true);
                return;
            }
        }
    }
}
