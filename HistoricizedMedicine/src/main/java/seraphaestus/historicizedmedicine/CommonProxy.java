package seraphaestus.historicizedmedicine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import seraphaestus.historicizedmedicine.Compat.MainCompatHandler;
import seraphaestus.historicizedmedicine.CraftingTable.Recipe;
import seraphaestus.historicizedmedicine.Effect.EntityUpdate;
import seraphaestus.historicizedmedicine.Item.MilkOverride;
import seraphaestus.historicizedmedicine.Mob.MobChangesHandler;
import seraphaestus.historicizedmedicine.Mob.MobSpawns;
import seraphaestus.historicizedmedicine.Mob.ModEntities;
import seraphaestus.historicizedmedicine.Mob.RegisterVillagePieces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class CommonProxy {

	public static Configuration config;
	private static List<Recipe> customRecipes = new ArrayList<Recipe>();

	public static List<Recipe> getCustomRecipes() {
		if (customRecipes.size() == 0) {
			return new ArrayList<Recipe>();
		}
		List<Recipe> output = new ArrayList<Recipe>();
		for (Recipe r : customRecipes) {
			if (r != null) {
				output.add(new Recipe(r));
			}
		}
		return output;
	}

	/**
	 * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
	 */
	public void preInit(FMLPreInitializationEvent e) {
		File directory = e.getModConfigurationDirectory();
		config = new Configuration(new File(directory.getPath(), "historicizedMedicine.cfg"));
		Config.readConfig();

		MainCompatHandler.registerTOP();

		seraphaestus.historicizedmedicine.Item.RegistryHandler.preInitCommon();
		seraphaestus.historicizedmedicine.Block.RegistryHandler.preInitCommon();
		(new ModEntities()).preInit();
		RegisterVillagePieces.init();

		if (Config.disableUseOfMilkBuckets) {
			MinecraftForge.EVENT_BUS.register(new MilkOverride());
		}
		MinecraftForge.EVENT_BUS.register(new MobChangesHandler());

		//setup exampleRecipe and Recipe directory
		File baseDir = new File(directory.getPath(), "HistoricizedMedicine Recipes");
		try {
			Files.createDirectory(baseDir.toPath());
		} catch (Exception e1) {

		}
		try {
			Path exampleFile = new File(baseDir.getPath(), "exampleFile.json").toPath();
			if (!Files.exists(exampleFile)) {
				Files.createFile(exampleFile);
				Files.write(exampleFile, HardCodedValues.exampleRecipe.getBytes());
			}
		} catch (Exception e2) {

		}
		File[] recipeJsons = baseDir.listFiles();
		//add custom recipes to mod
		if (recipeJsons != null) {
			for (File recipeJson : recipeJsons) {
				if (recipeJson.getName() != "exampleFile") {
					try {
						String contents = readFile(recipeJson.getAbsolutePath());
						customRecipes.add(Recipe.getFromJson(contents));
					} catch (Exception e2) {
						System.out.println(e2.getLocalizedMessage());
					}
				}
			}
		}
	}

	/**
	 * Do your mod setup. Build whatever data structures you care about. Register recipes,
	 * send FMLInterModComms messages to other mods.
	 */
	public void init() {
		registerEventHandlers();
		OreDictRegister.initOreDict();
		NetworkRegistry.INSTANCE.registerGuiHandler(HMedicineMod.instance, new GUIProxy());
	}

	/**
	 * Handle interaction with other mods, complete your setup based on this.
	 */
	public void postInit() {
		if (config.hasChanged()) {
			config.save();
		}
	}

	// helper to determine whether the given player is in creative mode
	//  not necessary for most examples
	abstract public boolean playerIsInCreativeMode(EntityPlayer player);

	/**
	 * is this a dedicated server?
	 *
	 * @return true if this is a dedicated server, false otherwise
	 */
	abstract public boolean isDedicatedServer();

	protected void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(new EntityUpdate());
		MinecraftForge.EVENT_BUS.register(new MobSpawns());
	}

	private String readFile(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			return stringBuilder.toString();
		} finally {
			reader.close();
		}
	}
}