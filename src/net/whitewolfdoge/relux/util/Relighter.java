package net.whiteWolfdoge.relux.util;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.WorldServer;

import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

public class Relighter{
	/**
	 * Use the following method to relight a chunk
	 * @param Chunk chk		The chunk to be relighted
	 */
	public static void relightChunk(Chunk chk){
		// TODO Load chunk and its adjacent chunks
		if(!chk.isLoaded()){ // IF the chunk is NOT loaded
			chk.load(); // Try to load it
		}
		if(chk.isLoaded()){ // If the chunk is loaded now
			for(byte xLoc = 0; xLoc <= 15; xLoc++){ // For every block on the X axis
				for(int yLoc = 0; yLoc <= 255; yLoc++){ // For every block on the Y axis
					for(byte zLoc = 0; zLoc <= 15; zLoc++){ // For every block on the Z axis
						relightBlock(chk.getBlock(xLoc, yLoc, xLoc)); // Relight the block
					}
				}
			}
		}
	}
	
	/**
	 * Use the following method to relight a single block. This method is
	 * proven to be the most efficient way of re-lighting.
	 * @param blk		The block to be relighted
	 */
	public static void relightBlock(Block blk){
		int blockX =			blk.getX();
		int blockY =			blk.getY();
		int blockZ =			blk.getZ();
		BlockPosition blkPos =	new BlockPosition(blockX, blockY, blockZ);
		
		CraftWorld craftWld = (CraftWorld)blk.getWorld();
		WorldServer worldSrv = craftWld.getHandle();
		worldSrv.x(blkPos); // Magic!
	}
}
