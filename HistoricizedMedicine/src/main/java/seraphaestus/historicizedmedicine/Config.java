package seraphaestus.historicizedmedicine;

import net.minecraftforge.common.config.Configuration;
import scala.Int;

public class Config {

    public static final String categoryGeneral = "general";
    public static final String categoryCompat = "compat";
    public static final String categoryKnowledge = "knowledge";

    // This values below you can access elsewhere in your mod:
    public static boolean implementHoney = true;
    public static boolean canViewEffectsInTooltip = true;
    public static boolean canViewHealAmountInTooltip = true;
    public static boolean knowledgeSheetConsumed = true;
    public static String commandRunOnEbersUse = null;
    public static boolean registerRecipes = true;
    public static boolean enableCreativeTab = true;
    public static boolean enableRecipes = true;
    public static int bleedTotalAmount = 10;

    // Call this from CommonProxy.preInit(). It will create our config if it doesn't
    // exist yet and read the values if it does exist.
    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
            initCompatConfig(cfg);
            initKnowledgeConfig(cfg);
        } catch (Exception e1) {

        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(categoryGeneral, "General configuration");
        canViewEffectsInTooltip = cfg.getBoolean("tooltipEffects", categoryGeneral, canViewEffectsInTooltip, "Set to false if you don't want players to be able to see what medical items do re: potion effects");
        canViewHealAmountInTooltip = cfg.getBoolean("tooltipHealAmount", categoryGeneral, canViewHealAmountInTooltip, "Set to false if you don't want players to be able to see what medical items do re: health");
        registerRecipes = cfg.getBoolean("registerRecipes", categoryGeneral, registerRecipes, "Set to false to disable all recipes for this mod");
        enableCreativeTab = cfg.getBoolean("enableCreativeTab", categoryGeneral, enableCreativeTab, "Set to false to disable the creative tab for this mod");
        enableRecipes = cfg.getBoolean("enableRecipes", categoryGeneral, enableRecipes, "Set to false to disable all recipes implemented by this mod");
        bleedTotalAmount = cfg.getInt("bleedTotalAmount", categoryGeneral, bleedTotalAmount, 0, Int.MaxValue(), "The amount of half-hearts that Blood Loss will take off over the course of its effect");
    }

    private static void initCompatConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(categoryCompat, "Configuration of common items that may be duplicates");
        implementHoney = cfg.getBoolean("implementHoney", categoryCompat, implementHoney, "Set to false if you don't want this mod to add honey");
    
    }
    
    private static void initKnowledgeConfig(Configuration cfg) {
    	cfg.addCustomCategoryComment(categoryKnowledge, "Configuration of knowledge sheets (e.g. the ebers papyrus)");
    	knowledgeSheetConsumed = cfg.getBoolean("knSheetConsumed", categoryKnowledge, knowledgeSheetConsumed, "Set to false if you want players to keep their knowledge sheet after using it to run a command");
    	commandRunOnEbersUse = cfg.getString("commandEbers", categoryKnowledge, commandRunOnEbersUse, "The command that should be run when a player uses a complete ebers parchment. (useful with game stages)");
    }
}
