package seraphaestus.historicizedmedicine.Compat.JEI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import seraphaestus.historicizedmedicine.Config;
import seraphaestus.historicizedmedicine.HMedicineMod;
import seraphaestus.historicizedmedicine.HardCodedValues;
import seraphaestus.historicizedmedicine.ServerProxy;
import seraphaestus.historicizedmedicine.CraftingTable.CraftingTableGUI;
import seraphaestus.historicizedmedicine.CraftingTable.Recipe;

@JEIPlugin
public class JEICompat implements IModPlugin{
	
	@SuppressWarnings("deprecation")
	public void register(IModRegistry registry) {
		IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		
		registry.addRecipeCategories(new HMedRecipeCategory(guiHelper));
		registry.addRecipeHandlers(new HMedRecipeHandler());
		
		registry.addRecipeClickArea(CraftingTableGUI.class, 88, 32, 28, 23, HMedRecipeCategory.uid);
		
		List<Recipe> toAdd = new ArrayList<Recipe>();
		try {
			toAdd = getRecipes();
		} catch(IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
		System.out.println("Adding " + toAdd.size() + " recipes for Medical Crafting table (JEI viewer)");
		registry.addRecipes(toAdd, HMedRecipeCategory.uid);
		
		if(Config.implementHoney) {
			registry.addIngredientInfo(new ItemStack(Item.getByNameOrId(HMedicineMod.MODID + ":honey")), ItemStack.class, "Obtain via trading with Apothecarian villagers");
		}
		registry.addIngredientInfo(new ItemStack(Item.getByNameOrId(HMedicineMod.MODID + ":leech")), ItemStack.class, "Obtain via trading with Plague Doctor villagers");
		registry.addIngredientInfo(new ItemStack(Item.getByNameOrId(HMedicineMod.MODID + ":urine")), ItemStack.class, "Obtain by using the sample glass");
		registry.addIngredientInfo(new ItemStack(Item.getByNameOrId(HMedicineMod.MODID + ":theriac")), ItemStack.class, "Obtain via trading with Apothecarian villagers");
		registry.addIngredientInfo(new ItemStack(Item.getByNameOrId(HMedicineMod.MODID + ":unicorn_powder")), ItemStack.class, "Obtain via trading with Plague Doctor villagers");
		registry.addIngredientInfo(new ItemStack(Item.getByNameOrId(HMedicineMod.MODID + ":plague_mask")), ItemStack.class, "Drops from Plague Doctor villagers");
		
		registry.addRecipeCategoryCraftingItem(new ItemStack(Item.getByNameOrId(HMedicineMod.MODID + ":crafting_table")), HMedRecipeCategory.uid);
		
		/*
		IRecipeTransferRegistry recipeTranferRegistry = registry.getRecipeTransferRegistry();
		//BasicRecipeTransferInfo<CraftingTableContainer> transferInfo = new BasicRecipeTransferInfo<CraftingTableContainer>(CraftingTableContainer.class, HMedRecipeCategory.uid, 0, 10, 10, 9 * 4);
		HMedRecipeTransfer transferInfo = new HMedRecipeTransfer();
		recipeTranferRegistry.addRecipeTransferHandler(transferInfo);
		*/
		
		IIngredientBlacklist ingredientBlacklist = registry.getJeiHelpers().getIngredientBlacklist();
		// Game freezes when loading player skulls, see https://bugs.mojang.com/browse/MC-65587
		ingredientBlacklist.addIngredientToBlacklist(new ItemStack(Items.SKULL, 1, 3));
	}
	
	private List<Recipe> getRecipes() throws IOException{
		List<Recipe> output = new ArrayList<Recipe>();
		
		List<String> files = HardCodedValues.getRecipeFilePaths();

	  	for (String path : files) {
			BufferedReader readIn = new BufferedReader(
					new InputStreamReader(
							getClass().getClassLoader().getResourceAsStream(
									"assets/historicizedmedicine/textures/hmedcraftingtablerecipes/" + path + ".json"),
							"UTF-8"));
			String contents = "";
			String line = "first line, woo";
			while (line != null) {
				line = readIn.readLine();
				if(line != null) {
					contents = contents + line;
				}
			} 
			output.add(Recipe.getFromJson(contents));
		}
	  	output.addAll(ServerProxy.getCustomRecipes());
		return output;
	}

}
