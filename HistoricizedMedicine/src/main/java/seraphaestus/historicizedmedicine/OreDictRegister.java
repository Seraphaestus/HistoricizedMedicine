package seraphaestus.historicizedmedicine;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class OreDictRegister {

	private static String meatName = "listAllmeatraw";
	private static String honeyName = "itemHoney";

	private static List<ItemStack> meats = new ArrayList<ItemStack>();
	private static List<ItemStack> honey = new ArrayList<ItemStack>();

	public static void initializeConstants() {
		meats.add(new ItemStack(Item.getByNameOrId("minecraft:beef")));
		meats.add(new ItemStack(Item.getByNameOrId("minecraft:chicken")));
		meats.add(new ItemStack(Item.getByNameOrId("minecraft:mutton")));
		meats.add(new ItemStack(Item.getByNameOrId("minecraft:porkchop")));
		meats.add(new ItemStack(Item.getByNameOrId("minecraft:rabbit")));
		if (Loader.isModLoaded("primitivemobs")) {
			meats.add(new ItemStack(Item.getByNameOrId("primitivemobs:dodo")));
		}
		if (Loader.isModLoaded("animalium")) {
			meats.add(new ItemStack(Item.getByNameOrId("animalium:rat_meat")));
			meats.add(new ItemStack(Item.getByNameOrId("animalium:bear_meat")));
		}

		if (Config.implementHoney) {
			honey.add(new ItemStack(Item.getByNameOrId("historicizedMedicine:honey")));
		}
		if (Loader.isModLoaded("harvestcraft")) {
			honey.add(new ItemStack(Item.getByNameOrId("harvestcraft:honeyitem")));
		}
		if (Loader.isModLoaded("biomesoplenty")) {
			honey.add(new ItemStack(Item.getByNameOrId("biomesoplenty:filled_honeycomb")));
		}
		if (Loader.isModLoaded("rustic")) {
			honey.add(new ItemStack(Item.getByNameOrId("rustic:honeycomb")));
		}
		if (Loader.isModLoaded("forestry")) {
			honey.add(new ItemStack(Item.getByNameOrId("forestry:honey_drop")));
		}
		if (Loader.isModLoaded("erebus")) {
			honey.add(new ItemStack(Item.getByNameOrId("erebus:materials"), 1, 20));
		}
	}

	public static void initOreDict() {
		initializeConstants();
		for (ItemStack i : meats) {
			OreDictionary.registerOre(meatName, i);
		}
		for (ItemStack i : honey) {
			OreDictionary.registerOre(honeyName, i);
		}
	}

}
