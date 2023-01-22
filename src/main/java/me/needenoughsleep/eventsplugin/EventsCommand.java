package me.needenoughsleep.eventsplugin;

import me.needenoughsleep.eventsplugin.gui.EventMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EventsCommand implements TabExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
            return true;
        }
        else if (args.length > 0) {
            sender.sendMessage(ChatColor.RED + "Command entered incorrectly.");
            return false;
        }
        else {

            /////////////

            Player player = (Player) sender;
            if (EventMenu.cooldownPlayers.contains(player)) {
                player.sendMessage(ChatColor.RED + "Cooldown");
            }
            else {
                EventMenu gui = new EventMenu(player);
                player.openInventory(gui.getInventory());
            }

            /////////////

            return true;
        }
    }

    public List<String> onTabComplete(CommandSender Sender, Command cmd, String label, String[] args) {
        return new ArrayList<>();
    }
}
