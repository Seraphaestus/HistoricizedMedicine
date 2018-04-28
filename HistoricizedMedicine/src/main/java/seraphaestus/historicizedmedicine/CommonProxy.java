package seraphaestus.historicizedmedicine;

import java.io.File;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import seraphaestus.historicizedmedicine.Effect.EntityUpdate;
import seraphaestus.historicizedmedicine.Item.RegistryHandler;
import seraphaestus.historicizedmedicine.Mob.ModEntities;

public abstract class CommonProxy {

	public static Configuration config;
	
    /**
     * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
     */
    public void preInit(FMLPreInitializationEvent e)
    {
        RegistryHandler.preInitCommon();
        ModEntities.init();
        
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "historicizedMedicine.cfg"));
        Config.readConfig();
    }

    /**
     * Do your mod setup. Build whatever data structures you care about. Register recipes,
     * send FMLInterModComms messages to other mods.
     */
    public void init()
    {
        registerEventHandlers();
    }

    /**
     * Handle interaction with other mods, complete your setup based on this.
     */
    public void postInit()
    {
    	if (config.hasChanged()) {
            config.save();
        }
    }

    // helper to determine whether the given player is in creative mode
    //  not necessary for most examples
    abstract public boolean playerIsInCreativeMode(EntityPlayer player);

    /**
     * is this a dedicated server?
     * @return true if this is a dedicated server, false otherwise
     */
    abstract public boolean isDedicatedServer();

    protected void registerEventHandlers(){
        MinecraftForge.EVENT_BUS.register(new EntityUpdate());
    }
}