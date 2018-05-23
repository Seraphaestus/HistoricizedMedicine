package seraphaestus.historicizedmedicine.Compat.JEI;

import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.inventory.Slot;
import seraphaestus.historicizedmedicine.CraftingTable.CraftingTableContainer;

import java.util.ArrayList;
import java.util.List;

public class HMedRecipeTransfer implements IRecipeTransferInfo<CraftingTableContainer> {

	private final Class<CraftingTableContainer> containerClass;
	private final String recipeCategoryUid;
	private final int recipeSlotStart;
	private final int recipeSlotCount;
	private final int inventorySlotStart;
	private final int inventorySlotCount;

	public HMedRecipeTransfer(Class<CraftingTableContainer> containerClass, String recipeCategoryUid, int recipeSlotStart, int recipeSlotCount,
	                          int inventorySlotStart, int inventorySlotCount) {
		this.containerClass = containerClass;
		this.recipeCategoryUid = recipeCategoryUid;
		this.recipeSlotStart = recipeSlotStart;
		this.recipeSlotCount = recipeSlotCount;
		this.inventorySlotStart = inventorySlotStart;
		this.inventorySlotCount = inventorySlotCount;
	}

	public HMedRecipeTransfer() {
		this(CraftingTableContainer.class, HMedRecipeCategory.uid, 0, 10, 10, 9 * 4);
	}

	@Override
	public Class<CraftingTableContainer> getContainerClass() {
		return containerClass;
	}

	@Override
	public String getRecipeCategoryUid() {
		return recipeCategoryUid;
	}

	@Override
	public boolean canHandle(CraftingTableContainer container) {
		return true;
	}

	@Override
	public List<Slot> getRecipeSlots(CraftingTableContainer container) {
		List<Slot> slots = new ArrayList<>();
		for (int i = recipeSlotStart; i < recipeSlotStart + recipeSlotCount; i++) {
			Slot slot = container.getSlot(i);
			slots.add(slot);
		}
		return slots;
	}

	@Override
	public List<Slot> getInventorySlots(CraftingTableContainer container) {
		List<Slot> slots = new ArrayList<>();
		for (int i = inventorySlotStart; i < inventorySlotStart + inventorySlotCount; i++) {
			Slot slot = container.getSlot(i);
			slots.add(slot);
		}
		return slots;
	}


}
