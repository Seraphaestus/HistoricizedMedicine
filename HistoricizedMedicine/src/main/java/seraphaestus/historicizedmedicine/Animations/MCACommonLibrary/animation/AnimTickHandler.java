package seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.animation;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import seraphaestus.historicizedmedicine.Animations.MCACommonLibrary.IMCAnimatedEntity;

public class AnimTickHandler {
	private ArrayList<IMCAnimatedEntity> activeEntities = new ArrayList<IMCAnimatedEntity>();
	private ArrayList<IMCAnimatedEntity> removableEntities = new ArrayList<IMCAnimatedEntity>();

	public AnimTickHandler() {
		FMLCommonHandler.instance().bus().register(this);
	}

	public void addEntity(IMCAnimatedEntity entity) {
		activeEntities.add(entity);
	}

	//Called when the client ticks. 
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if(!activeEntities.isEmpty()) {
			if(event.phase == Phase.START) {
				try {
					for(IMCAnimatedEntity entity : activeEntities) {
						AnimationHandler animHandler = entity.getAnimationHandler();
						if(animHandler != null) {
							animHandler.animationsUpdate();
						}
		
						if(((Entity)entity).isDead) {
							removableEntities.add(entity);
						}
					}
		
					for(IMCAnimatedEntity entity : removableEntities) {
						activeEntities.remove(entity);
					}
					removableEntities.clear();
				} catch (ConcurrentModificationException e) {
					
				}
			}
		}
	}

	//Called when the server ticks. Usually 20 ticks a second. 
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {
		if(!activeEntities.isEmpty()) {
			if(event.phase == Phase.START) {
				try {
					for(IMCAnimatedEntity entity : activeEntities) {
						AnimationHandler animHandler = entity.getAnimationHandler();
						if(animHandler != null) {
							animHandler.animationsUpdate();
						}
	
						if(((Entity)entity).isDead) {
							removableEntities.add(entity);
						}
					}
	
					for(IMCAnimatedEntity entity : removableEntities) {
						activeEntities.remove(entity);
					}
					removableEntities.clear();
				} catch(ConcurrentModificationException e) {
					
				}
			}
		}
	}

	//Called when a new frame is displayed (See fps) 
	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event) {
	}

	//Called when the world ticks
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {
	}
}
