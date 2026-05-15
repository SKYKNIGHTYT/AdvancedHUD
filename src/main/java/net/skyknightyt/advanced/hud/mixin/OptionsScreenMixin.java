package net.skyknightyt.advanced.hud.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.network.chat.Component;
import net.skyknightyt.advanced.hud.compat.DistantHorizonsCompat;
import net.skyknightyt.advanced.hud.config.AdvancedHUDConfigScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {

	@Unique
	private final Minecraft minecraft = Minecraft.getInstance();

	@Unique
	private Button optionsScreenButton;

	protected OptionsScreenMixin(Component title) {
		super(title);
	}

	@Unique
	private AbstractWidget findFOV() {
		List<Renderable> renderables = ((OptionsScreenAccessorMixin) this).getRenderables();

		for (var r : renderables) {
			if (r instanceof AbstractWidget abstractWidget) {
				if (abstractWidget.getMessage().getString().toLowerCase().contains("fov")) {
					return abstractWidget;
				}
			}
		}
		return null;
	}

	@Inject(method = "init", at = @At("RETURN"))
	private void addButton(CallbackInfo callbackInfo) {
		this.optionsScreenButton = Button.builder(
				Component.literal("X"),
				button -> this.minecraft.setScreen(
						new AdvancedHUDConfigScreen(
								Component.literal("AdvancedHUD Config"),
								this
						)
				)
		).size(20, 20).build();

		this.addRenderableWidget(this.optionsScreenButton);

		repositionElements();
	}

	@Inject(method = "repositionElements", at = @At("TAIL"))
	private void reposition(CallbackInfo callbackInfo) {
		AbstractWidget anchor = findFOV();

		if (anchor == null || this.optionsScreenButton == null) return;

		if (DistantHorizonsCompat.DISTANT_HORIZONS) {
			this.optionsScreenButton.setPosition(
					anchor.getX() - 26,
					anchor.getY() + 24
			);
		} else {
			this.optionsScreenButton.setPosition(
					anchor.getX() - 24,
					anchor.getY()
			);
		}
	}
}
