package net.skylen.advanced.hud.compat;

import net.fabricmc.loader.api.FabricLoader;

public class DistantHorizonsCompat {
    public static final boolean DISTANT_HORIZONS = FabricLoader.getInstance().isModLoaded("distanthorizons");
}
