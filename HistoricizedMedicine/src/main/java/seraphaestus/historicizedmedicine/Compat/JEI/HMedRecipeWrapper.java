package seraphaestus.historicizedmedicine.Compat.JEI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import seraphaestus.historicizedmedicine.CraftingTable.Recipe;

public class HMedRecipeWrapper implements IRecipeWrapper {

	public final List<List<ItemStack>> inputs = new ArrayList<List<ItemStack>>();
	public final ItemStack output;
	public final ItemStack knowledge;
	
	public HMedRecipeWrapper(Recipe recipe) {
		for(int i = 0; i < 3; i++) {
			for(int ii = 0; ii < 3; ii++) {
				NBTTagCompound nbt = recipe.grid[i][ii];
				String name = nbt.getString("item");
				String ore = nbt.getString("ore");
				if(name != "") {
					int meta = nbt.getInteger("meta");
					inputs.add(Collections.singletonList(new ItemStack(Item.getByNameOrId(name), 1, meta)));
				} else
				if(ore != "") {
					List<ItemStack> ores = OreDictionary.getOres(ore);
					inputs.add(ores);
				} else {
					inputs.add(null);
				}
			}
		}
		output = recipe.output;
		knowledge = new ItemStack(recipe.requiredSheet);
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		List<List<ItemStack>> newInputs = this.inputs;
		newInputs.add(Collections.singletonList(this.knowledge));
		ingredients.setInputLists(ItemStack.class, newInputs);
		ingredients.setOutput(ItemStack.class, this.output);
	}
	
	public ItemStack getKnowledge() {
		return this.knowledge;
	}

}
