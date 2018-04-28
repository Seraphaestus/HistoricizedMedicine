package seraphaestus.historicizedmedicine.Mob;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import seraphaestus.historicizedmedicine.HMedicineMod;

public class ModEntities {

	public static void init() {
		int id = 1;
		
		ResourceLocation registryName = new ResourceLocation(HMedicineMod.MODID, "PlagueDoctor");
		EntityRegistry.registerModEntity(registryName, EntityPlagueDoctor.class, "PlagueDoctor", id++, HMedicineMod.instance, 64, 3, true, 0x000000, 0x588f30);
	}
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityPlagueDoctor.class, RenderPlagueDoctor.FACTORY);
    }
}