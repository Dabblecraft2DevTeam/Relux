package net.whiteWolfdoge.relux.util;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

public class Relighter{
	/**
	 * Use the following method to relight a single chunk.
	 * @param chk		The chunk to be relighted
	 * @return			Returns true if the task is successful.
	 */
	public static boolean relightChunk(Chunk chk){
		// Collect all affected Chunks
		Chunk[] affectedChks = new Chunk[9];
		for(int xLoc = chk.getX() - 1; xLoc < chk.getX() + 2; xLoc++){ // Iterate 3 times on x, starting one chunk west
			for(int zLoc = chk.getZ() - 1; zLoc < chk.getZ() + 2; zLoc++){ // Iterate 3 times on y, starting one chunk north
				affectedChks[(xLoc - chk.getX() + 1) * 3 + (zLoc - chk.getZ() + 1)] = chk.getWorld().getChunkAt(xLoc, zLoc);
			}
		}
		
		boolean loadIssue = false;
		for(int currChk = 0; currChk < affectedChks.length && !loadIssue; currChk++){ // For every chunk, while there is no loading issue
			if(! affectedChks[currChk].load()) loadIssue = true; // If chunk loading was NOT successful, flag loading issue
			else; // Else it's ready to be relighted
		}
		
		if(!loadIssue){ // If there was no loading issue with the affected chunks
			for(byte xLoc = 0; xLoc <= 15; xLoc++){ // For every block on the x axis
				for(int yLoc = 255; yLoc >= 0; yLoc--){ // For every block on the y axis, top to bottom
					for(byte zLoc = 0; zLoc <= 15; zLoc++){ // For every block on the z axis
						Block currBlk = chk.getBlock(xLoc, yLoc, zLoc); // Pick the block in the chunk at the relative position
						relightBlock(currBlk); // Relight the picked block
					}
				}
			}
			Bukkit.getLogger().info(String.format("[Relux] Relit chunk (%d, %d) in world '%s'", chk.getX(), chk.getZ(), chk.getWorld().getName())); // Log the relight
			// TODO Maybe unload chunk
		}
		
		return !loadIssue;
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
