package seraphaestus.historicizedmedicine;

import java.util.function.BooleanSupplier;

import com.google.gson.JsonObject;

import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

public class RecipeToggleHandler implements IConditionFactory {

	@Override
	public BooleanSupplier parse(JsonContext context, JsonObject json) {
		return () -> this.enableRecipe();
	}
	
	public boolean enableRecipe() {
		return Config.enableRecipes;
	}

}
