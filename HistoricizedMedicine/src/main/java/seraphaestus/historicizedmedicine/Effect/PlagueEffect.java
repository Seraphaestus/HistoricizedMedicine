package seraphaestus.historicizedmedicine.Effect;

import net.minecraft.client.particle.ParticleSpell;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PlagueEffect extends ParticleSpell
{
    public PlagueEffect(World parWorld,
            double parX, double parY, double parZ,
            double parMotionX, double parMotionY, double parMotionZ) 
    {
        super(parWorld, parX, parY, parZ, parMotionX, parMotionY, parMotionZ);
        //setParticleTextureIndex(82);
        particleScale = 0.6F;
        setRBGColorF(0x00, 0x00, 0x00);
    }
}
