package net.whiteWolfdoge.relux;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Entity;

/**
 * This class handles all direct input from the user.
 */
class InputAnalyzer implements TabExecutor{
	
	/**
	 * Constructs a new InputAnalyzer
	 */
	InputAnalyzer(){
		// Do nothing
	}
	
	/**
	 * Returns a list of possible completions for a command argument.<br />
	 * <b>NOTE:</b> For internal use only!
	 * @return			A list of possible completions for the command argument
	 * @param sender	The issuer of the command
	 * @param cmd		The command issued
	 * @param alias		The alias of the command issued
	 * @param args		The arguments of the command issued
	 */
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args){
		// TODO
		return null;
	}
	
	/**
	 * Executes an issued command.<br />
	 * <b>NOTE:</b> For internal use only!
	 * @return			True if the command was executed successfully
	 * @param sender	The issuer of the command
	 * @param cmd		The command issued
	 * @param alias		The alias of the command issued
	 * @param args		The arguments of the command issued
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args){
		if(args.length == 0){ // If there's no argument, tell info
			sender.sendMessage(new String[]{ReluxPlugin.MSG_INFO, ReluxPlugin.MSG_USAGE});
		}
		boolean hasPerm = 		sender.hasPermission(ReluxPlugin.PERMISSION_USE);
		boolean requiresLoc =	true;
		if(sender instanceof Entity || sender instanceof Block)
			requiresLoc = false;
	}
}
