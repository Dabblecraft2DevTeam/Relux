package net.whiteWolfdoge.relux;

import org.bukkit.ChatColor;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class ReluxPlugin extends JavaPlugin{
	private TabExecutor tex;
	protected static final String 
		CMD_MAIN =					"relux",
		PERMISSION_USE =			"relux.use",
		MSG_PREFIX =				ChatColor.LIGHT_PURPLE + "[Relux] " + ChatColor.YELLOW,
		MSG_INFO =					MSG_PREFIX + "Relux allows manual chunk relighting. Written by WhiteWolfdoge (Emily White)",
		MSG_USAGE =					MSG_PREFIX + "Usage: " + ChatColor.ITALIC + "/relux <radius> [<xPos> <zPos>]",
		MSG_USAGE_INCORRECT =		MSG_PREFIX + ChatColor.RED + "No no no, you did that all wrong!",
		MSG_PERMISSION_DENIED =		MSG_PREFIX + ChatColor.RED + "You don't have permission to do that.";
	protected static final int
		MIN_RADIUS =	1,
		MAX_RADIUS = 	15;
	
	@Override
	public void onEnable(){
		tex = new InputAnalyzer();
		getCommand(CMD_MAIN).setExecutor(tex);
		getCommand(CMD_MAIN).setTabCompleter(tex);
	}
	
	@Override
	public void onDisable(){
		//
	}
}
