package seraphaestus.historicizedmedicine;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(	modid = HMedicineMod.MODID, 
		version = HMedicineMod.VERSION)
public class HMedicineMod
{
    // you also need to update the modid and version in two other places as well:
    //  build.gradle file (the version, group, and archivesBaseName parameters)
    //  resources/mcmod.info (the name, description, and version parameters)
    public static final String MODID = "historicizedmedicine";
    public static final String VERSION = "1.12.2a";
    public static CreativeTab creativeTab;

    // The instance of your mod that Forge uses.  Optional.
    @Mod.Instance(HMedicineMod.MODID)
    public static HMedicineMod instance;

    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide="seraphaestus.historicizedmedicine.ClientProxy", serverSide="seraphaestus.historicizedmedicine.ServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	if(Config.enableCreativeTab) {
    		creativeTab = new CreativeTab();
    	}
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();
    }

    public static String prependModID(String name) {return MODID + ":" + name;}
}