package seraphaestus.historicizedmedicine.Item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import seraphaestus.historicizedmedicine.Config;

public class KnowledgeSheet extends ItemBase {

	private int maxKnowledge;
	private Item[] knowledgeGivers;
	private String useCommand;
	private int tier = 0;	public int getTier() { return tier; }
	
	public KnowledgeSheet(String id, int maxKnowledge, Item[] knowledgeGivers, String useCmd, int tier) {
		super(id, 1);
		this.maxKnowledge = maxKnowledge;
		this.knowledgeGivers = knowledgeGivers;
		this.useCommand = useCmd;
		this.tier = tier;
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
		if(knowledge + 1 <= this.maxKnowledge) {
			nbt.setInteger("currentKnowledge", knowledge + 1);
		}
	}
	
	public void takeKnowledge(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		if(nbt == null) {
			stack.setTagCompound(new NBTTagCompound());
			return;
		}
		int knowledge = nbt.getInteger("currentKnowledge");
		if(knowledge - 1 >= 0) {
			nbt.setInteger("currentKnowledge", knowledge - 1);
		}
	}
	
	public void setMaxKnowledge(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		if(nbt == null) {
			stack.setTagCompound(new NBTTagCompound());
			return;
		}
		KnowledgeSheet ks = (KnowledgeSheet)stack.getItem();
		nbt.setInteger("currentKnowledge", ks.maxKnowledge);
	}
	
	private int getCurrentKnowledge(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		if(nbt == null) {
			stack.setTagCompound(new NBTTagCompound());
			return 0;
		}
		return nbt.getInteger("currentKnowledge");
	}
	public boolean isFull(ItemStack stack) {
		return maxKnowledge == getCurrentKnowledge(stack);
	}
	public boolean isEmpty(ItemStack stack) {
		return 0 == getCurrentKnowledge(stack);
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
      return isFull(stack);
  	}
	
}
