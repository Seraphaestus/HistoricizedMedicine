package seraphaestus.historicizedmedicine.Compat;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import seraphaestus.historicizedmedicine.Config;
import seraphaestus.historicizedmedicine.HMedicineMod;
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
		
		registry.addRecipes(getRecipes(), HMedRecipeCategory.uid);
		
		if(Config.implementHoney) {
			registry.addIngredientInfo(new ItemStack(Item.getByNameOrId(HMedicineMod.MODID + ":honey")), ItemStack.class, "Obtain via trading with Apothecarian villagers");
		}
		registry.addIngredientInfo(new ItemStack(Item.getByNameOrId(HMedicineMod.MODID + ":leech")), ItemStack.class, "Obtain via trading with Plague Doctor villagers");
		registry.addIngredientInfo(new ItemStack(Item.getByNameOrId(HMedicineMod.MODID + ":urine")), ItemStack.class, "Obtain by using the sample glass");
		
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
	
	private final String dir = "/src/main/resources/assets/historicizedmedicine/HMedCraftingTableRecipes";
	private List<Recipe> getRecipes() {
		File base = new File(System.getProperty("user.dir"));
		base = base.getParentFile();
		File dir = new File(base.toString() + this.dir);
		File[] directoryListing = dir.listFiles();
		return getRecipes(directoryListing);
	}
	private List<Recipe> getRecipes(File[] directoryListing) {
		List<Recipe> output = new ArrayList<Recipe>();
		if (directoryListing != null) {
		    for (File child : directoryListing) {
		    	File[] childDir = child.listFiles();
				if(childDir != null) {
					output.addAll(getRecipes(childDir));
				} else {
					output.add(getFromJson(child));
				}
		    }		    
	    }
		return output;
	}	
	private Recipe getFromJson(File file) {
		Charset chr = null;
		String contents;
		try {
			contents = FileUtils.readFileToString(file, chr);
			NBTTagCompound nbt = JsonToNBT.getTagFromJson(contents);
			Recipe output = new Recipe();
			NBTTagCompound outputTag = nbt.getCompoundTag("result");
			output.output = new ItemStack(Item.getByNameOrId(outputTag.getString("item")), outputTag.getInteger("amount"));
			output.requiredSheet = Item.getByNameOrId(nbt.getString("knowledge"));
			for(int i = 0; i < 3; i++) {
				output.grid[0][i] = nbt.getCompoundTag("top" + (i + 1));
				output.grid[1][i] = nbt.getCompoundTag("mid" + (i + 1));
				output.grid[2][i] = nbt.getCompoundTag("low" + (i + 1));
			}
			return output;

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NBTException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
