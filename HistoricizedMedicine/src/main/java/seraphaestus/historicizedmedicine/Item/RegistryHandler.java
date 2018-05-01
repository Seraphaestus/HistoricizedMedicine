package seraphaestus.historicizedmedicine.Item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import seraphaestus.historicizedmedicine.Effect.RegisterEffects;
import seraphaestus.historicizedmedicine.Config;
import seraphaestus.historicizedmedicine.HMedicineMod;

import java.util.ArrayList;
import java.util.List;

public class RegistryHandler {

    public static List<ItemBase> items; // this holds the unique instance of your block

    private static Potion wither = pId(20);
    private static Potion poison = pId(19);
    
    public static KnowledgeSheet ebers;

    public static void setupItems(){
        items = new ArrayList<ItemBase>();
        //primitive
        //items.add(new MedKitBase("trephine", "Trephine", 1, -1, -1, new PotionEffect[]{pain(15), bleed(30, 1)}, new Potion[]{wither}, null, -2));
        items.add(new MedKitTool("trephine", "Trephine", 1, -1, -1, new PotionEffect[]{pain(15), bleed(30, 1)}, new Potion[]{wither}, null, -2, 3));
        items.add(new MedKitFood("medicinal_clay", "Medicinal Clay", 8, -1, -1, null, null, null, 1, 1, 1));
        items.add(new MedKitBase("ward_charm", "Ward Charm", 1, -1, -1, null, null, null, 0));
        items.add(new MedKitBase("herbal_poultice", "Herbal Poultice", 8, -1, -1, null, null, new Reduce[] {new Reduce(RegisterEffects.pain, 100)}, 0));
        
        //egyptian: requires ebers papyrus
        items.add(new MedKitBase("meat_bandage", "Raw Meat Bandage", 64, -1, -1, new PotionEffect[]{infect(30)}, null, new Reduce[] {new Reduce(RegisterEffects.bleeding, 100)}, 0));
        if(Config.implementHoney) {
        	items.add(new ItemBase("honey", "Honey", 64, "itemHoney"));
        }
        items.add(new MedKitBase("honey_poultice", "Honey Poultice", 8, -1, -1, null, null, null, 0));
        //speed charm (ocelot claw) //made from bone and leather
        //strength charm (wolf fang)
        //resistance charm (eye of horus)
        items.add(new ItemBase("suture", "Suture", 64));
        items.add(new MedKitBase("ligature", "Ligature Set", 1, -1, -1, new PotionEffect[]{pain(25)}, null, new Reduce[] {new Reduce(RegisterEffects.bleeding, 100)}, -4));       
        
        //greek/roman: Hippocratic Corpus
        //statues/shrines
        items.add(new MedKitTool("scalpel", "Scalpel", 1, -1, 10, new PotionEffect[]{pain(20), infect(20)}, null, null, 5, 10));
        items.add(new MedKitBase("reed_bandage", "Reed Bandage", 64, -1, -1, new PotionEffect[]{infect(10)}, null, new Reduce[] {new Reduce(RegisterEffects.bleeding, 200)}, 0));
        //sulfuric cream (mild antiseptic, antiinflammatory)
        
        //middle ages: The Canon of Medicine
        items.add(new MedKitBase("leech", "Leech", 64, -1, -1, new PotionEffect[] {bleed(10)}, new Potion[] {poison}, null, -1));
        items.add(new MedKitTool("cautery", "Tile Cautery", 1, -1, -1, new PotionEffect[]{pain(40), infect(20)}, new Potion[] {RegisterEffects.bleeding}, null, -6, 20));
        items.add(new MedKitTool("razor", "Barber's Razor", 1, -1, -1, new PotionEffect[]{pain(10), infect(20), bleed(30)}, null, null, 10, 5));
        
        //renaissance: Chirugic Magna
        items.add(new ItemBase("urine", "Urine", 64));
        items.add(new SampleGlass());
        items.add(new ItemBase("ammonia", "Ammonia", 64));
        items.add(new MedKitBase("nitrous_oxide", "Nitrous Oxide", 64, -1, -1, null, new Potion[] {RegisterEffects.pain}, null, 0));
        //tourniquet
        //sterilisation?
    }

    public static void preInitCommon() {  	
        // each instance of your item should have a name that is unique within your mod.  use lower case.
        if(items == null){
            setupItems();
        }
        for(ItemBase item : items){
            ForgeRegistries.ITEMS.register(item.setRegistryName(item.id).setUnlocalizedName(item.name).setMaxStackSize(item.stackSize));
            item.init();
        }
        
        //knowledge sheets
        List<ItemBase> sheets = new ArrayList<ItemBase>();
        Item[] knowledge1 = {iId("trephine")};
        ebers = new KnowledgeSheet("ebers_papyrus", "Ebers Papyrus", 4, knowledge1, Config.commandRunOnEbersUse);
        sheets.add(ebers);
        //register k. sheets
        for(ItemBase item : sheets){
            ForgeRegistries.ITEMS.register(item.setRegistryName(item.id).setUnlocalizedName(item.name).setMaxStackSize(item.stackSize));
        }
    }

    public static void preInitClientOnly() {
        // model to be used for rendering this item
        final int DEFAULT_ITEM_SUBTYPE = 0;
        if(items == null){
            setupItems();
        }
        for(ItemBase item : items){
            ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(HMedicineMod.MODID + ":" + item.id, "inventory");
            ModelLoader.setCustomModelResourceLocation(item, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
        }
        //knowledge sheets
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(HMedicineMod.MODID + ":ebers_papyrus", "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getByNameOrId(HMedicineMod.MODID + ":ebers_papyrus"), DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);

    }

    public static PotionEffect pain(float d){
        return new PotionEffect(RegisterEffects.pain, (int)(d * 20));
    }
    public static PotionEffect bleed(float d){
        return new PotionEffect(RegisterEffects.bleeding, (int)(d * 20));
    }
    public static PotionEffect bleed(float d, int level){
        return new PotionEffect(RegisterEffects.bleeding, (int)(d * 20), level);
    }
    public static PotionEffect infect(float d){
        return new PotionEffect(RegisterEffects.infection, (int)(d * 20));
    }

    public static Potion pId(int id){
        return Potion.getPotionById(id);
    }
    
    public static Item iId(String id) {
    	return Item.getByNameOrId(HMedicineMod.MODID + ":" + id);
    }
}
