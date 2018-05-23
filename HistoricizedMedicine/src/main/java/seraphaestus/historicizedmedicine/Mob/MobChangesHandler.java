package seraphaestus.historicizedmedicine.Mob;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import seraphaestus.historicizedmedicine.Mob.Rat.EntityRat;

public class MobChangesHandler {

	//if tamed, should only target rats with plague
	//if not tamed, should always target rats
	@SubscribeEvent
	public void addAITasks(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		if(EntityOcelot.class.isAssignableFrom(entity.getClass())) {
			EntityTameable entityTameable = (EntityTameable) entity;		
			entityTameable.targetTasks.addTask(1, new EntityAITargetNonTamedHasPlague<EntityRat>(entityTameable, EntityRat.class, false, (Predicate<EntityRat>)null));	
		}
	}
	
}
