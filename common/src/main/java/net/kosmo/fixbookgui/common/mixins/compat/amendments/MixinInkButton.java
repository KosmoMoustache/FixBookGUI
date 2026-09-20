//? if 1.21 || 1.21.1 {
/*package net.kosmo.fixbookgui.common.mixins.compat.amendments;

import com.llamalad7.mixinextras.sugar.Local;
import net.kosmo.fixbookgui.common.FixBookGui;
import net.mehvahdjukaar.amendments.client.gui.InkButton;
import net.mehvahdjukaar.amendments.client.gui.LecternBookEditScreen;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(InkButton.class)
public abstract class MixinInkButton extends AbstractWidget {
    protected MixinInkButton() {
        super(0, 0, 0, 0, Component.empty());
    }

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/AbstractWidget;<init>(IIIILnet/minecraft/network/chat/Component;)V",
            ordinal = 0), index = 1)
    private static int fbg$InitSuper(int y, @Local(argsOnly = true, name = "screen") LecternBookEditScreen screen) {
        return FixBookGui.getFixedY(screen) + y;
    }
}
*///? }
