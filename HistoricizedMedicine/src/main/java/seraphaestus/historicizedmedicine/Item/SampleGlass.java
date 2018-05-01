package seraphaestus.historicizedmedicine.Item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import seraphaestus.historicizedmedicine.HMedicineMod;

public class SampleGlass extends ItemBase {

    public SampleGlass(){
        super("sample_glass", 64);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 40;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        ItemStack itemStackHeld = playerIn.getHeldItem(hand);
        return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackHeld);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
    	try {
    		EntityPlayer player = (EntityPlayer)entityLiving;
    		player.addItemStackToInventory(new ItemStack(Item.getByNameOrId(HMedicineMod.MODID + ":" + "urine")));
    	}
    	catch(Exception e) {
    		
    	}
        stack.setCount(stack.getCount() - 1);
        return stack;
    }
}

