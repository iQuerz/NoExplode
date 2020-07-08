package iQuerz.noexplode.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import iQuerz.noexplode.commands.Reload;
import iQuerz.noexplode.listeners.Listeners;

public class NoExplode extends JavaPlugin{

	Listeners listener;
	
	@SuppressWarnings("unused")
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		listener = new Listeners(this);
		getServer().getPluginManager().registerEvents(listener, this);
		this.getCommand("noereload").setExecutor(new Reload(this));
		//Reload reload = new Reload(this);
		Bukkit.getServer().getLogger().info("Enabled " + getDescription().getName() + " v" + getDescription().getVersion() +".");
	}
	
	@Override
	public void onDisable() {
		Bukkit.getServer().getLogger().info("Disabled " + getDescription().getName() + " v" + getDescription().getVersion() +".");
	}
	
	public NoExplode getPlugin() {
		return this;
	}
	
	public void reload() {
		org.bukkit.event.HandlerList.unregisterAll();
		this.reloadConfig();
		listener = new Listeners(this);
		getServer().getPluginManager().registerEvents(listener, this);
	}
}