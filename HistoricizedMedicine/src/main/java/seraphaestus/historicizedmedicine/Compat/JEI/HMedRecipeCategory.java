package seraphaestus.historicizedmedicine.Compat.JEI;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.util.ResourceLocation;
import seraphaestus.historicizedmedicine.HMedicineMod;

public class HMedRecipeCategory implements IRecipeCategory<HMedRecipeWrapper> {

	public final static String uid = "HMedCrafting";
	public static final int width = 116;
	public static final int height = 54;
	private static final int craftOutputSlot = 10;
	private static final int craftInputSlot1 = 0;
	private static final int craftKnowledgeSlot = 9;
	private final String title = "Medical Crafting";
	private final IDrawable background;

	public HMedRecipeCategory(IGuiHelper guiHelper) {
		ResourceLocation location = new ResourceLocation(HMedicineMod.MODID, "textures/gui/crafting.png");
		background = guiHelper.createDrawable(location, 29, 16, width, height);
	}

	@Override
	public String getUid() {
		return this.uid;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getModName() {
		return HMedicineMod.MODID;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, HMedRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(craftOutputSlot, false, 94, 18);

		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 3; ++x) {
				int index = craftInputSlot1 + x + (y * 3);
				guiItemStacks.init(index, true, x * 18, y * 18);
			}
		}

		recipeLayout.setRecipeTransferButton(95, 40);

		guiItemStacks.init(craftKnowledgeSlot, true, 120, 36);

		HMedRecipeWrapper hMedRecipeWrapper = recipeWrapper;
		for (int i = 0; i < 9; i++) {
			recipeLayout.getItemStacks().set(craftInputSlot1 + i, hMedRecipeWrapper.inputs.get(i));
		}
		recipeLayout.getItemStacks().set(craftOutputSlot, hMedRecipeWrapper.output);
		recipeLayout.getItemStacks().set(craftKnowledgeSlot, hMedRecipeWrapper.knowledge);
	}


}
