package com.shinobi.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

/**
 * Draws a small chakra bar above the hotbar, blue-to-cyan like the series'
 * chakra color, filling left to right with the player's current chakra.
 */
public class ChakraHudOverlay implements IGuiOverlay {

    @Override
    public void render(net.minecraftforge.client.gui.overlay.ForgeGui gui, GuiGraphics graphics, float partialTick, int screenWidth, int screenHeight) {
        int barWidth = 100;
        int barHeight = 5;
        int x = screenWidth / 2 - barWidth / 2;
        int y = screenHeight - 49;

        int chakra = ClientChakraData.getChakra();
        int max = Math.max(1, ClientChakraData.getMaxChakra());
        int filled = (int) ((float) chakra / max * barWidth);

        graphics.fill(x - 1, y - 1, x + barWidth + 1, y + barHeight + 1, 0x80000000);
        graphics.fill(x, y, x + barWidth, y + barHeight, 0xFF1B2A4A);
        graphics.fill(x, y, x + filled, y + barHeight, 0xFF2E9BF0);
    }
}
