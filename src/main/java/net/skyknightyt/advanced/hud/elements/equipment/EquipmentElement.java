package net.skyknightyt.advanced.hud.elements.equipment;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.skyknightyt.advanced.hud.AdvancedHUD;

import static net.skyknightyt.advanced.hud.elements.equipment.InventoryGetter.getEquipment;

@Environment(EnvType.CLIENT)
public class EquipmentElement {

    private static final Identifier[] SLOT_TEXTURES = {
            Identifier.fromNamespaceAndPath("advancedhud", "textures/gui/sprites/hud/slotv6a.png"),
            Identifier.fromNamespaceAndPath("advancedhud", "textures/gui/sprites/hud/slotv6b.png"),
            Identifier.fromNamespaceAndPath("advancedhud", "textures/gui/sprites/hud/slotv6c.png"),
            Identifier.fromNamespaceAndPath("advancedhud", "textures/gui/sprites/hud/slotv6d.png")
    };

    private static final Minecraft minecraft = Minecraft.getInstance();

    public static void render(GuiGraphicsExtractor render_i, DeltaTracker tickCounter) {

        if (AdvancedHUD.CONFIG.showArmorHUD) {
            ItemStack[] equipment = getEquipment();

            int screenX = render_i.guiWidth();
            int screenY = render_i.guiHeight();

            int slotSize = 22;

            int hotbarX = (screenX - (slotSize * 9)) / 2;
            int hotbarY = screenY - 22;

            int baseX = hotbarX - (slotSize * 5);

            int[] itemOffsets = {
                    6,
                    slotSize + 4,
                    (slotSize * 2) + 2,
                    (slotSize * 3)
            };

            for (int i = 0; i < 4; i++) {

                int x = baseX + itemOffsets[i];
                ItemStack stack = equipment[i];

                render_i.blit(
                        RenderPipelines.GUI_TEXTURED,
                        SLOT_TEXTURES[i],
                        x,
                        hotbarY,
                        0f, 0f,
                        22, 22,
                        22, 22
                );

                render_i.item(stack, x + itemOffset, hotbarY + 3);

                render_i.itemDecorations(minecraft.font, stack, x + 3, hotbarY + 3);
            }
        }
    }
}
