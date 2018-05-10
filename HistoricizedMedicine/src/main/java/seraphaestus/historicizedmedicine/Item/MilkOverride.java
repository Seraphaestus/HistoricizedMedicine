package seraphaestus.historicizedmedicine.Item;

import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MilkOverride {
	
	@SubscribeEvent
	public void useItem(RightClickItem event) {
		if(event.getItemStack().getItem() == Item.getByNameOrId("minecraft:milk_bucket")) {
			event.setCanceled(true);
		}
	}
}
