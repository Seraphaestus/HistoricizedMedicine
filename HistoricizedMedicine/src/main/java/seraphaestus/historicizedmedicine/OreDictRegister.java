package seraphaestus.historicizedmedicine;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictRegister {
	
	private static String meatName = "itemMeatRaw";
	private static List<Item> meats = new ArrayList<Item>();
	
	public static void initializeConstants() {
		meats.add(Item.getByNameOrId("beef"));
		meats.add(Item.getByNameOrId("chicken"));
		meats.add(Item.getByNameOrId("mutton"));
		meats.add(Item.getByNameOrId("porkchop"));
		meats.add(Item.getByNameOrId("rabbit"));
	}
	
	public static void initOreDict() {
		initializeConstants();
		for(Item i : meats) {
			OreDictionary.registerOre(meatName, i);
		}
	}
	
}
