package net.skyknightyt.advanced.hud.config;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class AdvancedHUDConfigData {

    public boolean enabled = true;

    public boolean showFPS = true;

    public boolean showPing = true;

    public boolean showDirection = true;

    public boolean showCoordinates = true;

    public boolean showArmorHUD;

    public boolean showDay;
}
