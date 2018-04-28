package seraphaestus.historicizedmedicine.Effect;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;

public class EffectPain extends Potion
{
    public EffectPain()
    {
        super(true, 0xF2F2F0);
        setRegistryName("pain");
        setPotionName("Pain");
        setIconIndex(0, 0);
    }


}
