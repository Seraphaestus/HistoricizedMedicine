package seraphaestus.historicizedmedicine.Block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import seraphaestus.historicizedmedicine.Config;
import seraphaestus.historicizedmedicine.HMedicineMod;
import seraphaestus.historicizedmedicine.Effect.RegisterEffects;

public class BlockBase extends Block {

	public String id;
	private String oreDictName = null;
	
	public BlockBase(String id, Material materialIn) {
		super(materialIn);
		this.id = id;
		setUnlocalizedName(HMedicineMod.MODID + "." + id);     // Used for localization (en_US.lang)
        setRegistryName(id);    
        if(Config.enableCreativeTab) {
        	setCreativeTab(HMedicineMod.creativeTab);
        }
	}
	
	public void init() {
    	if(oreDictName != null) {
    		OreDictionary.registerOre(oreDictName, this);
    	}
    	registerItemBlock();
    }
	
	public void registerItemBlock() {
    	ForgeRegistries.ITEMS.register(new ItemBlock(this).setRegistryName(this.id));
	}
	
	public static PotionEffect pain(float d){
        return new PotionEffect(RegisterEffects.pain, (int)(d * 20));
    }
    public static PotionEffect bleed(float d){
        return new PotionEffect(RegisterEffects.bleeding, (int)(d * 20));
    }
    public static PotionEffect infect(float d){
        return new PotionEffect(RegisterEffects.infection, (int)(d * 20));
    }
}
