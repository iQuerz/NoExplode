package iQuerz.noexplode.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import iQuerz.noexplode.main.NoExplode;

public class Reload implements CommandExecutor{
	
	NoExplode plugin;
	
	public Reload(NoExplode plugin) {
		this.plugin = plugin;
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player&&!sender.isOp()) {
			sender.sendMessage("§cYou don't have the permission to use this command.");
			return false;
		}
		plugin.reload();
		sender.sendMessage("§2NoExplode succesfully reloaded.");
		return true;
    }
}
