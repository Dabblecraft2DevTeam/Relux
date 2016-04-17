package net.whiteWolfdoge.relux;

import java.util.LinkedList;

import net.minecraft.server.v1_8_R3.ChunkCoordIntPair;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.whiteWolfdoge.relux.util.Relighter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ReluxPlugin extends JavaPlugin{
	public final byte defaultRadius = 3;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){ // Used for command preprocessing
		if(cmd.getName().equalsIgnoreCase("relux")){ // If the command was "relux" or one of its aliases
			if(!sender.hasPermission("relux.use")){ // If the command sender does NOT have permission
				sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
				return true;
			}
			else return parseRequest(sender, args);
		}
		else{ // Command not recognized by this plugin
			return false;
		}
	}
	
	private boolean parseRequest(CommandSender sender, String[] args){
		if(sender instanceof Entity || sender instanceof Block){ // If sent from an Entity or Block
			// Get the center Chunk
			Chunk centerChunk = null;
			if(sender instanceof Entity) centerChunk = ((Entity)sender).getLocation().getChunk();
			else centerChunk = ((Block)sender).getChunk();
			
			// Process the Radius
			byte radius;
			if(args.length == 0) radius = 0; // If there was no argument
			else if(args.length == 1){ // If there was one argument
				try{
					radius = Byte.parseByte(args[0]);
				}
				catch(NumberFormatException nfex){ // The argument was not a valid number
					return false;
				}
			}
			else return false; // Else there was more than one argument
			
			Relighter.relightChunk(centerChunk);
			Bukkit.getLogger().info("Processed chunk!");
			Player pl = ((Player)sender);
			HumanEntity he = (HumanEntity)pl;
			EntityPlayer ep = (EntityPlayer)he;
			ep.chunkCoordIntPairQueue.add(new ChunkCoordIntPair(centerChunk.getX(), centerChunk.getZ())); // TODO DEBUG
			return true; // TODO DEBUG
			
			//return processRequest(centerChunk, radius); // TODO
		}
		else{ // Else we can't get the location
			sender.sendMessage(ChatColor.RED + "This command cannot be issued from the console.");
			return true;
		}
	}
	
	private boolean processRequest(Chunk centerChunk, byte radius){
		return false; // TODO
	}
	
	private boolean processRequest(CommandSender sender, String radRaw){
		try{
			try{
				byte rad = Byte.parseByte(radRaw);
				if(rad < 0) throw new NumberFormatException();
			}
			catch(NumberFormatException nfex){ // The radius wans't a valid number
				return false;
			}
			
			Block centerBlock = ((Player)sender).getLocation().getBlock();
			Chunk centerChunk = ((Player)sender).getWorld().getChunkAt(centerBlock);
			
			LinkedList<Chunk> chunks = new LinkedList<Chunk>();
			if(chunks.offer(centerChunk));// TODO queue all chunks
			else{
				sender.sendMessage("An unexpected exception has occurred:\nThe chunk could not be queued!");
				throw new Exception();
			}
			
			sender.sendMessage("Relighting...");
			Chunk currChk;
			while((currChk = chunks.poll()) != null){
				Relighter.relightChunk(currChk);
			}
			
			sender.sendMessage("Done.");
		}
		catch(Exception ex){
			return false;
		}
		
		return true;
	}
}
