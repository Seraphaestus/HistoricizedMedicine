package seraphaestus.historicizedmedicine.CraftingTable;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class Recipe implements IRecipe {

	public NBTTagCompound[][] grid = new NBTTagCompound[3][3];
	public ItemStack output;
	public Item requiredSheet = null;
	private ResourceLocation recipeRegistryName;

	public Recipe() {
		for (int i = 0; i < 3; i++) {
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

	public static Recipe getFromJson(String contents) {
		try {
			NBTTagCompound nbt = JsonToNBT.getTagFromJson(contents);
			Recipe output = new Recipe();
			NBTTagCompound outputTag = nbt.getCompoundTag("result");
			output.output = new ItemStack(Item.getByNameOrId(outputTag.getString("item")), outputTag.getInteger("amount"));
			output.requiredSheet = Item.getByNameOrId(nbt.getString("knowledge"));
			for (int i = 0; i < 3; i++) {
				output.grid[0][i] = nbt.getCompoundTag("top" + (i + 1));
				output.grid[1][i] = nbt.getCompoundTag("mid" + (i + 1));
				output.grid[2][i] = nbt.getCompoundTag("low" + (i + 1));
			}
			return output;

		} catch (NBTException e) {
			e.printStackTrace();
		}
		return null;
	}

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

	/*public static Recipe getFromJson(String contents) {
		try {
			Recipe output = new Recipe();
			NBTTagCompound nbt = JsonToNBT.getTagFromJson(contents);
			String s = nbt.getCompoundTag("result").getString("item");
			if(s == "historicizedmedicine:honey_poultice") {
				int jetsetset = 1;
				int alkd = jetsetset;
			}
			NBTTagCompound keys = nbt.getCompoundTag("key");
			HashMap<String, NBTTagCompound> keyMap = new HashMap<String, NBTTagCompound>();
			for(String key : nbt.getKeySet()) {
				keyMap.put(key, keys.getCompoundTag(key));
			}
			NBTTagCompound patternNbt = nbt.getCompoundTag("pattern");
			String[] recipeKeyNBT = new String[] {patternNbt.getString("0"), patternNbt.getString("1"), patternNbt.getString("2")};
			for(int i = 0; i < 3; i++) {
				char[] patternCharArr = recipeKeyNBT[i].toCharArray();
				for(int ii = 0; ii < 3 && ii < patternCharArr.length; ii++) {
					output.grid[i][ii] = keyMap.get(patternCharArr[ii]);
				}
			}
			NBTTagCompound outputTag = nbt.getCompoundTag("result");
			output.output = new ItemStack(Item.getByNameOrId(outputTag.getString("item")), outputTag.getInteger("amount"));
			output.requiredSheet = Item.getByNameOrId(nbt.getString("knowledge"));
			return output;
			
		} catch (NBTException e) {
			e.printStackTrace();
		}
		return null;
	}*/
}
