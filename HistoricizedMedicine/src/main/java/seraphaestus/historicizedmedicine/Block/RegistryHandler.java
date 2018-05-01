package seraphaestus.historicizedmedicine.Block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import seraphaestus.historicizedmedicine.HMedicineMod;


public class RegistryHandler {
	
	public static List<BlockBase> blocks;
	
	public static void setupBlocks(){
		blocks = new ArrayList<BlockBase>();
        
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
