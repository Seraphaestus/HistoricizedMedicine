package seraphaestus.historicizedmedicine.Compat;

import mezz.jei.transfer.BasicRecipeTransferInfo;
import seraphaestus.historicizedmedicine.CraftingTable.CraftingTableContainer;

public class HMedRecipeTransfer extends BasicRecipeTransferInfo<CraftingTableContainer> {

	public HMedRecipeTransfer(Class<CraftingTableContainer> containerClass, String recipeCategoryUid, int recipeSlotStart, int recipeSlotCount,
			int inventorySlotStart, int inventorySlotCount) {
		super(containerClass, recipeCategoryUid, recipeSlotStart, recipeSlotCount, inventorySlotStart, inventorySlotCount);
	}
	public HMedRecipeTransfer() {
		this(CraftingTableContainer.class, HMedRecipeCategory.uid, 0, 10, 10, 9 * 4);
	}
	

}
