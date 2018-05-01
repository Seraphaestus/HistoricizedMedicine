package seraphaestus.historicizedmedicine;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictRegister {
	
	private static String meatName = "itemMeat";
	private static List<Item> meats = new ArrayList<Item>();
	
	public static void initializeConstants() {
		meats.add(Item.getByNameOrId("beef"));
		meats.add(Item.getByNameOrId("cooked_beef"));
		meats.add(Item.getByNameOrId("chicken"));
		meats.add(Item.getByNameOrId("cooked_chicken"));
		meats.add(Item.getByNameOrId("mutton"));
		meats.add(Item.getByNameOrId("cooked_mutton"));
		meats.add(Item.getByNameOrId("porkchop"));
		meats.add(Item.getByNameOrId("cooked_porkchop"));
		meats.add(Item.getByNameOrId("rabbit"));
		meats.add(Item.getByNameOrId("cooked_rabbit"));
	}
	
	public static void initOreDict() {
		initializeConstants();
		for(Item i : meats) {
			OreDictionary.registerOre(meatName, i);
		}
	}
	
}
