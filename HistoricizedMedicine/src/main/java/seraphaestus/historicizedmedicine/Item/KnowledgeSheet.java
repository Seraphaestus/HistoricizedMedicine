package seraphaestus.historicizedmedicine.Item;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import seraphaestus.historicizedmedicine.Config;
import seraphaestus.historicizedmedicine.HMedicineMod;

public class KnowledgeSheet extends ItemBase {

	private int maxKnowledge;
	private Item[] knowledgeGivers;
	private String useCommand;
	
	
	public KnowledgeSheet(String id, String name, int maxKnowledge, Item[] knowledgeGivers, String useCmd) {
		super(id, name, 1);
		this.maxKnowledge = maxKnowledge;
		this.knowledgeGivers = knowledgeGivers;
		this.useCommand = useCmd;
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        return EnumActionResult.PASS;
    }
	
	public Item[] getKnGivers(){
		return this.knowledgeGivers;
	}
	
	public void addKnowledge(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		if(nbt == null) {
			stack.setTagCompound(new NBTTagCompound());
			return;
		}
		int knowledge = nbt.getInteger("currentKnowledge");
		nbt.setInteger("currentKnowledge", knowledge + 1);
	}
	
	private int getCurrentKnowledge(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		if(nbt == null) {
			stack.setTagCompound(new NBTTagCompound());
			return 0;
		}
		return (int)nbt.getInteger("currentKnowledge");
	}
	public boolean isFull(ItemStack stack) {
		return maxKnowledge == getCurrentKnowledge(stack);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag)
	{
		tooltip.add("Knowledge: " + getCurrentKnowledge(stack) + "/" + maxKnowledge);
		if(GuiScreen.isShiftKeyDown()) {
			tooltip.add("Use medical items to increase your knowledge which will advance you to the next stage of medical understanding");
		}
	}
	
	@Override
    public int getMaxItemUseDuration(ItemStack stack) {
		if((this.useCommand != null)) {
			return 1;
		} else {
			return 0;
		}
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        ItemStack itemStackHeld = playerIn.getHeldItem(hand);
        if(this.isFull(itemStackHeld)) {
        	return new ActionResult(EnumActionResult.PASS, itemStackHeld);
        }
        return new ActionResult(EnumActionResult.FAIL, itemStackHeld);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
    	if (this.useCommand != null) {
    		worldIn.getMinecraftServer().getCommandManager().executeCommand(worldIn.getMinecraftServer().getCommandSenderEntity(), this.useCommand);
        	if(Config.knowledgeSheetConsumed) {
        		stack.setCount(stack.getCount() - 1);
        	}
    	}
        return stack;
    }
    
    //used to give enchanted glint
    @Override
    public boolean hasEffect(ItemStack stack) {
      NBTTagCompound nbtTagCompound = stack.getTagCompound();
      return isFull(stack);
  	}
	
}
