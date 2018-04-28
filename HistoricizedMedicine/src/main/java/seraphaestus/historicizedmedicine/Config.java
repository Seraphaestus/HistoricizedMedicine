package seraphaestus.historicizedmedicine;

import net.minecraftforge.common.config.Configuration;

public class Config {

    private static final String categoryGeneral = "general";
    private static final String categoryCompat = "compat";
    private static final String categoryKnowledge = "knowledge";

    // This values below you can access elsewhere in your mod:
    public static boolean implementHoney = true;
    public static boolean canViewEffectsInTooltip = true;
    public static boolean canViewHealAmountInTooltip = true;
    public static boolean knowledgeSheetConsumed = true;
    public static String commandRunOnEbersUse = null;
    public static boolean registerRecipes = true;

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
    }

    private static void initCompatConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(categoryCompat, "Configuration of common items that may be duplicates");
        implementHoney = cfg.getBoolean("implementHoney", categoryCompat, implementHoney, "Set to false if you don't want this mod to add honey");
    
    }
    
    private static void initKnowledgeConfig(Configuration cfg) {
    	cfg.addCustomCategoryComment(categoryKnowledge, "Configuration of knowledge sheets (e.g. the ebers papyrus)");
    	knowledgeSheetConsumed = cfg.getBoolean("knSheetConsumed", categoryKnowledge, knowledgeSheetConsumed, "Set to false if you want players to keep their knowledge sheet after using it");
    	commandRunOnEbersUse = cfg.getString("commandEbers", categoryKnowledge, commandRunOnEbersUse, "The command that should be run when a player uses a complete ebers parchment. (useful with game stages)");
    }
}
