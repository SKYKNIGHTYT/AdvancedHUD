package net.skyknightyt.advanced.hud.elements.generic;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.skyknightyt.advanced.hud.AdvancedHUD;

@Environment(EnvType.CLIENT)
public class FPS {

    public static void render(GuiGraphicsExtractor render_i, DeltaTracker tickCounter) {

        Minecraft minecraft = Minecraft.getInstance();

        int fps = minecraft.getFps();

        int fpsLimit = minecraft.options.framerateLimit().get();

        if (AdvancedHUD.CONFIG.showFPS) {

            int x = 2;
            int y = x + 30;

            int lowFPSColor = 0xFFEF4444;
            int midFPSColor = 0xFFFACC15;
            int highFPSColor = 0xFF22C55E;

            int defaultColor;

            if (fps <= (fpsLimit / 3)) {
                defaultColor = lowFPSColor;
            } else if (fps <= ((fpsLimit * 2) / 3)) {
                defaultColor = midFPSColor;
            } else {
                defaultColor = highFPSColor;
            }
            render_i.text(minecraft.font, "FPS: " + fps, x, y, defaultColor);
        }
    }
}
