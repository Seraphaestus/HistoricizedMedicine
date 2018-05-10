package seraphaestus.historicizedmedicine.Mob;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import seraphaestus.historicizedmedicine.HMedicineMod;
import seraphaestus.historicizedmedicine.Mob.Apothecarian.EntityApothecarian;
import seraphaestus.historicizedmedicine.Mob.Apothecarian.RenderApothecarian;
import seraphaestus.historicizedmedicine.Mob.PlagueDoctor.EntityPlagueDoctor;
import seraphaestus.historicizedmedicine.Mob.PlagueDoctor.RenderPlagueDoctor;

public class ModEntities {

	public static void init() {
		int id = 1;
		
		ResourceLocation registryName = new ResourceLocation(HMedicineMod.MODID, "PlagueDoctor");
		EntityRegistry.registerModEntity(registryName, EntityPlagueDoctor.class, "PlagueDoctor", id++, HMedicineMod.instance, 64, 3, true, 0x000000, 0x588f30);
	
		registryName = new ResourceLocation(HMedicineMod.MODID, "Apothecarian");
		EntityRegistry.registerModEntity(registryName, EntityApothecarian.class, "Apothecarian", id++, HMedicineMod.instance, 64, 3, true, 0x000000, 0x588f30);
	
	}
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityPlagueDoctor.class, RenderPlagueDoctor.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityApothecarian.class, RenderApothecarian.FACTORY);
    }
}