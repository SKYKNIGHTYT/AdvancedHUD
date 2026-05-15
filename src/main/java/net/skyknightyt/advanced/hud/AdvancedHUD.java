package net.skylen.advanced.hud;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.resources.Identifier;
import net.skylen.advanced.hud.config.AdvancedHUDConfigData;
import net.skylen.advanced.hud.elements.equipment.EquipmentElementV3;
import net.skylen.advanced.hud.elements.generic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdvancedHUD implements ClientModInitializer {
	public static final String ADVANCEDHUD_MOD_ID = "advancedhud";
	public static final Logger LOGGER = LoggerFactory.getLogger(ADVANCEDHUD_MOD_ID);

	public static AdvancedHUDConfigData CONFIG = new AdvancedHUDConfigData();

	@Override
	public void onInitializeClient() {

		if (CONFIG.enabled) {
			HudElementRegistry.attachElementAfter(
					VanillaHudElements.CHAT,
					Identifier.fromNamespaceAndPath(ADVANCEDHUD_MOD_ID, "fps"),
					FPS::render
			);
			HudElementRegistry.attachElementAfter(
					VanillaHudElements.CHAT,
					Identifier.fromNamespaceAndPath(ADVANCEDHUD_MOD_ID, "coordinates"),
					Coordinates::render
			);
			HudElementRegistry.attachElementAfter(
					VanillaHudElements.CHAT,
					Identifier.fromNamespaceAndPath(ADVANCEDHUD_MOD_ID, "equipment"),
					EquipmentElementV3::render
			);
			HudElementRegistry.attachElementAfter(
					VanillaHudElements.CHAT,
					Identifier.fromNamespaceAndPath(ADVANCEDHUD_MOD_ID, "ping"),
					Ping::render
			);
			HudElementRegistry.attachElementAfter(
					VanillaHudElements.CHAT,
					Identifier.fromNamespaceAndPath(ADVANCEDHUD_MOD_ID, "direction"),
					Direction::render
			);
			HudElementRegistry.attachElementAfter(
					VanillaHudElements.CHAT,
					Identifier.fromNamespaceAndPath(ADVANCEDHUD_MOD_ID, "day"),
					Day::render
			);
		}
	}
}
