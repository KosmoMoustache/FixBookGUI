package net.kosmo.fixbookgui.mixins;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.LecternScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LecternScreen.class)
public abstract class MixinLecternScreen extends Screen {
    protected MixinLecternScreen() {
        super(null);
    }

    @ModifyArg(method = "addCloseButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;dimensions(IIII)Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;", ordinal = 0), index = 1)
    public int addCloseButton1(int y) {
        return (this.height - 192) / 2 + y;
    }
    @ModifyArg(method = "addCloseButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;dimensions(IIII)Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;", ordinal = 1), index = 1)
    public int addCloseButton2(int y) {
        return (this.height - 192) / 2 + y;
    }
}
