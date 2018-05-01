package seraphaestus.historicizedmedicine.Item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import seraphaestus.historicizedmedicine.Util.Pair;
import seraphaestus.historicizedmedicine.Util.Reduce;

public class MedKitFood extends MedKitBase {

	public int hunger = 0;
	public int saturation = 0;
	
	public MedKitFood(String id, int stackSize, int minHealth, int maxHealth, PotionEffect[] effect, Potion[] cure, Reduce[] reduce, int heal, int hunger, int saturation){
        super(id, stackSize, minHealth, maxHealth, effect, cure, reduce, heal);
        this.hunger = hunger;
        this.saturation = saturation;
	}
	
	@Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.EAT;
    }
	
	@Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 20;
    }
	
	@Override
	protected void extraEffects(EntityLivingBase entityLiving) {
		EntityPlayer player;
		if(entityLiving instanceof EntityPlayer) {
        	player = (EntityPlayer)entityLiving;
        	player.getFoodStats().addStats(hunger, saturation);
    	}
    }
}
