package com.example.examplemod.guis;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.containers.ContainerDemoBlock;
import com.example.examplemod.tileEntities.TileDemoBlock;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiDemoBlock extends GuiContainer {
    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private static final ResourceLocation background = new ResourceLocation(ExampleMod.MODID, "textures" +
            "/gui/demogui.png");

    public GuiDemoBlock(TileDemoBlock tileEntity, ContainerDemoBlock container) {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
