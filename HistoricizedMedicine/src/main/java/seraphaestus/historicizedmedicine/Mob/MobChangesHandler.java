package seraphaestus.historicizedmedicine.Mob;

import java.util.Collection;

import com.google.common.base.Predicate;

import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import seraphaestus.historicizedmedicine.Mob.Rat.EntityRat;

public class MobChangesHandler {

	public static void postInit() {
		Collection<EntityEntry> entities = ForgeRegistries.ENTITIES.getValuesCollection();
		for(EntityEntry entry : entities) {
			//if entry extends EntityOcelot
			if(EntityOcelot.class.isAssignableFrom(entry.getEntityClass())) {
				EntityTameable entity;
				try {
					entity = ((EntityTameable)entry.getEntityClass().newInstance());
					entity.targetTasks.addTask(1, new EntityAITargetNonTamed<EntityRat>(entity, EntityRat.class, false, isRatTamed()));
				} catch (InstantiationException e) {
				} catch (IllegalAccessException e) {
				}
				
			}
		}
	}
	
	private static Predicate<EntityRat> isRatTamed() {
		return null;
	}
	
}
