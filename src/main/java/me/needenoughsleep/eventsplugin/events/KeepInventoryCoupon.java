package me.needenoughsleep.eventsplugin.events;

import me.needenoughsleep.eventsplugin.Coupon;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Listener: me.quelleen.eventsplugin.listeners.KeepInventoryCouponListener
 */

public class KeepInventoryCoupon implements Event {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.SLIME_BALL);
    }

    @Override
    public ColorScheme getColorScheme() {
        return ColorScheme.OWN;
    }

    @Override
    public String getName() {
        return "Keep inventory";
    }

    @Override
    public int getPrice() {
        return 90;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("Сохраняет инвентарь 1 раз во время смерти");
        return description;
    }

    @Override
    public void run(Player player) {
        ItemStack coupon = Coupon.create("KeepInventory");
        ItemMeta meta = coupon.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "Keep inventory coupon");
        meta.setLore(new ArrayList<>(Collections.singletonList("This coupon will save your inventory on death.")));
        coupon.setItemMeta(meta);
        player.getInventory().addItem(coupon);
        player.playSound(player.getLocation(), Sound.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, 1, 2);
    }
}
