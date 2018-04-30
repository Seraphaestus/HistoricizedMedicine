package seraphaestus.historicizedmedicine.Effect;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import seraphaestus.historicizedmedicine.HMedicineMod;

public class HMedPotion extends Potion {

	static ResourceLocation tex = new ResourceLocation(HMedicineMod.MODID, "textures/effects.png");
	
	public HMedPotion(boolean isBadEffectIn, int liquidColorIn, String id, String name, int HMedID) {
		super(isBadEffectIn, liquidColorIn);
		setRegistryName(id);
        setPotionName(name);
        setIconIndex(HMedID % 8, HMedID / 8); //row length of 8 icons for the texture atlas
	}
	
	@Override
	public int getStatusIconIndex()
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(tex);
		return super.getStatusIconIndex();
	}

}
