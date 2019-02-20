package seraphaestus.historicizedmedicine.Item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import seraphaestus.historicizedmedicine.Config;
import seraphaestus.historicizedmedicine.Effect.RegisterEffects;
import seraphaestus.historicizedmedicine.HMedicineMod;
import seraphaestus.historicizedmedicine.Util.Reduce;

import java.util.ArrayList;
import java.util.List;

public class RegistryHandler {

	public static List<IItemBaseData> items; // this holds the unique instance of your block
	public static KnowledgeSheet ebers;
	public static KnowledgeSheet corpus;
	public static KnowledgeSheet canon;
	public static KnowledgeSheet magna;
	private static Potion wither = pId(20);
	private static Potion poison = pId(19);

	public static void setupItems() {
		items = new ArrayList<IItemBaseData>();
		//primitive
		items.add(new ItemBase("herbs", 64, "herbs"));
		items.add(new MedKitTool("trephine", 1, -1, -1, new PotionEffect[]{pain(15), bleed(30, 1)}, new Potion[]{wither}, null, -2, 3));
		items.add(new MedKitFood("medicinal_clay", 8, -1, -1, null, null, null, 1, 1, 1));
		items.add(new MedKitBase("ward_charm", 1, -1, -1, null, null, null, 0));
		items.add(new MedKitBase("herbal_poultice", 8, -1, -1, null, null, new Reduce[]{new Reduce(RegisterEffects.pain, 100)}, 0));

		//egyptian: requires ebers papyrus
		items.add(new MedKitBase("meat_bandage", 64, -1, -1, new PotionEffect[]{infect(30)}, null, new Reduce[]{new Reduce(RegisterEffects.bleeding, 100)}, 0));
		if (Config.implementHoney) {
			items.add(new ItemBase("honey", 64, "itemHoney"));
		}
		items.add(new MedKitBase("honey_poultice", 8, -1, -1, null, null, new Reduce[]{new Reduce(RegisterEffects.pain, 300)}, 0));
		//speed charm (ocelot claw) //made from bone and leather
		//strength charm (wolf fang)
		//resistance charm (eye of horus)
		items.add(new ItemBase("suture", 64));
		items.add(new MedKitBase("ligature", 1, -1, -1, new PotionEffect[]{pain(25)}, null, new Reduce[]{new Reduce(RegisterEffects.bleeding, 100)}, -4));

		//greek/roman: Hippocratic Corpus
		//statues/shrines
		items.add(new MedKitTool("scalpel", 1, -1, 10, new PotionEffect[]{pain(20), infect(20)}, null, null, 5, 10));
		items.add(new MedKitBase("reed_bandage", 64, -1, -1, new PotionEffect[]{infect(10)}, null, new Reduce[]{new Reduce(RegisterEffects.bleeding, 200)}, 0));
		//sulfuric cream (mild antiseptic, antiinflammatory)

		//middle ages: The Canon of Medicine
		items.add(new MedKitBase("leech", 64, -1, -1, new PotionEffect[]{bleed(10)}, new Potion[]{poison}, null, -1));
		items.add(new MedKitTool("cautery", 1, -1, -1, new PotionEffect[]{pain(40), infect(20)}, new Potion[]{RegisterEffects.bleeding}, null, -6, 20));
		items.add(new MedKitTool("razor", 1, -1, -1, new PotionEffect[]{pain(10), infect(20), bleed(30)}, null, null, 10, 5));
		items.add(new ItemBase("theriac", 1));
		items.add(new ItemBase("unicorn_powder", 64));
		items.add(new PlagueCure());
		items.add(new ItemMask());


		//renaissance: Chirurgia Magna
		items.add(new ItemBase("urine", 64));
		items.add(new SampleGlass());
		items.add(new ItemBase("ammonia", 64));
		items.add(new MedKitBase("nitrous_oxide", 64, -1, -1, null, new Potion[]{RegisterEffects.pain}, null, 0));
		//tourniquet
		//sterilisation?
	}

	public static void preInitCommon() {
		// each instance of your item should have a name that is unique within your mod.  use lower case.
		if (items == null) {
			setupItems();
		}
		for (IItemBaseData item : items) {
			if (item instanceof Item) {
				Item item2 = (Item) item;
				ForgeRegistries.ITEMS.register(item2.setRegistryName(item.getId()).setTranslationKey(item.getName()).setMaxStackSize(item.getStackSize()));
			}
			if (item instanceof ItemBase) {
				((ItemBase) item).init();
			}
		}

		//knowledge sheets
		List<ItemBase> sheets = new ArrayList<ItemBase>();
		final Item[] knowledge1 = {iId("trephine")};
		final Item[] knowledge2 = {iId("trephine")};
		final Item[] knowledge3 = {iId("trephine")};
		final Item[] knowledge4 = {iId("trephine")};
		ebers = new KnowledgeSheet("ebers_papyrus", 4, knowledge1, Config.commandRunOnEbersUse, 1);
		corpus = new KnowledgeSheet("hippocratic_corpus", 8, knowledge2, Config.commandRunOnCorpusUse, 2);
		canon = new KnowledgeSheet("canon_of_medicine", 12, knowledge3, Config.commandRunOnCanonUse, 3);
		magna = new KnowledgeSheet("chirurgia_magna", 16, knowledge4, Config.commandRunOnMagnaUse, 4);
		sheets.add(ebers);
		sheets.add(corpus);
		sheets.add(canon);
		sheets.add(magna);
		//register k. sheets
		for (ItemBase item : sheets) {
			ForgeRegistries.ITEMS.register(item.setRegistryName(item.id).setTranslationKey(item.name).setMaxStackSize(item.stackSize));
		}
	}

	public static void preInitClientOnly() {
		// model to be used for rendering this item
		final int DEFAULT_ITEM_SUBTYPE = 0;
		if (items == null) {
			setupItems();
		}
		for (IItemBaseData item : items) {
			ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(HMedicineMod.MODID + ":" + item.getId(), "inventory");
			if (item instanceof Item) {
				Item item2 = (Item) item;
				ModelLoader.setCustomModelResourceLocation(item2, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
			}
		}
		//knowledge sheets
		ModelResourceLocation itemModelResourceLocation;
		itemModelResourceLocation = new ModelResourceLocation(HMedicineMod.MODID + ":ebers_papyrus", "inventory");
		ModelLoader.setCustomModelResourceLocation(Item.getByNameOrId(HMedicineMod.MODID + ":ebers_papyrus"), DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);

		itemModelResourceLocation = new ModelResourceLocation(HMedicineMod.MODID + ":hippocratic_corpus", "inventory");
		ModelLoader.setCustomModelResourceLocation(Item.getByNameOrId(HMedicineMod.MODID + ":hippocratic_corpus"), DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);

		itemModelResourceLocation = new ModelResourceLocation(HMedicineMod.MODID + ":canon_of_medicine", "inventory");
		ModelLoader.setCustomModelResourceLocation(Item.getByNameOrId(HMedicineMod.MODID + ":canon_of_medicine"), DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);

		itemModelResourceLocation = new ModelResourceLocation(HMedicineMod.MODID + ":chirurgia_magna", "inventory");
		ModelLoader.setCustomModelResourceLocation(Item.getByNameOrId(HMedicineMod.MODID + ":chirurgia_magna"), DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);

	}

	public static PotionEffect pain(float d) {
		return new PotionEffect(RegisterEffects.pain, (int) (d * 20));
	}

	public static PotionEffect bleed(float d) {
		return new PotionEffect(RegisterEffects.bleeding, (int) (d * 20));
	}

	public static PotionEffect bleed(float d, int level) {
		return new PotionEffect(RegisterEffects.bleeding, (int) (d * 20), level);
	}

	public static PotionEffect infect(float d) {
		return new PotionEffect(RegisterEffects.infection, (int) (d * 20));
	}

	public static Potion pId(int id) {
		return Potion.getPotionById(id);
	}

	public static Item iId(String id) {
		return Item.getByNameOrId(HMedicineMod.MODID + ":" + id);
	}
}
