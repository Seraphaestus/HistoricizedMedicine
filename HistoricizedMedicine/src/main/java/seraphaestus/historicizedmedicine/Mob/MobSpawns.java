package seraphaestus.historicizedmedicine.Mob;

import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import seraphaestus.historicizedmedicine.Mob.Rat.EntityRat;

public class MobSpawns {

    @SubscribeEvent
    public void onEntityUpdate(EntityJoinWorldEvent event)
    {
    	if(event.getEntity() instanceof EntityRat) {
    		if(event.getEntity().dimension != 0) {
        		event.setCanceled(true);		
    		}
    	}
    }
    	
}
