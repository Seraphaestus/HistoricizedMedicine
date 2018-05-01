package seraphaestus.historicizedmedicine.Mob;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import seraphaestus.historicizedmedicine.HMedicineMod;
import seraphaestus.historicizedmedicine.Util.Pair;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;

public class VillagerProfessions {

	public static final Set<VillagerProfession> PROFESSIONS = new HashSet<>();
	
	public static VillagerProfession PlagueDoctorProfession;
	private static final Item honey = Item.getByNameOrId(HMedicineMod.MODID + ":honey");
	private static final Item herbalPoultice = Item.getByNameOrId(HMedicineMod.MODID + "herbal_poultice");
	
	@Mod.EventBusSubscriber(modid = HMedicineMod.MODID)
	public static class RegistrationHandler {
		
		@SubscribeEvent
		public static void registerProfessions(final RegistryEvent.Register<VillagerProfession> event) {
			final IForgeRegistry<VillagerProfession> registry = event.getRegistry();
			PlagueDoctorProfession = new VillagerProfession(HMedicineMod.MODID + ":plaguedoctor", HMedicineMod.MODID + ":textures/entity/plaguedoctor.png", HMedicineMod.MODID + ":textures/entity/zombieplaguedoctor.png");
			
			registry.register(PlagueDoctorProfession);
			new VillagerCareer(PlagueDoctorProfession, HMedicineMod.MODID + ".plaguedoctor")
			.addTrade(1, trade(honey, 4, price(1,3)))
			.addTrade(1, trade(herbalPoultice, 1, price(1,3)));
			PROFESSIONS.add(PlagueDoctorProfession);
		}
	}
	
	private static Pair<Integer, Integer> price(int x, int y){
		return new Pair<Integer, Integer>(x, y);
	}
	private static EntityVillager.ListItemForEmeralds trade(Item item, int amount, int meta, int minEm, int maxEm){
		return new EntityVillager.ListItemForEmeralds(new ItemStack(item, amount, meta), new EntityVillager.PriceInfo(minEm, maxEm));
	}
	private static EntityVillager.ListItemForEmeralds trade(Item item, int amount, Pair<Integer, Integer> price){
		return trade(item, amount, 0, price.x, price.y);
	}	
}


