package seraphaestus.historicizedmedicine.Mob.Rat;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderRat extends RenderLiving<EntityRat> {

	public static ModelRat modelRat = new ModelRat();	
	public static final Factory FACTORY = new Factory();
	
	public RenderRat(RenderManager rendermanagerIn)
    {
        super(rendermanagerIn, modelRat, 0.2F);
    }
	
    public static class Factory implements IRenderFactory<EntityRat> {

        @Override
        public Render<? super EntityRat> createRenderFor(RenderManager manager) {
            return new RenderRat(manager);
        }

    }
	@Override
	protected ResourceLocation getEntityTexture(EntityRat entity) {
		String colour = entity.generateColour(entity.getPersistentID());
		if(colour == "") {
			return new ResourceLocation("historicizedmedicine", "textures/entity/rat.png");
		}
		return new ResourceLocation("historicizedmedicine", "textures/entity/rat" + colour + ".png");
	}
}