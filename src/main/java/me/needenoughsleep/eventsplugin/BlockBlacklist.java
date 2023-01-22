package me.needenoughsleep.eventsplugin;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class BlockBlacklist {
    private static final Collection<Material> blacklist = new ArrayList<>(Arrays.asList(
            Material.BEDROCK,
            Material.END_PORTAL_FRAME,
            Material.END_PORTAL
    ));

    public static Collection<Material> get() {
        return blacklist;
    }

    public static boolean contains(Material blockMaterial) {
        return blacklist.contains(blockMaterial);
    }
}
