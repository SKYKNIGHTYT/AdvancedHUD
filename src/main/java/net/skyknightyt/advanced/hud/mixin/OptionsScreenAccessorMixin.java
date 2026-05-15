package net.skyknightyt.advanced.hud.mixin;

import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(Screen.class)
public interface OptionsScreenAccessorMixin {

    @Accessor("renderables")
    List<Renderable> getRenderables();

}
