package net.whiteWolfdoge.relux;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class ReluxPlugin extends JavaPlugin{
	private TabExecutor tex;
	protected static final int
		MIN_RADIUS =	1,
		MAX_RADIUS = 	15;
	protected static final String 
		CMD_MAIN =						"relux",
		PERMISSION_USE =				"relux.use",
		MSG_PREFIX =					ChatColor.LIGHT_PURPLE + "[Relux] " + ChatColor.YELLOW,
		MSG_INFO =						MSG_PREFIX + "Relux allows manual chunk relighting. Written by WhiteWolfdoge (Emily White)",
		MSG_USAGE =						MSG_PREFIX + "Usage: " + ChatColor.ITALIC + "/relux <radius> [<xPos> <zPos>]",
		MSG_EX_PERMISSION_DENIED =		MSG_PREFIX + ChatColor.RED + "You were denied permission to use this plugin.",
		MSG_EX_ARGS_INVALID_QTY =		MSG_PREFIX + ChatColor.RED + "Invalid quantity of arguments!",
		MSG_EX_ARGS_INVALID_RADIUS =	MSG_PREFIX + ChatColor.RED + String.format("The radius must be an integer in the range of %i through %i", MIN_RADIUS, MAX_RADIUS),
		MSG_EX_INVALID_SOURCE =			MSG_PREFIX + ChatColor.RED + "You must use this command from within a world.";
	
	// TODO write doc
	@Override
	public void onLoad(){
		tex = new InputAnalyzer();
		getCommand(CMD_MAIN).setExecutor(tex);
		getCommand(CMD_MAIN).setTabCompleter(tex);
	}
	
	// TODO write doc
	@Override
	public void onEnable(){
		// TODO
	}
	
	// TODO write doc
	@Override
	public void onDisable(){
		//
	}
	
	// TODO write doc
	public void relightChunks(Chunk centerchunk, int radius){
		// TODO
	}
}
