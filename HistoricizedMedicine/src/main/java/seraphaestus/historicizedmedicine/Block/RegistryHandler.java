package seraphaestus.historicizedmedicine.Block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import seraphaestus.historicizedmedicine.HMedicineMod;
import seraphaestus.historicizedmedicine.Effect.RegisterEffects;
import seraphaestus.historicizedmedicine.Util.Reduce;


public class RegistryHandler {
	
	public static List<BlockBase> blocks;
	
	private static final Potion fireRes = Potion.getPotionById(12);
	public static final String[] statues = new String[] {"statue_nether", "statue_end", "statue_wise"};
	
	public static void setupBlocks(){
		blocks = new ArrayList<BlockBase>();
        blocks.add(new BlockStatue("statue_nether", Material.ROCK, new PotionEffect[] {new PotionEffect(fireRes, 20 * 60)}, null, null, 0));
        blocks.add(new BlockStatue("statue_end", Material.ROCK, null, null, null, 6));
        blocks.add(new BlockStatue("statue_wise", Material.ROCK, null, null, new Reduce[] {new Reduce(RegisterEffects.infection, 20 * 10), new Reduce(RegisterEffects.pain, 20 * 45)}, 0));
    }

    public static void preInitCommon() {  	
        // each instance of your item should have a name that is unique within your mod.  use lower case.
        if(blocks == null){
        	setupBlocks();
        }
        for(BlockBase block : blocks){
        	ForgeRegistries.BLOCKS.register(block);
            block.init();
        }
    }

    public static void preInitClientOnly() {
        // model to be used for rendering this item
        final int DEFAULT_ITEM_SUBTYPE = 0;
        if(blocks == null){
            setupBlocks();
        }
        for(BlockBase block : blocks){
            ModelResourceLocation blockModelResourceLocation = new ModelResourceLocation(HMedicineMod.MODID + ":" + block.id, "inventory");
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), DEFAULT_ITEM_SUBTYPE, blockModelResourceLocation);
        }
    }

}
