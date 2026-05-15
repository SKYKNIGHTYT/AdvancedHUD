package net.skyknightyt.advanced.hud.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.skyknightyt.advanced.hud.config.AdvancedHUDConfigScreen;

@Environment(EnvType.CLIENT)
public class ModMenuCompat implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent ->
                new AdvancedHUDConfigScreen(
                        Component.literal("AdvancedHUD Config"),
                        parent
                );
    }
}
