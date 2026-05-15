package net.skyknightyt.advanced.hud.elements.generic;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.player.Player;
import net.skyknightyt.advanced.hud.AdvancedHUD;

@Environment(EnvType.CLIENT)
public class Ping {

    public static void render(GuiGraphicsExtractor render_i, DeltaTracker tickCounter) {

        if (AdvancedHUD.CONFIG.showPing) {
            Minecraft minecraft = Minecraft.getInstance();

            Player player = minecraft.player;

            int x = 2;
            int y = x + 20;

            if (minecraft.level == null) return;

            var connection = minecraft.getConnection();

            if (player == null || connection == null) return;

            try {
                var playerLatencyInfo = connection.getPlayerInfo(player.getUUID());

                if (playerLatencyInfo == null) {
                    render_i.text(minecraft.font, "Ping: ...", x, y, 0xFF9CA3AF);
                    return;
                }

                int ping = playerLatencyInfo.getLatency();

                int color;
                if (ping < 50) {
                    color = 0xFF22C55E;
                } else if (ping < 150) {
                    color = 0xFFFACC15;
                } else {
                    color = 0xFFEF4444;
                }

                render_i.text(minecraft.font, "Ping: " + ping + "ms", x, y, color);

            } catch (Exception ignored) {

            }
        }
    }
}
