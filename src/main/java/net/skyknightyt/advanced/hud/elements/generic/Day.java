package net.skyknightyt.advanced.hud.elements.generic;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.player.Player;
import net.skyknightyt.advanced.hud.AdvancedHUD;

@Environment(EnvType.CLIENT)
public class Day {
    public static void render(GuiGraphicsExtractor render_i, DeltaTracker tickCounter) {

        if (AdvancedHUD.CONFIG.showDay) {
            Minecraft minecraft = Minecraft.getInstance();
            Player player = minecraft.player;
            ClientLevel level = minecraft.level;

            if (level == null || player == null) return;

            int x = 2;
            int y = 40;

            int defaultColor = 0xFFFFFFFF;

            long day = level.getGameTime() / 24000L;

            render_i.text(minecraft.font, "Day: " + day, x, y, defaultColor);
        }
    }
}
