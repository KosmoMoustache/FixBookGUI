//? if 1.21 || 1.21.1 {
/*package net.kosmo.fixbookgui.common.mixins.compat.amendments;

import com.llamalad7.mixinextras.sugar.Local;
import net.kosmo.fixbookgui.common.FixBookGui;
import net.mehvahdjukaar.amendments.client.gui.QuillButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(QuillButton.class)
public abstract class MixinQuillButton extends AbstractWidget {
    protected MixinQuillButton() {
        super(0, 0, 0, 0, Component.empty());
    }

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/AbstractWidget;<init>(IIIILnet/minecraft/network/chat/Component;)V",
            ordinal = 0), index = 1)
    private static int fbg$InitSuper(int y, @Local(argsOnly = true, name = "screen") Screen screen) {
        return FixBookGui.getFixedY(screen) + y;
    }
}
*///? }
