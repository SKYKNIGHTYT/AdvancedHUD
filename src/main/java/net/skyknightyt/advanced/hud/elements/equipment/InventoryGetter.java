package net.skyknightyt.advanced.hud.elements.equipment;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public class InventoryGetter {

    public static ItemStack[] getEquipment() {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if (player == null) {
            return new ItemStack[]{
                    ItemStack.EMPTY,
                    ItemStack.EMPTY,
                    ItemStack.EMPTY,
                    ItemStack.EMPTY
            };
        }
        return new ItemStack[]{
                player.getItemBySlot(EquipmentSlot.HEAD),
                player.getItemBySlot(EquipmentSlot.CHEST),
                player.getItemBySlot(EquipmentSlot.LEGS),
                player.getItemBySlot(EquipmentSlot.FEET)
        };
    }
    public static ItemStack[] getOffhand() {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if (player == null) {
            return new ItemStack[]{
                    ItemStack.EMPTY
            };
        }
        return new ItemStack[]{
                player.getItemBySlot(EquipmentSlot.OFFHAND)
        };
    }

    public static ItemStack[] getMainHand() {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if (player == null) {
            return new ItemStack[]{
                    ItemStack.EMPTY
            };
        }

        return new ItemStack[]{
                player.getItemBySlot(EquipmentSlot.MAINHAND)
        };
    }
}
