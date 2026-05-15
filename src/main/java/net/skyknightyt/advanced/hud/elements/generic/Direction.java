package net.skyknightyt.advanced.hud.elements.generic;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.player.Player;
import net.skyknightyt.advanced.hud.AdvancedHUD;

@Environment(EnvType.CLIENT)
public class Direction {

    private static final int[] COLORS = {
            0xFF3B82F6,
            0xFFFACC15,
            0xFFEF4444,
            0xFF22C55E,
            0xFF9BA786,
            0xFFF5882D,
            0xFF898551,
            0xFF2FA4AA
    };

    private static int getDirectionIndex(float yaw) {
        yaw = (yaw + 360f) % 360f;

        if (yaw >= 337.5 || yaw < 22.5) return 2;
        if (yaw < 67.5)  return 6;
        if (yaw < 112.5) return 3;
        if (yaw < 157.5) return 7;
        if (yaw < 202.5) return 0;
        if (yaw < 247.5) return 4;
        if (yaw < 292.5) return 1;
        return 5;
    }

    private static final String[] LABELS = {
            "North", "East", "South", "West",
            "North-East", "South-East", "South-West", "North-West"
    };

    public static void render(GuiGraphicsExtractor render_i, DeltaTracker tickTracker) {

        if (!AdvancedHUD.CONFIG.showDirection) return;

        int x = 2;
        int y = x + 10;

        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (player == null) return;

        int direction = getDirectionIndex(player.getYRot());

        render_i.text(
                minecraft.font,
                "Facing: " + LABELS[direction],
                x,
                y,
                COLORS[direction]
        );
    }
}
