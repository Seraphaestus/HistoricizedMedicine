package seraphaestus.historicizedmedicine.CraftingTable;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.oredict.OreDictionary;
import seraphaestus.historicizedmedicine.Config;
import seraphaestus.historicizedmedicine.Item.KnowledgeSheet;

public class CraftingTableTileEntity extends TileEntity{

	public final static int size = 11;
	final static int outputSlot = 10;
	final static int knowledgeSlot = 9;
	private final String dir = "/src/main/resources/assets/historicizedmedicine/HMedCraftingTableRecipes";
	
	
	//handler for crafting table slots + output + knowledge input
	UnrecursiveItemStackHandler itemStackHandlerMain = new UnrecursiveItemStackHandler(size) {
		
		@Override
		protected void onContentsChanged(int slot) {
			goThroughRecipes();
			if(slot == outputSlot) {
				//removed from output
				if(Config.craftingConsumesKnowledge) {
					ItemStack is = itemStackHandlerMain.getStackInSlot(knowledgeSlot).copy();
					KnowledgeSheet ks = (KnowledgeSheet)is.getItem();
					ks.takeKnowledge(is);
					itemStackHandlerMain.setStackInSlot(knowledgeSlot, is, false);
				}
				for(int i = 0; i < 9; i++) {
					ItemStack is = itemStackHandlerMain.getStackInSlot(i);
					if(is.getCount() <= 1) {
						itemStackHandlerMain.setStackInSlot(i, ItemStack.EMPTY, false);
					} else {
						itemStackHandlerMain.setStackInSlot(i, new ItemStack(is.getItem(),is.getCount() - 1), false);
					}
				}
				goThroughRecipes();

			}
			CraftingTableTileEntity.this.markDirty();
		}
	};
	
	private boolean goThroughRecipes() {
		File base = new File(System.getProperty("user.dir"));
		base = base.getParentFile();
		File dir = new File(base.toString() + this.dir);
		File[] directoryListing = dir.listFiles();
		return goThroughRecipes(directoryListing);
	}
	private boolean goThroughRecipes(File[] directoryListing) {
		if (directoryListing != null) {
		    for (File child : directoryListing) {
		    	if(verifyIfRecipe(child)) {
		    		return true;
		    	}
		    }		    
	    }
		return false;
	}
	
	private boolean verifyIfRecipe(File f) {
		File[] directoryListing = f.listFiles();
		if(directoryListing != null) {
			return goThroughRecipes(directoryListing);
		} else {
			Recipe r = getFromJson(f);
	    	//reset output slotstack
	    	itemStackHandlerMain.setStackInSlot(outputSlot, ItemStack.EMPTY, false);
			if(itemCheck(r.grid[0][0], 0) &&
			   itemCheck(r.grid[0][1], 1) &&
			   itemCheck(r.grid[0][2], 2) &&
			   itemCheck(r.grid[1][0], 3) &&
			   itemCheck(r.grid[1][1], 4) &&
			   itemCheck(r.grid[1][2], 5) &&
		       itemCheck(r.grid[2][0], 6) &&
		       itemCheck(r.grid[2][1], 7) &&
		       itemCheck(r.grid[2][2], 8)) {
				//on match
				boolean checkKnowledge = false;
				if(r.requiredSheet == null) {
					checkKnowledge = true;
				} else {
					ItemStack sheet = itemStackHandlerMain.getStackInSlot(knowledgeSlot);
					if(!sheet.isEmpty()) {
						boolean knowledgeSheetCheck;
						if(Config.requireExactTier) {
							knowledgeSheetCheck = sheet.getItem().getRegistryName() == r.requiredSheet.getRegistryName();
						} else {		
							KnowledgeSheet ks = (KnowledgeSheet)sheet.getItem();
							KnowledgeSheet ks2 = (KnowledgeSheet)r.requiredSheet;
							knowledgeSheetCheck = ks.getTier() >= ks2.getTier();
						}
						if(knowledgeSheetCheck) {			
							KnowledgeSheet knSheet = (KnowledgeSheet)sheet.getItem();
							if (Config.fullKnowledgeRequired == false) {
								if(!knSheet.isEmpty(sheet)) {
									checkKnowledge = true;
								}
							} else {
								if(knSheet.isFull(sheet)) {
									checkKnowledge = true;
								}
							}
						}
					}
				}
				if(r.requiredSheet == null || checkKnowledge) {
					itemStackHandlerMain.setStackInSlot(outputSlot, r.output, false);
					return true;
				}
			}
		}
		return false;
	}
	
	/*private boolean verifyIfRecipe() {
		File base = new File(System.getProperty("user.dir"));
		base = base.getParentFile();
		File dir = new File(base.toString() + "/src/main/resources/assets/historicizedmedicine/recipes/HMedCraftingTable");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
		    for (File child : directoryListing) {
		    	Recipe r = getFromJson(child);
		    	//reset output slotstack
		    	itemStackHandlerMain.setStackInSlot(9, ItemStack.EMPTY, false);
				if(itemCheck(r.grid[0][0], 0) &&
				   itemCheck(r.grid[0][1], 1) &&
				   itemCheck(r.grid[0][2], 2) &&
				   itemCheck(r.grid[1][0], 3) &&
				   itemCheck(r.grid[1][1], 4) &&
				   itemCheck(r.grid[1][2], 5) &&
			       itemCheck(r.grid[2][0], 6) &&
			       itemCheck(r.grid[2][1], 7) &&
			       itemCheck(r.grid[2][2], 8)) {
					//on match
					boolean checkKnowledge = false;
					if(r.requiredSheet == null) {
						checkKnowledge = true;
					} else {
						ItemStack sheet = itemStackHandlerMain.getStackInSlot(10);
						if(!sheet.isEmpty()) {
							if(sheet.getItem().getRegistryName() == r.requiredSheet.getRegistryName()) {			
								KnowledgeSheet knSheet = (KnowledgeSheet)sheet.getItem();
								if (Config.fullKnowledgeRequired == false) {
									if(!knSheet.isEmpty(sheet)) {
										checkKnowledge = true;
									}
								} else {
									if(knSheet.isFull(sheet)) {
										checkKnowledge = true;
									}
								}
							}
						}
					}
					if(r.requiredSheet == null || checkKnowledge) {
						itemStackHandlerMain.setStackInSlot(9, r.output, false);
						return true;
					}
				}
		    }
		}
		
		return false;
	}*/
	
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
	
	private boolean itemCheck (NBTTagCompound nbt, int slot) {
		try {
		ItemStack isCheck = itemStackHandlerMain.getStackInSlot(slot).copy();

		if(isCheck.isEmpty() && nbt.hasNoTags()) return true;
		if(isCheck.isEmpty() || nbt.hasNoTags()) return false;
		
		Item check = isCheck.getItem();
		
		
		String item = nbt.getString("item");
		if(item != "") {
			Item i = Item.getByNameOrId(item);
			if(i != null) {
				if(nbt.getInteger("meta") != 0) {
					return check.getRegistryName() == i.getRegistryName() && nbt.getInteger("meta") == isCheck.getMetadata();
				} else {
					return check.getRegistryName() == i.getRegistryName();
				}
			}
		}
		String ore = nbt.getString("ore");
		if(ore != "") {
			List<ItemStack> oreMatches = OreDictionary.getOres(ore);
			for(ItemStack is : oreMatches) {
				if(check.getRegistryName() == is.getItem().getRegistryName()) {
					return true;
				}
			}
			return false;
		}
		
		return false;
		}
		catch(Exception e) {
			System.out.println("oh noes");
			return false;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if(compound.hasKey("items")) {
			itemStackHandlerMain.deserializeNBT((NBTTagCompound) compound.getTag("items"));
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
        compound.setTag("items", itemStackHandlerMain.serializeNBT());
        return compound;
	}
	
    public boolean canInteractWith(EntityPlayer playerIn) {
        // If we are too far away from this tile entity you cannot use it
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }
  
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }
    
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandlerMain);
        }
        return super.getCapability(capability, facing);
    }
}
