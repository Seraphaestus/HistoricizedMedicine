package seraphaestus.historicizedmedicine.Recipe;

import com.google.gson.JsonObject;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import seraphaestus.historicizedmedicine.Config;

import java.util.function.BooleanSupplier;

public class RecipeToggleHandler implements IConditionFactory {

	@Override
	public BooleanSupplier parse(JsonContext context, JsonObject json) {
		return () -> this.enableRecipes();
	}

	public boolean enableRecipes() {
		return Config.enableRecipes;
	}

}