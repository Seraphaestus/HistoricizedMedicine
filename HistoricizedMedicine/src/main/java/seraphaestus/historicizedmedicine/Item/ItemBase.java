package seraphaestus.historicizedmedicine.Item;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import seraphaestus.historicizedmedicine.Config;
import seraphaestus.historicizedmedicine.HMedicineMod;

public class ItemBase extends Item {

    String id;
    String name;
    int stackSize = 64;

    public ItemBase(String id, String name){
        this.id = id;
        this.name = name;
        if(Config.enableCreativeTab) {
        	setCreativeTab(HMedicineMod.creativeTab);
        }
    }
    public ItemBase(String id, String name, int stackSize){
        this(id, name);
        this.stackSize = stackSize;
    }
    public ItemBase(String id, String name, int stackSize, String oreDictName){
        this(id, name, stackSize);
        OreDictionary.registerOre(oreDictName, this);
    }
}
