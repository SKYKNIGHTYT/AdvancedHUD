package net.skyknightyt.advanced.hud;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.resources.Identifier;
import net.skyknightyt.advanced.hud.config.AdvancedHUDConfigData;
import net.skyknightyt.advanced.hud.config.data.ArmorSettingsData;
import net.skyknightyt.advanced.hud.elements.equipment.EquipmentElement;
import net.skyknightyt.advanced.hud.elements.generic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class AdvancedHUD implements ClientModInitializer {
	public static final String ADVANCEDHUD_MOD_ID = "advancedhud";
	public static final Logger LOGGER = LoggerFactory.getLogger(ADVANCEDHUD_MOD_ID);

	public static AdvancedHUDConfigData CONFIG = new AdvancedHUDConfigData();
	public static ArmorSettingsData ARMOR_CONFIG = new ArmorSettingsData();

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
					EquipmentElement::render
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
