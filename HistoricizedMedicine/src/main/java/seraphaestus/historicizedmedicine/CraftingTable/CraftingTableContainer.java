package seraphaestus.historicizedmedicine.CraftingTable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import seraphaestus.historicizedmedicine.HMedicineMod;

public class CraftingTableContainer extends Container{
	
	private CraftingTableTileEntity te; public CraftingTableTileEntity getTE() { return te; }
	
	public CraftingTableContainer(IInventory playerInventory, CraftingTableTileEntity te) {
		this.te = te;
		
		addTableSlots();
		addPlayerSlots(playerInventory);
	}
	
	private void addPlayerSlots(IInventory playerInventory) {
		// Slots for the main inventory
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = 8 + col * 18;
                int y = row * 18 + 72 + 12;
                this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 9, x, y));
            }
        }

        // Slots for the hotbar
        for (int row = 0; row < 9; ++row) {
            int x = 8 + row * 18;
            int y = 60 + 70 + 12;
            this.addSlotToContainer(new Slot(playerInventory, row, x, y));
        }	
	}
	
	private void addTableSlots() {
        IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        int xInitial = 30;
        int x = 0;
        int y = 5 + 12;

        // Add our own slots
        int slotIndex = 0;
        for (int i = 0; i < 3; i++) {
        	 for (int ii = 0; ii < 3; ii++) {
                 addSlotToContainer(new SlotItemHandler(itemHandler, slotIndex, xInitial + x, y));
                 slotIndex++;
                 x += 18;
             }
        	 x = 0;
        	 y += 18;
        }      
	 	//add knowledge sheet input slot
	 	addSlotToContainer(new SlotKnowledge(itemHandler, slotIndex, xInitial + 13 * 9 + 4, 5 + 2 * 18 + 12));
	 	slotIndex++;
   	 	//add crafting output slot
	 	addSlotToContainer(new SlotUninsertable(itemHandler, slotIndex, xInitial + 5 * 18 + 4, 5 + 18 + 12));
	 	slotIndex++;
    }
	 
	@Nullable
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = null;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < CraftingTableTileEntity.size) {
                if (!this.mergeItemStack(itemstack1, CraftingTableTileEntity.size, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, CraftingTableTileEntity.size, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        } else {
        	return ItemStack.EMPTY; //changed
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return te.canInteractWith(playerIn);
    }
	
    private class SlotUninsertable extends SlotItemHandler {

		public SlotUninsertable(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
			super(itemHandler, index, xPosition, yPosition);
		}
		
		@Override
	    public boolean isItemValid(@Nonnull ItemStack stack)
	    {
			return false;
	    }
    }
    
    private class SlotKnowledge extends SlotItemHandler {

		public SlotKnowledge(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
			super(itemHandler, index, xPosition, yPosition);
		}
		
		@Override
	    public boolean isItemValid(@Nonnull ItemStack stack)
	    {
			final String[] sheets = {"ebers_papyrus", "hippocratic_corpus", "canon_of_medicine", "chirurgia_magna"};
			String stackId = stack.getItem().getRegistryName().toString();
			for(String s : sheets) {
				if(stackId.contentEquals(HMedicineMod.MODID + ":" + s)) return true;	
			}
			return false;
	    }
    }
}
