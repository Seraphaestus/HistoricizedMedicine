package seraphaestus.historicizedmedicine.Effect;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityUpdate
{
    final int painTickUpdate = 5;

    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event)
    {
        if(event.getEntityLiving() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();

            if(player.isPotionActive(RegisterEffects.bleeding) && !player.capabilities.isCreativeMode){
                PotionEffect pot = player.getActivePotionEffect(RegisterEffects.bleeding);
                //effect here
            }

            if(player.isPotionActive(RegisterEffects.pain) && !player.capabilities.isCreativeMode){
                PotionEffect pot = player.getActivePotionEffect(RegisterEffects.pain);
                //effect here

                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(2), painTickUpdate)); //slowness
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(18), painTickUpdate)); //weakness

            }

            if(player.isPotionActive(RegisterEffects.infection) && !player.capabilities.isCreativeMode){
                PotionEffect pot = player.getActivePotionEffect(RegisterEffects.infection);
                //effect here
            }
        }
    }
}

