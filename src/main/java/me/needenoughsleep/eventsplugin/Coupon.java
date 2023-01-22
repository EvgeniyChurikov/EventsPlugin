package me.needenoughsleep.eventsplugin;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nullable;

/**
 * Listener: me.quelleen.eventsplugin.listeners.DisableCouponCraftingListener
 */

public class Coupon {
    private static final Material material = Material.SLIME_BALL;
    private static final String containerKey = "CouponLabel";

    public static ItemStack create(String label) {
        ItemStack coupon = new ItemStack(material);
        ItemMeta meta = coupon.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(Enchantment.OXYGEN, 1, false);
        meta.getPersistentDataContainer().set(
                new NamespacedKey(EventsPlugin.getInstance(), containerKey),
                PersistentDataType.STRING,
                label);
        coupon.setItemMeta(meta);
        return coupon;
    }

    @Nullable
    public static String getLabel(ItemStack coupon) {
        return coupon.getItemMeta().getPersistentDataContainer().get(
                new NamespacedKey(EventsPlugin.getInstance(), containerKey),
                PersistentDataType.STRING);
    }
}
