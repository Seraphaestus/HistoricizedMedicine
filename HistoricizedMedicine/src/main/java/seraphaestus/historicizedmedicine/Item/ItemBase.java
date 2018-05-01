package seraphaestus.historicizedmedicine.Item;



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
    String oreDictName = null;

    public ItemBase(String id){
        this.id = id;
        this.name = HMedicineMod.MODID + "." + id;
        if(Config.enableCreativeTab) {
        	setCreativeTab(HMedicineMod.creativeTab);
        }
    }
    public ItemBase(String id, int stackSize){
        this(id);
        this.stackSize = stackSize;
    }
    public ItemBase(String id, int stackSize, String oreDictName){
        this(id, stackSize);
        this.oreDictName = oreDictName;
    }
    
    public void init() {
    	if(oreDictName != null) {
    		OreDictionary.registerOre(oreDictName, this);
    	}
    }
}
