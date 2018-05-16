package seraphaestus.historicizedmedicine.Compat.JEI;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import seraphaestus.historicizedmedicine.CraftingTable.Recipe;

@SuppressWarnings("deprecation")
public class HMedRecipeHandler implements IRecipeHandler<Recipe>{

	@Override
	public Class<Recipe> getRecipeClass() {
		return Recipe.class;
	}

	@Override
	public String getRecipeCategoryUid(Recipe recipe) {
		return HMedRecipeCategory.uid;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(Recipe recipe) {
		return new HMedRecipeWrapper(recipe);
	}

	@Override
	public boolean isRecipeValid(Recipe recipe) {
		if (recipe.getRecipeOutput() == null) {
			//Recipe has no outputs
			return false;
		}
		int inputCount = 0;
		for(int i = 0; i < 3; i++) {
			for(int ii = 0; ii < 3; ii++) {
				if(recipe.grid[i][ii].getString("item") != "" || recipe.grid[i][ii].getString("ore") != "") {
					inputCount++;
				}
			}
		}
		if (inputCount > 9) {
			//Recipe has too many inputs
			return false;
		}
		if (inputCount == 0) {
			//Recipe has no inputs
			return false;
		}
		return true;
	}

}
