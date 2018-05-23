package seraphaestus.historicizedmedicine.CraftingTable;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import seraphaestus.historicizedmedicine.Block.BlockBase;
import seraphaestus.historicizedmedicine.HMedicineMod;

public class HMedCraftingTable extends BlockBase implements ITileEntityProvider {

	public static final int GUI_ID = 1;

	public HMedCraftingTable() {
		super("crafting_table", Material.WOOD, true);
		setHardness(5f);
		setResistance(40f);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new CraftingTableTileEntity();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		//Only execute on the server
		if (worldIn.isRemote) {
			return true;
		}
		TileEntity te = worldIn.getTileEntity(pos);
		if (!(te instanceof CraftingTableTileEntity)) {
			return false;
		}
		playerIn.openGui(HMedicineMod.instance, GUI_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity te = worldIn.getTileEntity(pos);
		CraftingTableTileEntity ctte = (CraftingTableTileEntity) te;
		for (int i = 0; i < 11; i++) {
			ItemStack is = ctte.itemStackHandlerMain.getStackInSlot(i);
			if (is != null && i != CraftingTableTileEntity.outputSlot) {
				worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), is));
			}
		}
		worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(this)));

		if (hasTileEntity(state)) {
			worldIn.removeTileEntity(pos);
		}
	}

}
