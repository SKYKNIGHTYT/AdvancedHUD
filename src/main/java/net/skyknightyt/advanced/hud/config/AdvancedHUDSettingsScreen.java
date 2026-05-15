package net.skyknightyt.advanced.hud.config;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

@Environment(EnvType.CLIENT)
public class AdvancedHUDSettingsScreen extends Screen {

    private final Screen parent;

    protected AdvancedHUDSettingsScreen(Component title, Screen parent) {
        super(title);
        this.parent = parent;
    }
    
    @Override

    protected void init() {
        addRenderableWidget(
                Button.builder(Component.literal("Back"),
                                button -> minecraft.setScreen(parent))
                        .bounds(this.width / 2 - 204,
                                this.height - 26,
                                200,
                                20)
                        .build()
        );
    }
}
