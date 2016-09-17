package net.whiteWolfdoge.relux.util;

import net.whiteWolfdoge.relux.ReluxPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Relighter{
	/**
	 * Use the following method to relight a radius of chunks.
	 * @param	cenChk		The center chunk to be relighted.
	 * @param	rad		The radius of the chunks to be selected.
	 * @return			Returns true if the task is successful.
	 */
	public static boolean relightChunkRadius(Chunk cenChk, byte rad){
		return relightChunk(cenChk); //TODO actually do all chunks
	}
	
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
			// TODO Send the affected players the updated data
			Bukkit.getLogger().info(String.format(ReluxPlugin.MSG_PREFIX + "Relit chunk (%d, %d) in world '%s'", chk.getX(), chk.getZ(), chk.getWorld().getName())); // Log the relight
		}
		
		return !loadIssue;
	}
	
	/**
	 * Use the following method to relight a single block.
	 * @param blk		The block to be relighted
	 */
	public static void relightBlock(Block blk){
		int blockX =			blk.getX();
		int blockY =			blk.getY();
		int blockZ =			blk.getZ();
		World blockWorld =		blk.getWorld();
		
		Native.relightBlock(blockX, blockY, blockZ, blockWorld);
	}
}
