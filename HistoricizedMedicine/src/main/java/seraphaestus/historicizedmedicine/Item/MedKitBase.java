package seraphaestus.historicizedmedicine.Item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
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
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import seraphaestus.historicizedmedicine.Config;

public class MedKitBase extends ItemBase {

    private int minHealthReq = -1;
    private int maxHealthReq = -1;
    private PotionEffect[] effect;
    private Potion[] cure;
    private Reduce[] reduce;
    private int heal = 0;

    public MedKitBase(String id, String unlocName, int stackSize, int minHealth, int maxHealth, PotionEffect[] effect, Potion[] cure, Reduce[] reduce, int heal){
        super(id, unlocName, stackSize);
        this.minHealthReq = minHealth;
        this.maxHealthReq = maxHealth;
        this.effect = effect;
        this.cure = cure;
        this.reduce = reduce;
        this.heal = heal;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 1;
    }

    public void knowledgeHandler(EntityPlayer playerIn) {
    	NonNullList<ItemStack> inv = playerIn.inventory.mainInventory;
    	try {
    		inv.addAll(playerIn.inventory.offHandInventory);
    	}
    	catch(Exception e){
    		
    	}
    	for(ItemStack i : inv) {
    		//find a knowledge sheet in the inventory
    		if(i.getItem() instanceof KnowledgeSheet) {
    			//check against list of items that give knowledge to that sheet
    			KnowledgeSheet kS = (KnowledgeSheet)i.getItem();
    			if(kS.isFull(i)) {
    				return;
    			}
    			//replace with fresh copy that retains its knowledgeGiver list, hopefully
    			if(kS.id == RegistryHandler.ebers.id) {
    				kS = RegistryHandler.ebers;
    			}
    			//
    			for(Item i2 : kS.getKnGivers()) {
    				if(i2 instanceof ItemBase) {
    					ItemBase ibase = (ItemBase)i2;
    					if(this.id == ibase.id) {
    						//then there is a match, so add knowledge to the sheet
    						kS.addKnowledge(i);
    					}
    				}
    			}
    		}
    	}
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        ItemStack itemStackHeld = playerIn.getHeldItem(hand);

        if (minHealthReq == -1 || playerIn.getHealth() > minHealthReq){
            if (maxHealthReq == -1 || playerIn.getHealth() < maxHealthReq){
                playerIn.setActiveHand(hand);
                return new ActionResult(EnumActionResult.PASS, itemStackHeld);
            }
        }

        return new ActionResult(EnumActionResult.FAIL, itemStackHeld);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
    	if(entityLiving instanceof EntityPlayer) {
        	knowledgeHandler((EntityPlayer)entityLiving);
    	}
    	
    	//add associated effects
    	if(effect != null) {
	        for(PotionEffect p : effect){
	        	PotionEffect pe = entityLiving.getActivePotionEffect(p.getPotion());
	        	int dur = 0;
	        	if(pe != null) {
	        		dur = pe.getDuration();
	        		entityLiving.removeActivePotionEffect(p.getPotion());
	        	}
	        	p = new PotionEffect(p.getPotion(), p.getDuration() + dur);
	            entityLiving.addPotionEffect(new PotionEffect(p));
	        }
    	}
    	//remove associated effects
    	if(cure != null) {
	        for(Potion p : cure){
	            entityLiving.removePotionEffect(p);
	        }
    	}
    	//reduce associated effects
    	if(reduce != null) {
	        for(Reduce r : reduce){
	        	PotionEffect pe = entityLiving.getActivePotionEffect(r.p);
	        	if(pe != null) {
		        	int dur = pe.getDuration();
		            entityLiving.removeActivePotionEffect(r.p);
		            if(dur - r.dur > 0) {
		            	entityLiving.addPotionEffect(new PotionEffect(r.p, dur - r.dur));
		            }
	        	}
	        }
    	}
        float newHealth = entityLiving.getHealth() + heal;
        if (newHealth > entityLiving.getMaxHealth()){
            newHealth = entityLiving.getMaxHealth();
        }

        entityLiving.setHealth(newHealth);
        extraEffects(entityLiving);
        consumption(stack, entityLiving);
        return stack;
    }
    
    protected void consumption(ItemStack stack, EntityLivingBase entityIn) {
    	stack.setCount(stack.getCount() - 1);
    }
    protected void extraEffects(EntityLivingBase entityLiving) {
    	
    }
    
    @Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag)
	{

		if(GuiScreen.isShiftKeyDown()) {	
			if(Config.canViewHealAmountInTooltip) {
				//healing
				if(heal > 0) {
					tooltip.add("Heals " + (float)heal / 2 + " hearts");
				} else if(heal < 0) {
					tooltip.add("Damages by " + (float)heal / -2 + " hearts");
				}
			}
			if(Config.canViewEffectsInTooltip) {
		    	//re: removing effects
		    	if(cure != null) {
			        for(Potion p : cure){
			        	tooltip.add("Cures " + potionName(p));
			        }
		    	}
		    	//re: reducing effects
		    	if(reduce != null) {
			        for(Reduce r : reduce){
			        	tooltip.add("Reduces " + potionName(r.p) + " duration by " + (float)r.dur / 20 + " seconds");
			        }
		    	}
				//re: adding effects
				if(effect != null) {
			        for(PotionEffect p : effect){
						tooltip.add("Gives " + potionName(p.getPotion()) + " for " + (float)p.getDuration() / 20 + " seconds");
			        }
		    	}
			}
		}
		else {
			tooltip.add("Shift to view effects of use");
			if(this.minHealthReq != -1 || this.maxHealthReq != -1) {
				int min = minHealthReq;
				int max = maxHealthReq;
				if(min == -1) min = 0;
				if(max == -1) {
					tooltip.add("Usable when above " + (float)min / 2 + " hearts");
					
				} else {
					tooltip.add("Usable between " + (float)min / 2 + " and " + (float)max / 2 + " hearts");				
				}
			}
		}
	}
    
    private String potionName(Potion p) {
    	String s = p.getName();
    	if(s.contains("effect.")) {
    		s = s.split("\\.")[1];
    		char[] ca = s.toCharArray();
    		ca[0] = Character.toUpperCase(ca[0]);
    		s = new String(ca);
    	}
    	return s;
    }
}

