package seraphaestus.historicizedmedicine.Item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import seraphaestus.historicizedmedicine.Util.Pair;
import seraphaestus.historicizedmedicine.Util.Reduce;

public class MedKitTool extends MedKitBase {

	public MedKitTool(String id, int stackSize, int minHealth, int maxHealth, PotionEffect[] effect,
			Potion[] cure, Reduce[] reduce, int heal, int maxDurability) {
		super(id, stackSize, minHealth, maxHealth, effect, cure, reduce, heal);
		this.setMaxDamage(maxDurability - 1);
	}

	@Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }
	
	@Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 30;
    }
	
	@Override
	protected void consumption(ItemStack stack, EntityLivingBase entityIn) {
    	stack.damageItem(1, entityIn);
    }
}
