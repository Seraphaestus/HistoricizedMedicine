package seraphaestus.historicizedmedicine.Effect;

import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import seraphaestus.historicizedmedicine.HMedicineMod;

@EventBusSubscriber(modid = HMedicineMod.MODID)
public final class RegisterEffects
{
    public static final Potion bleeding = 	new HMedPotion(true, 0, "bleed", "Blood Loss", 0);
    public static final Potion infection = 	new HMedPotion(true, 0, "infection", "Infection", 1);
    public static final Potion pain = 		new HMedPotion(true, 0, "pain", "Pain", 2);
    public static final Potion plague = 	new HMedPotion(true, 0, "plague", "The Block Death", 3);

    @SubscribeEvent
    public static void init(RegistryEvent.Register<Potion> event)
    {
        event.getRegistry().registerAll(bleeding, infection, pain, plague);
    }
}
