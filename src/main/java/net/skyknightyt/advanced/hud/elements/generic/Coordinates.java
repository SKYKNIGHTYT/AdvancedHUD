package net.skyknightyt.advanced.hud.elements.generic;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.player.Player;
import net.skyknightyt.advanced.hud.AdvancedHUD;

@Environment(EnvType.CLIENT)
public class Coordinates {

    public static void render(GuiGraphicsExtractor render_i, DeltaTracker tickTracker) {

        if (AdvancedHUD.CONFIG.showCoordinates) {
            Minecraft minecraft = Minecraft.getInstance();
            Player player = minecraft.player;

            if (player == null) return;

            int x = 2;
            int y = 2;

            double playerX = player.getX();
            double playerY = player.getY();
            double playerZ = player.getZ();

            String xText = String.format("X: %.3f", playerX);
            String yText = String.format(" Y: %.3f", playerY);
            String zText = String.format(" Z: %.3f", playerZ);

            int xColor = 0xFFEF4444;
            int yColor = 0xFF22C55E;
            int zColor = 0xFF3B82F6;

            int offset = 0;

            render_i.text(minecraft.font, xText, x + offset, y, xColor);
            offset += minecraft.font.width(xText);

            render_i.text(minecraft.font, yText, x + offset, y, yColor);
            offset += (minecraft.font.width(yText));

            render_i.text(minecraft.font, zText, x + offset, y, zColor);
        }
    }
}
