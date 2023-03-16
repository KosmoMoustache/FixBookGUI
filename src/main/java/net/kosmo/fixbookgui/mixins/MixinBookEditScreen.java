package net.kosmo.fixbookgui.mixins;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.PageTurnWidget;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.lang.reflect.Array;

/**
 * @author KosmoMoustache
 * @reason <a href="https://bugs.mojang.com/projects/MC/issues/MC-61489">Minecraft Bug Tracker</a>
 */
@Mixin(BookEditScreen.class)
public abstract class MixinBookEditScreen extends Screen {


    protected MixinBookEditScreen() {
        super(null);
    }

    // Buttons OK
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;dimensions(IIII)Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;", ordinal = 0), index = 1)
    private int fbg$init1(int y) {
        return y + (this.height - 192) / 2;
    }

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;dimensions(IIII)Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;", ordinal = 1), index = 1)
    private int fbg$init2(int y) {
        return y + (this.height - 192) / 2;
    }

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;dimensions(IIII)Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;", ordinal = 2), index = 1)
    private int fbg$init3(int y) {
        return y + (this.height - 192) / 2;
    }

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;dimensions(IIII)Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;", ordinal = 3), index = 1)
    private int fbg$init4(int y) {
        return y + (this.height - 192) / 2;
    }

    @Redirect(method = "init", at = @At(value = "NEW", target = "net/minecraft/client/gui/widget/PageTurnWidget"))
    private PageTurnWidget fbg$init4(int x, int y, boolean isNextPageButton, ButtonWidget.PressAction action, boolean playPageTurnSound) {
        return new PageTurnWidget(x, y + (this.height - 192) / 2, isNextPageButton, action, playPageTurnSound);
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/BookEditScreen;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"),
            index = 2)
    public int fbg$render1(int y) {
        return (this.height - 192) / 2;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I",
            ordinal = 0), index = 3)
    public float fbg$render2(float y) {
        return (float) (this.height - 192) / 2 + y;
    }

    @ModifyArg(method = "render", at= @At(value="INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/OrderedText;FFI)I",
            ordinal = 0), index = 3)
    public float fbg$render3(float y) {
        return (float) (this.height - 192) / 2 + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I",
            ordinal = 1), index = 3)
    public float fbg$render4(float y) {
        return (float) (this.height - 192) / 2 + y;
    }


    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;drawTrimmed(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/StringVisitable;IIII)V"),
            index = 3)
    public int fbg$render5(int y) {
        return (this.height - 192) / 2 + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I",
            ordinal = 2), index = 3)
    public float fbg$render6(float y) {
        return (float) (this.height - 192) / 2 + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I",
            ordinal = 3), index = 3)
    public float fbg$render7(float y) {
        return (float) (this.height - 192) / 2 + y;
    }

    @Redirect(method="drawCursor", at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawableHelper;fill(Lnet/minecraft/client/util/math/MatrixStack;IIIII)V"))
    public void fbg$drawCursor1(MatrixStack matrices, int x1, int y1, int x2, int y2, int color) {
        DrawableHelper.fill(matrices, x1, (this.height - 192) / 2 + y1, x2, (this.height - 192) / 2 + y2, color);
    }

    @ModifyArg(method = "drawCursor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I"),
            index = 3)
    public float fbg$drawCursor2(float y) {
        return (float) (this.height - 192) / 2 + y;
    }


    @ModifyArg(method = "drawSelection", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/BookEditScreen;fill(Lnet/minecraft/client/util/math/MatrixStack;IIIII)V"),
            index = 2)
    public int fbg$drawSelectionFillY(int j) {
        return j + (this.height - 192) / 2;
    }
    @ModifyArg(method = "drawSelection", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/BookEditScreen;fill(Lnet/minecraft/client/util/math/MatrixStack;IIIII)V"),
            index =4)
    public int fbg$drawSelectionFillHeight(int l) {
        return l + (this.height - 192) / 2;
    }
}
