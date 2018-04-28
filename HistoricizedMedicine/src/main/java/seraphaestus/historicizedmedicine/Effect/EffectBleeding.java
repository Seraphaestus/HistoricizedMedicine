package seraphaestus.historicizedmedicine.Effect;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import seraphaestus.historicizedmedicine.HMedicineMod;

public class EffectBleeding extends Potion
{
    public EffectBleeding()
    {
        super(true, 0xF2F2F0);
        setRegistryName("bleed");
        setPotionName("Blood Loss");
        //setIconIndex(0, 0);
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {	
		ResourceLocation loc = new ResourceLocation(HMedicineMod.MODID, "effects.png");
		GuiScreen screen = Minecraft.getMinecraft().currentScreen;
		if (screen != null) {
			mc.getTextureManager().bindTexture(loc);
			screen.drawTexturedModalRect(x + 7, y + 7, 18, 18, 16, 16);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
		ResourceLocation loc = new ResourceLocation(HMedicineMod.MODID, "textures/effects.png");
		Gui screen = Minecraft.getMinecraft().ingameGUI;
		if (screen != null) {
			mc.getTextureManager().bindTexture(loc);
			screen.drawTexturedModalRect(x + 4, y + 4, 18, 18, 16, 16);
		}
	}
}