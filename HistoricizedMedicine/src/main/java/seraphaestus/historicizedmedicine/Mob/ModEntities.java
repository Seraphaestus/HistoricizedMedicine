package seraphaestus.historicizedmedicine.Mob;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import seraphaestus.historicizedmedicine.HMedicineMod;
import seraphaestus.historicizedmedicine.HardCodedValues;
import seraphaestus.historicizedmedicine.Mob.Apothecarian.EntityApothecarian;
import seraphaestus.historicizedmedicine.Mob.Apothecarian.RenderApothecarian;
import seraphaestus.historicizedmedicine.Mob.PlagueDoctor.EntityPlagueDoctor;
import seraphaestus.historicizedmedicine.Mob.PlagueDoctor.RenderPlagueDoctor;
import seraphaestus.historicizedmedicine.Mob.Rat.EntityRat;
import seraphaestus.historicizedmedicine.Mob.Rat.RenderRat;

public class ModEntities {
	 
	public void preInit() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void registerEntities(RegistryEvent.Register<EntityEntry> event) {

		ResourceLocation plagueDoctorName = new ResourceLocation(HMedicineMod.MODID, "PlagueDoctor");
		ResourceLocation apothecarianName = new ResourceLocation(HMedicineMod.MODID, "Apothecarian");
		ResourceLocation ratName = new ResourceLocation(HMedicineMod.MODID, "Rat");
		
		event.getRegistry().registerAll(
				EntityEntryBuilder.create().entity(EntityPlagueDoctor.class).id(plagueDoctorName.getResourcePath(), 0).name(plagueDoctorName.getResourceDomain() + "." + plagueDoctorName.getResourcePath()).tracker(64, 3, true).egg(0x000000, 0x588f30).build(),
				EntityEntryBuilder.create().entity(EntityApothecarian.class).id(apothecarianName.getResourcePath(), 1).name(apothecarianName.getResourceDomain() + "." + apothecarianName.getResourcePath()).tracker(64, 3, true).egg(0x000000, 0x588f30).build(),
				EntityEntryBuilder.create().entity(EntityRat.class).id(ratName.getResourcePath(), 2).name(ratName.getResourceDomain() + "." + ratName.getResourcePath()).tracker(18, 3, true).egg(0x000000, 0x588f30).spawn(EnumCreatureType.MONSTER, 100, 3, 5, HardCodedValues.overworldBiomes()).build()			
		);
	}

	@SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityPlagueDoctor.class, RenderPlagueDoctor.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityApothecarian.class, RenderApothecarian.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRat.class, RenderRat.FACTORY);
    }
}