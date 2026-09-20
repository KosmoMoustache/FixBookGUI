//? if 1.20.1 || 1.21 || 1.21.1 {
/*package net.kosmo.fixbookgui.common.mixins.compat.amendments;

import net.kosmo.fixbookgui.common.FixBookGui;
import net.mehvahdjukaar.amendments.client.gui.LecternBookEditScreen;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LecternBookEditScreen.class)
public abstract class MixinLecternBookEditMenu extends Screen {
    protected MixinLecternBookEditMenu() {
        super(null);
    }

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;",
            ordinal = 0), index = 1)
    private int fbg$InitTakeButton(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;",
            ordinal = 1), index = 1)
    private int fbg$InitDoneButton(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;",
            ordinal = 2), index = 1)
    private int fbg$InitFinalizeButton(int y) {
        return FixBookGui.getFixedY(this) + y;
    }
}
*///? }
