package com.creeperevents.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ForceDismount extends JavaPlugin implements CommandExecutor {
	public void onEnable() {
		getCommand("dismount").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("forcedismount.use"))
			if(args.length != 0) {
				Player player = Bukkit.getPlayer(args[0]);
				if(player != null) {
					if(!player.hasPermission("forcedismount.exempt")) {
						Entity vehicle = player.getVehicle();
						if(vehicle != null) {
							vehicle.eject();
							@SuppressWarnings("deprecation")
							String name = vehicle.getType().getName();
							name = name.toLowerCase();
							name = name.replace("rideable", "");
							name = name.replace("entity", "");
							sender.sendMessage(ChatColor.GOLD + player.getDisplayName() + " forcefully dismounted their " + name + ".");
						} else
							sender.sendMessage(ChatColor.RED + player.getDisplayName() + " isn't riding a vehicle!");
					} else
						sender.sendMessage(ChatColor.RED + "You can't force " + player.getDisplayName() + " to dismount a vehicle!");
				} else
					sender.sendMessage(ChatColor.RED + "Couldn't find that player!");
			} else
				sender.sendMessage(ChatColor.RED + "You have to enter a name!");
		else
			sender.sendMessage(ChatColor.RED + "No permission!");

		return true;
	}
}
