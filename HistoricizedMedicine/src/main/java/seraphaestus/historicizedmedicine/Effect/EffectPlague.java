package seraphaestus.historicizedmedicine.Effect;

import net.minecraft.potion.Potion;

public class EffectPlague extends Potion
{
    public EffectPlague()
    {
        super(true, 0xF2F2F0);
        setRegistryName("plague");
        setPotionName("The Block Death");
        setIconIndex(0, 0);
    }
}
