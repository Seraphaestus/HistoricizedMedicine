package seraphaestus.historicizedmedicine.Effect;

import net.minecraft.potion.Potion;

public class EffectInfection extends Potion
{
    public EffectInfection()
    {
        super(true, 0xF2F2F0);
        setRegistryName("infection");
        setPotionName("Infection");
        setIconIndex(0, 0);
    }
}
