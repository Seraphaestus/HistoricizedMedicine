package seraphaestus.historicizedmedicine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import seraphaestus.historicizedmedicine.CraftingTable.CraftingTableContainer;
import seraphaestus.historicizedmedicine.CraftingTable.CraftingTableGUI;
import seraphaestus.historicizedmedicine.CraftingTable.CraftingTableTileEntity;

public class GUIProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof CraftingTableTileEntity) {
			return new CraftingTableContainer(player.inventory, (CraftingTableTileEntity) te);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof CraftingTableTileEntity) {
			CraftingTableTileEntity containerTileEntity = (CraftingTableTileEntity) te;
			return new CraftingTableGUI(containerTileEntity, new CraftingTableContainer(player.inventory, containerTileEntity));
		}
		return null;
	}
}