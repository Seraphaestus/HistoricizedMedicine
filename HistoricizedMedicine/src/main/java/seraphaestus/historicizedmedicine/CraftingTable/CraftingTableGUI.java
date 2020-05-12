package seraphaestus.historicizedmedicine.CraftingTable;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import seraphaestus.historicizedmedicine.HMedicineMod;

public class CraftingTableGUI extends GuiContainer {

	public static final int WIDTH = 176;
	public static final int HEIGHT = 166;

	private static final ResourceLocation background = new ResourceLocation(HMedicineMod.MODID, "textures/gui/crafting.png");

	public CraftingTableGUI(CraftingTableTileEntity tileEntity, CraftingTableContainer container) {
		super(container);

		xSize = WIDTH;
		ySize = HEIGHT;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(background);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		//renderHoveredToolTip(mouseX, mouseY);
	}
}
