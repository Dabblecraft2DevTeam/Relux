package net.whiteWolfdoge.relux.util;

import net.minecraft.server.v1_9_R1.BlockPosition;
import net.minecraft.server.v1_9_R1.WorldServer;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;

class Native{
	
	/**
	 * <b>Note:</b> This method makes native calls and will need to be updated when the NMS version changes.
	 * @param inBlockX
	 * @param inBlockY
	 * @param inBlockZ
	 * @param inBlockWorld
	 * @return The result from the native method.
	 **/
	/*This method calls the following native method:
		public boolean ?(BlockPosition blockposition){
			boolean flag = false;
			
			if(!this.worldProvider.o()){
				flag |= this.c(EnumSkyBlock.SKY, blockposition);
			}
			
			flag |= this.c(EnumSkyBlock.BLOCK, blockposition);
			return flag;
		}
	 */
	static boolean relightBlock(int inBlockX, int inBlockY, int inBlockZ, World inBlockWorld){
		BlockPosition blkPos =	new BlockPosition(inBlockX, inBlockY, inBlockZ);
		
		CraftWorld craftWld = (CraftWorld)inBlockWorld;
		WorldServer worldSrv = craftWld.getHandle();
		return worldSrv.w(blkPos); // Magic!
	}
}
