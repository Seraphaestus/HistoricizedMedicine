package seraphaestus.historicizedmedicine.Effect;

import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import seraphaestus.historicizedmedicine.HMedicineMod;

@EventBusSubscriber(modid = HMedicineMod.MODID)
public final class RegisterEffects
{
    public static final Potion bleeding = new EffectBleeding();
    public static final Potion infection = new EffectInfection();
    public static final Potion pain = new EffectPain();
    public static final Potion plague = new EffectPlague();

    @SubscribeEvent
    public static void init(RegistryEvent.Register<Potion> event)
    {
        event.getRegistry().registerAll(bleeding, infection, pain, plague);
    }
}
