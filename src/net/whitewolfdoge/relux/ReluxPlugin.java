package net.whiteWolfdoge.relux;

import java.util.LinkedList;

import net.whiteWolfdoge.relux.util.Relighter;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ReluxPlugin extends JavaPlugin{
	public final byte defaultRadius = 3;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("relux")){ // If the command was "relux" or one of its aliases
			if(!sender.hasPermission("relux.use")){ // If the command sender does not have permission
				sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
				return true;
			}
			else if(args.length < 1){ // If there was no argument
				return processRequest(sender, Integer.toString(defaultRadius));
			}
			else if(args.length > 1){ // If there was more than one argument
				return false;
			}
			else{
				return processRequest(sender, args[0]);
			}
		}
		else{ // Command not recognized by this plugin
			return false;
		}
	}
	
	private boolean processRequest(CommandSender sender, String radRaw){
		if(sender.getName().equalsIgnoreCase("CONSOLE")){ // If the command is issued from the console
			sender.sendMessage(ChatColor.RED + "This command cannot be issued from the console.");
		}
		else{
			try{
				byte rad = Byte.parseByte(radRaw);
				if(rad < 0) throw new NumberFormatException();
				
				Block centerBlock = ((Player)sender).getLocation().getBlock();
				Chunk centerChunk = ((Player)sender).getWorld().getChunkAt(centerBlock);
				
				LinkedList<Chunk> chunks = new LinkedList<Chunk>();
				chunks.offer(centerChunk);// TODO queue all chunks
				
				sender.sendMessage("Relighting...");
				Chunk currChk;
				while((currChk = chunks.poll()) != null){
					Relighter.relightChunk(currChk);
				}
				
				sender.sendMessage("Done.");
			}
			catch(NumberFormatException nfe){ // The radius wans't a valid number
				return false;
			}
		}
		
		return true;
	}
}
