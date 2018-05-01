package seraphaestus.historicizedmedicine.Block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;
import seraphaestus.historicizedmedicine.HMedicineMod;

public class BlockBase extends Block {

	public String id;
	private String oreDictName = null;
	
	public BlockBase(String id, Material materialIn) {
		super(materialIn);
		this.id = id;
		setUnlocalizedName(HMedicineMod.MODID + "." + id);     // Used for localization (en_US.lang)
        setRegistryName(id);    
	}
	
	public void init() {
    	if(oreDictName != null) {
    		OreDictionary.registerOre(oreDictName, this);
    	}
    }
}
