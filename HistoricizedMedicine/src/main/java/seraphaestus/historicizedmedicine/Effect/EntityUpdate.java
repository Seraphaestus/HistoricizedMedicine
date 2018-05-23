package seraphaestus.historicizedmedicine.Effect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import seraphaestus.historicizedmedicine.Config;
import seraphaestus.historicizedmedicine.HardCodedValues;
import seraphaestus.historicizedmedicine.Block.RegistryHandler;
import seraphaestus.historicizedmedicine.Item.ItemMask;
import seraphaestus.historicizedmedicine.Mob.PlagueDoctor.EntityPlagueDoctor;
import seraphaestus.historicizedmedicine.Mob.Rat.EntityRat;

public class EntityUpdate
{
	private static final int plagueParticleEveryXTicks = 6;
	private static HashMap<UUID, Integer> currentBleedDur = new HashMap<UUID, Integer>();
	
    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event)
    {
        if(event.getEntityLiving() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            
            Iterator<ItemStack> armor = player.getArmorInventoryList().iterator();
            boolean wearingMask = false;
            while(armor.hasNext()) {
            	if(armor.next().getItem() instanceof ItemMask) {
            		wearingMask = true;
            		break;
            	}
            }
            if(wearingMask) {
        		player.addPotionEffect(new PotionEffect(RegisterEffects.plagueImmunity, 10, 0, true, false));
            }
            
            //nbt tag handling
            
            NBTTagCompound nbt = player.getEntityData();	
            for(String id : RegistryHandler.statues) {
            	int cooldown = nbt.getInteger(id + "_cooldown");
            	if(cooldown != 0) {
            		nbt.setInteger(id + "_cooldown", cooldown - 1);	
    			}
            }
		
            
            //potion effects implementation:
            
            if(player.isPotionActive(RegisterEffects.bleeding) && !player.capabilities.isCreativeMode){
            //effect: reduces health by a constant amount, at even intervals over the duration of the effect
                PotionEffect pot = player.getActivePotionEffect(RegisterEffects.bleeding);
            	if(currentBleedDur.get(player.getUniqueID()) == null) {
            		currentBleedDur.put(player.getUniqueID(), pot.getDuration());
            	}
            	Integer bleedTick = currentBleedDur.get(player.getUniqueID());
            	if(bleedTick != null) {
            		bleedTick = bleedTick / (Config.bleedTotalAmount * (pot.getAmplifier() + 1));
	            	if(pot.getDuration() % bleedTick == 0) {
	            		//do effect
	            		player.setHealth(player.getHealth() - 1); //setHealth clamps between 0 and maxHealth so no need to verify
	            	}
            	}
            } else {
            	currentBleedDur.remove(player.getUniqueID());
            }

            if(player.isPotionActive(RegisterEffects.pain) && !player.capabilities.isCreativeMode){
            //effect: gives you weakness and slowness 2 while in effect
                //PotionEffect pot = player.getActivePotionEffect(RegisterEffects.pain);
                //note: this constructor allows you to set the potion effect to be ambient, as in like a beacon effect
                player.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 5, 1, true, false)); //slowness 2
                player.addPotionEffect(new PotionEffect(Potion.getPotionById(18), 5, 0, true, false)); //weakness

            }

            if(player.isPotionActive(RegisterEffects.infection) && !player.capabilities.isCreativeMode){
            //effect: disable natural health regeneration while the effect is active
            //does not work on peaceful difficulty
                //PotionEffect pot = player.getActivePotionEffect(RegisterEffects.infection);
                //effect here
                resetFoodTimer(player.getFoodStats());
            }
            
            if(player.isPotionActive(RegisterEffects.plague) && !player.capabilities.isCreativeMode){
            	//spreading plague
            	for(Entity entity : player.getEntityWorld().loadedEntityList) {
            		if(HardCodedValues.catchesPlague(entity) && entity instanceof EntityLiving) {
            			if(!((EntityLiving) entity).isPotionActive(RegisterEffects.plague) && !((EntityLiving) entity).isPotionActive(RegisterEffects.plagueImmunity)) {
                        	if(player.getDistanceToEntity(entity) <= Config.plagueRange){
                        		((EntityLiving) entity).addPotionEffect(new PotionEffect(RegisterEffects.plague, Config.plagueDuration));       		
                        		if(entity.hasCustomName() && !player.getEntityWorld().isRemote) {
                        			TextComponentString message = new TextComponentString(entity.getCustomNameTag() + " has caught " + Config.plagueName);
                        			List<EntityPlayer> players = player.getEntityWorld().playerEntities;
                        			for(EntityPlayer p : players) {
                        				p.sendMessage(message);
                        			}
                        		}
                        	}
            			}
            		}

            	}

            }
            
        } else {
        	//not instance of player
        	EntityLiving entity = (EntityLiving) event.getEntityLiving();
        	if(entity.isPotionActive(RegisterEffects.plague) && Config.enablePlague) {
        		int plagueDuration = entity.getActivePotionEffect(RegisterEffects.plague).getDuration();
        		if(entity instanceof EntityPlagueDoctor) {
        			entity.removeActivePotionEffect(RegisterEffects.plague);
        		}
        		if(plagueDuration != 0) {
        			if(plagueDuration == 1 && !(entity instanceof EntityRat)) {
        				//death
        				entity.attackEntityFrom(new DamageSource("HMedPlague"), entity.getHealth() + 1);
        				if(entity.hasCustomName() && !entity.getEntityWorld().isRemote) {
                			TextComponentString message = new TextComponentString(entity.getCustomNameTag() + " succumbed to " + Config.plagueName);
                			List<EntityPlayer> players = entity.getEntityWorld().playerEntities;
                			for(EntityPlayer p : players) {
                				p.sendMessage(message);
                			}
                		}
        			}
        			Random rnd = entity.getRNG();
        			if(plagueDuration % plagueParticleEveryXTicks == 0) {
        				renderParticles(entity, rnd);
        			}
    			}
        	}
        }
    }
    
    @SideOnly(Side.CLIENT)
    private void renderParticles(Entity entity, Random rnd) {
    	AxisAlignedBB box = entity.getEntityBoundingBox();
		Minecraft.getMinecraft().effectRenderer.addEffect(new PlagueEffect(entity.getEntityWorld(), entity.posX + (box.maxX - box.minX) * (rnd.nextFloat() - 0.5f), entity.posY + (box.maxY - box.minY) * (rnd.nextFloat()), entity.posZ + (box.maxZ - box.minZ) * (rnd.nextFloat() - 0.5f), 0.0f, 0.0f, 0.0f));   
    }

    private static Field foodTimerField = null;
    private void resetFoodTimer(FoodStats foodStats) {
		if (foodTimerField == null)
			foodTimerField = ReflectionHelper.findField(FoodStats.class, "field_75123_d", "foodTimer");
		if (foodStats.getFoodLevel() > 0) {
			try { foodTimerField.set(foodStats, 0); }
			catch (Exception ex) { throw new RuntimeException(ex); }
		}
}
}

