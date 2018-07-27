package seraphaestus.historicizedmedicine.Block;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBlockBase extends ItemBlock {

	String[] tooltip;

	public ItemBlockBase(Block block) {
		super(block);
	}

	public ItemBlockBase(Block block, String[] tooltip) {
		super(block);
		this.tooltip = tooltip;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		if (this.tooltip != null) {
			for (String s : this.tooltip) {
				tooltip.add(s);
			}
		}
	}

}
