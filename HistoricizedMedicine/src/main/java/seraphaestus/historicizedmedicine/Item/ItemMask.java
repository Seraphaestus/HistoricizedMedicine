package seraphaestus.historicizedmedicine.Item;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import seraphaestus.historicizedmedicine.Config;
import seraphaestus.historicizedmedicine.HMedicineMod;

public class ItemMask extends ItemArmor implements IItemBaseData {

	String id; 			public String getId() { return this.id; }
    String name;		public String getName() { return this.name; } 
    int stackSize = 1;		public int getStackSize() { return this.stackSize; } 
    
    public static final ArmorMaterial armorMaterial = EnumHelper.addArmorMaterial("PLAGUE_MASK", HMedicineMod.MODID + ":plague_mask", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
    
	public ItemMask() {
		super(armorMaterial, 0, EntityEquipmentSlot.HEAD);
		this.id = "plague_mask";
        this.name = HMedicineMod.MODID + "." + id;
        if(Config.enableCreativeTab) {
        	setCreativeTab(HMedicineMod.creativeTab);
        }
	}

	
	
}
