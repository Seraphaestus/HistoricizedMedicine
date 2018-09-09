package seraphaestus.historicizedmedicine.Mob;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import seraphaestus.historicizedmedicine.HMedicineMod;

public class RegisterVillagePieces {

	public static void init() {
		VillagerRegistry villageRegistry = VillagerRegistry.instance();
		villageRegistry.registerVillageCreationHandler(new VillageStall1.VillageManager());
		villageRegistry.registerVillageCreationHandler(new VillageStall2.VillageManager());
		MapGenStructureIO.registerStructureComponent(VillageStall1.class, HMedicineMod.MODID + ":MedicineStall1");
		MapGenStructureIO.registerStructureComponent(VillageStall2.class, HMedicineMod.MODID + ":MedicineStall2");
	}

}
