package seraphaestus.historicizedmedicine;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs {
	
	public CreativeTab() {
		super(HMedicineMod.MODID);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Item.getByNameOrId(HMedicineMod.MODID + ":trephine"));
	}
}
