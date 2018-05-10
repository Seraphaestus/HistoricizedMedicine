package seraphaestus.historicizedmedicine.CraftingTable;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class Recipe implements IRecipe {
	
	public NBTTagCompound[][] grid = new NBTTagCompound[3][3];
	public ItemStack output;
	public Item requiredSheet = null;
	
	public Recipe() {
		for(int i = 0; i < 3; i++) {
			for (int ii = 0; ii < 3; ii++) {
				grid[i][ii] = null;
			}
		}
	}
	
	public Recipe(NBTTagCompound[][] grid, ItemStack output) {
		this.grid = grid;
		this.output = output;
	}
	
	public Recipe(NBTTagCompound[][] grid, ItemStack output, Item requiredSheet) {
		this(grid, output);
		this.requiredSheet = requiredSheet;
	}

	private ResourceLocation recipeRegistryName;
	
	@Override
	public IRecipe setRegistryName(ResourceLocation name) {
		this.recipeRegistryName = name;
		return this;
	}

	@Override
	public ResourceLocation getRegistryName() {
		return recipeRegistryName;
	}

	@Override
	public Class<IRecipe> getRegistryType() {
		return null;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return null;
	}

	@Override
	public boolean canFit(int width, int height) {
		return width == 3 && height == 3;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.output;
	}
}
