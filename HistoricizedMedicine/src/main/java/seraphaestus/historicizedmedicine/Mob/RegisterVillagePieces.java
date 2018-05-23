package seraphaestus.historicizedmedicine.Mob;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import seraphaestus.historicizedmedicine.HMedicineMod;

public class RegisterVillagePieces {
	
	public static void init() {
		VillagerRegistry villageRegistry = VillagerRegistry.instance();
		villageRegistry.registerVillageCreationHandler(new VillageStall.VillageManager());
		MapGenStructureIO.registerStructureComponent(VillageStall.class, HMedicineMod.MODID + ":MedicineStall");
	}

}
