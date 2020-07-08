package iQuerz.noexplode.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import iQuerz.noexplode.main.NoExplode;

public class Listeners implements Listener{

	NoExplode plugin;
	List<String> areas;
	Location location1, location2;
	public Listeners(NoExplode plugin) {
		this.plugin = plugin;
		areas = new ArrayList<String>();
		areas = plugin.getConfig().getStringList("areas");
	}
	
	@EventHandler
	public void onExplode(EntityExplodeEvent event) {
		
		for(String area : areas) {
			String[] s = area.split(",");
			s = sort(s);
			double x1 = Double.parseDouble(s[1]);
			double y1 = Double.parseDouble(s[2]);
			double z1 = Double.parseDouble(s[3]);
			double x2 = Double.parseDouble(s[4]);
			double y2 = Double.parseDouble(s[5]);
			double z2 = Double.parseDouble(s[6]);
			
			location1 = new Location(Bukkit.getWorld(s[0]),x1,y1,z1);
			location2 = new Location(Bukkit.getWorld(s[0]),x2,y2,z2);
			
			Location expLocation = event.getLocation();
			if(expLocation.getBlockX()>location1.getBlockX()&&expLocation.getBlockX()<location2.getBlockX()) {
				if(expLocation.getBlockY()>location1.getBlockY()&&expLocation.getBlockY()<location2.getBlockY()) {
					if(expLocation.getBlockZ()>location1.getBlockZ()&&expLocation.getBlockZ()<location2.getBlockZ()) {
						event.setCancelled(true);
						return;
					}
				}
			}
		}
		
	}
	public void reloadAreas() {
		areas = plugin.getPlugin().getConfig().getStringList("areas");
	}
	
	public String[] sort(String[] s) {
		String temp;
		if(Double.parseDouble(s[1])>Double.parseDouble(s[4])) {
			temp = s[1];
			s[1] = s[4];
			s[4] = temp;
		}
		if(Double.parseDouble(s[2])>Double.parseDouble(s[5])) {
			temp = s[2];
			s[2] = s[5];
			s[5] = temp;
		}
		if(Double.parseDouble(s[3])>Double.parseDouble(s[6])) {
			temp = s[3];
			s[3] = s[6];
			s[6] = temp;
		}
		return s;
	}
}
