package seraphaestus.historicizedmedicine.Mob.PlagueDoctor;

import javax.annotation.Nonnull;

import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import seraphaestus.historicizedmedicine.HMedicineMod;

@SideOnly(Side.CLIENT)
public class RenderPlagueDoctor extends RenderLiving<EntityPlagueDoctor> {

    private ResourceLocation mobTexture = new ResourceLocation(HMedicineMod.MODID + ":textures/entity/plaguedoctor.png");

    public static final Factory FACTORY = new Factory();

    public RenderPlagueDoctor(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelVillager(0.5f), 0.5F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityPlagueDoctor entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityPlagueDoctor> {

        @Override
        public Render<? super EntityPlagueDoctor> createRenderFor(RenderManager manager) {
            return new RenderPlagueDoctor(manager);
        }

    }

}
