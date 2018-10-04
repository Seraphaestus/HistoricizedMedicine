package seraphaestus.historicizedmedicine.CraftingTable;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
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
	private static boolean keepInventory;

	public HMedCraftingTable() {
		super("crafting_table", Material.WOOD, true);
		setHardness(5f);
		setResistance(40f);
		setSoundType(SoundType.WOOD);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new CraftingTableTileEntity();
	}

	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!keepInventory)
		{
			TileEntity tileentity = worldIn.getTileEntity(pos);

			if (tileentity instanceof CraftingTableTileEntity)
			{
				InventoryHelper.dropInventoryItems(worldIn, pos, (CraftingTableTileEntity)tileentity);
				worldIn.updateComparatorOutputLevel(pos, this);
			}
		}

		super.breakBlock(worldIn, pos, state);
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
}
