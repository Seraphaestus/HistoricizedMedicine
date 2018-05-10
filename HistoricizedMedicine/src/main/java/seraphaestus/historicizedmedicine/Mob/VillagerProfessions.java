package seraphaestus.historicizedmedicine.Mob;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import net.minecraftforge.registries.IForgeRegistry;
import seraphaestus.historicizedmedicine.Config;
import seraphaestus.historicizedmedicine.HMedicineMod;
import seraphaestus.historicizedmedicine.Util.Pair;

public class VillagerProfessions {

	public static final Set<VillagerProfession> PROFESSIONS = new HashSet<>();
	
	public static VillagerProfession PlagueDoctorProfession;
	public static VillagerProfession ApothecarianProfession;
	private static final Item herbalPoultice = Item.getByNameOrId(HMedicineMod.MODID + ":herbal_poultice");
	private static final Item herbs = Item.getByNameOrId(HMedicineMod.MODID + ":herbs");
	private static final Item leech = Item.getByNameOrId(HMedicineMod.MODID + ":leech");
	private static final Item cautery = Item.getByNameOrId(HMedicineMod.MODID + ":cautery");
	private static final Item razor = Item.getByNameOrId(HMedicineMod.MODID + ":razor");
	private static final Item wardCharm = Item.getByNameOrId(HMedicineMod.MODID + ":ward_charm");
	private static final ItemStack allium = new ItemStack(Item.getByNameOrId("minecraft:red_flower"), 1, 2);
	private static final ItemStack orchid = new ItemStack(Item.getByNameOrId("minecraft:red_flower"), 1, 1);
	private static final Item wart = Item.getByNameOrId("minecraft:nether_wart");
	private static final Item foot = Item.getByNameOrId("minecraft:rabbit_foot");
	
	@Mod.EventBusSubscriber(modid = HMedicineMod.MODID)
	public static class RegistrationHandler {
		
		@SubscribeEvent
		public static void registerProfessions(final RegistryEvent.Register<VillagerProfession> event) {
			final IForgeRegistry<VillagerProfession> registry = event.getRegistry();
			PlagueDoctorProfession = new VillagerProfession(HMedicineMod.MODID + ":plaguedoctor", HMedicineMod.MODID + ":textures/entity/plaguedoctor.png", HMedicineMod.MODID + ":textures/entity/zombieplaguedoctor.png");
			
			registry.register(PlagueDoctorProfession);
			new VillagerCareer(PlagueDoctorProfession, HMedicineMod.MODID + ".plaguedoctor")
			.addTrade(1, trade(leech, 1, price(3,9)))
			.addTrade(2, trade(cautery, 1, price(6,18)))
			.addTrade(2, trade(razor, 1, price(6,18)))
			.addTrade(2, trade(wardCharm, 1, price(1,64)))
			.addTrade(1, trade(wart, 8, price(2,6)));
			PROFESSIONS.add(PlagueDoctorProfession);
			
			ApothecarianProfession = new VillagerProfession(HMedicineMod.MODID + ":apothecarian", HMedicineMod.MODID + ":textures/entity/apothecarian.png", HMedicineMod.MODID + ":textures/entity/zombieapothecarian.png");
			
			Item honey;
			if(Config.implementHoney) {
				honey = Item.getByNameOrId(HMedicineMod.MODID + ":honey");
			} else {
				honey = Item.getByNameOrId("minecraft:sugar");
			}
			registry.register(ApothecarianProfession);
			new VillagerCareer(ApothecarianProfession, HMedicineMod.MODID + ".apothecarian")
			.addTrade(1, trade(herbs, 4, price(1,8)))
			.addTrade(1, trade(honey, 4, price(2,12)))
			.addTrade(1, trade(herbalPoultice, 1, price(1,3)))
			.addTrade(2, trade(allium, 3, price(1,3)))
			.addTrade(2, trade(orchid, 3, price(1,3)))
			.addTrade(3, trade(foot, 1, price(6,24)));
			PROFESSIONS.add(ApothecarianProfession);
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
	private static EntityVillager.ListItemForEmeralds trade(ItemStack item, int amount, Pair<Integer, Integer> price){
		return trade(item.getItem(), amount, item.getMetadata(), price.x, price.y);
	}

}


