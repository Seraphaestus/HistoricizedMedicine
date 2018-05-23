package seraphaestus.historicizedmedicine.CraftingTable;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class UnrecursiveItemStackHandler extends ItemStackHandler {

    public UnrecursiveItemStackHandler(int size) {
		super(size);
	}

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack)
    {
        validateSlotIndex(slot);
        //if (ItemStack.areItemStacksEqual(this.stacks.get(slot), stack))
        //    return;
        this.stacks.set(slot, stack);
        onContentsChanged(slot);
    }
    
	public void setStackInSlot(int slot, @Nonnull ItemStack stack, boolean check)
    {
        validateSlotIndex(slot);
        if (ItemStack.areItemStacksEqual(this.stacks.get(slot), stack)) {
            return;
        }
        this.stacks.set(slot, stack);
    }
}
