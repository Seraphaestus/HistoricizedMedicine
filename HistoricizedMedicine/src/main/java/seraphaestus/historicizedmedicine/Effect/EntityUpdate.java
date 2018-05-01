package seraphaestus.historicizedmedicine.Effect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatBase;
import net.minecraft.util.FoodStats;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import seraphaestus.historicizedmedicine.Config;

public class EntityUpdate
{
	
	private static HashMap<UUID, Integer> currentBleedDur = new HashMap<UUID, Integer>();
	
    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event)
    {
        if(event.getEntityLiving() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();

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
                PotionEffect pot = player.getActivePotionEffect(RegisterEffects.pain);
                //note: this constructor allows you to set the potion effect to be ambient, as in like a beacon effect
                player.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 5, 1, true, false)); //slowness 2
                player.addPotionEffect(new PotionEffect(Potion.getPotionById(18), 5, 0, true, false)); //weakness

            }

            if(player.isPotionActive(RegisterEffects.infection) && !player.capabilities.isCreativeMode){
            //effect: disable natural health regeneration while the effect is active
            //does not work on peaceful difficulty
                PotionEffect pot = player.getActivePotionEffect(RegisterEffects.infection);
                //effect here
                resetFoodTimer(player.getFoodStats());
            }
        }
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

