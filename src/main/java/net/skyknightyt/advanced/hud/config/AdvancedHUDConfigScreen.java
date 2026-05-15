package net.skyknightyt.advanced.hud.config;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.skyknightyt.advanced.hud.AdvancedHUD;
import net.skyknightyt.advanced.hud.config.data.ArmorConfigData;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class AdvancedHUDConfigScreen extends Screen {

    private final Screen parent;

    public AdvancedHUDConfigScreen(Component title, Screen parent) {
        super(Component.literal("Advanced HUD Config"));
        this.parent = parent;
    }

    private record Toggle(
            String label,
            Supplier<Boolean> getter,
            Consumer<Boolean> setter
    ) {}
    private record MultiOption<T extends Enum<T>>(
            String label,
            Class<T> enumClass,
            Supplier<T> getter,
            Consumer<T> setter
    ) {}

    private Button createToggleButton(Toggle toggle) {
        return Button.builder(
                Component.literal(toggle.label + ": " +
                        (toggle.getter.get() ? "ON" : "OFF")),
                button -> {
                    boolean value = !toggle.getter.get();

                    toggle.setter.accept(value);

                    button.setMessage(Component.literal(
                            toggle.label + ": " + (value ? "ON" : "OFF")
                    ));
                }
        ).size(200, 20).build();
    }
    private <T extends Enum<T>> Button createMultiOptionButton(
            MultiOption<T> option
    ) {

        T[] values = option.enumClass.getEnumConstants();

        return Button.builder(
                Component.literal(
                        option.label + ": " + option.getter.get().toString()
                ),

                button -> {

                    T current = option.getter.get();

                    int nextIndex =
                            (current.ordinal() + 1) % values.length;

                    T next = values[nextIndex];

                    option.setter.accept(next);

                    button.setMessage(
                            Component.literal(
                                    option.label + ": " + next
                            )
                    );
                }

        ).size(200, 20).build();
    }

    @Override
    protected void init() {

        GridLayout gridLayout = new GridLayout();
        gridLayout.defaultCellSetting().padding(4);

        GridLayout.RowHelper row = gridLayout.createRowHelper(1);

        Toggle[] toggles = {
                new Toggle("FPS",
                        () -> AdvancedHUD.CONFIG.showFPS,
                        v -> AdvancedHUD.CONFIG.showFPS = v),

                new Toggle("Ping",
                        () -> AdvancedHUD.CONFIG.showPing,
                        v -> AdvancedHUD.CONFIG.showPing = v),

                new Toggle("Coordinates",
                        () -> AdvancedHUD.CONFIG.showCoordinates,
                        v -> AdvancedHUD.CONFIG.showCoordinates = v),

                new Toggle("ArmorHUD",
                        () -> AdvancedHUD.CONFIG.showArmorHUD,
                        v -> AdvancedHUD.CONFIG.showArmorHUD = v),

                new Toggle("Direction",
                        () -> AdvancedHUD.CONFIG.showDirection,
                        v -> AdvancedHUD.CONFIG.showDirection = v),
                new Toggle("Day",
                        () -> AdvancedHUD.CONFIG.showDay,
                        v -> AdvancedHUD.CONFIG.showDay = v)
        };

        MultiOption<?>[] multiOptions = {
                new MultiOption<>(
                        "Empty slot behaviour",
                        ArmorConfigData.EmptyArmorSlotBehaviour.class,
                        () -> AdvancedHUD.ARMOR_CONFIG.emptyArmorSlotBehaviour,
                        v -> AdvancedHUD.ARMOR_CONFIG.emptyArmorSlotBehaviour = v
                )
        };

        for (Toggle toggle : toggles) {
            row.addChild(createToggleButton(toggle));
        }
        for (MultiOption<?> option : multiOptions) {
            row.addChild(createMultiOptionButton(option));
        }

        gridLayout.arrangeElements();

        gridLayout.setPosition(this.width / 2, this.height / 8);

        gridLayout.visitWidgets(this::addRenderableWidget);

        addRenderableWidget(
                Button.builder(Component.literal("Back"),
                                button -> minecraft.setScreen(parent))
                        .bounds(this.width / 2 - 204,
                                this.height - 26,
                                200,
                                20)
                        .build()
        );
        addRenderableWidget(
                Button.builder(Component.literal("Advanced Settings"),
                                button -> minecraft.setScreen(
                                        new AdvancedHUDSettingsScreen(
                                                Component.literal("Advanced HUD"),
                                                this
                                        )
                                ))
                        .bounds(this.width / 2 + 4,
                                this.height - 26,
                                200,
                                20)
                        .build()
        );
    }
}
