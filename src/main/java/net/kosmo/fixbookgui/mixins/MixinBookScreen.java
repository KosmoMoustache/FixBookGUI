package net.kosmo.fixbookgui.mixins;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.PageTurnWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author mworzala (Implementation: KosmoMoustache)
 * @reason <a href="https://bugs.mojang.com/projects/MC/issues/MC-61489">Minecraft Bug Tracker</a>
 * @see <a href="https://gist.github.com/mworzala/9a8d86803784c9c81aac77d9a7f9fb2b">Gist</a>
 */
@Mixin(BookScreen.class)
public abstract class MixinBookScreen extends Screen {


    protected MixinBookScreen() {
        super(null);
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/BookScreen;getTextStyleAt(DD)Lnet/minecraft/text/Style;"), index = 1)
    public double fbg$getTextStyleAt(double y) {
        return y - (float)(this.height - 192) / 2;
    }
    @Shadow public abstract boolean mouseClicked(double mouseX, double mouseY, int button);

    @ModifyArg(method= "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/BookScreen;getTextStyleAt(DD)Lnet/minecraft/text/Style;"), index = 1)
    public double fbg$mouseClicked(double y) {
        return y - (float)(this.height - 192) / 2;
    }

    @ModifyArg(method = "addCloseButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;dimensions(IIII)Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;"), index = 1)
    public int fbg$addCloseButton(int y) {
        return y + (this.height - 192) / 2;
    }

    @Redirect(method = "addPageButtons", at = @At(value = "NEW", target = "net/minecraft/client/gui/widget/PageTurnWidget"))
    public PageTurnWidget fbg$addPageButtons(int x, int y, boolean isNextPageButton, ButtonWidget.PressAction action, boolean playPageTurnSound) {
        return new PageTurnWidget(x, y + (this.height - 192) / 2, isNextPageButton, action, playPageTurnSound);
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/BookScreen;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"), index = 2)
    public int fbg$render1(int y) {
        return (this.height - 192) / 2;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I"), index = 3)
    public float fbg$render2(float y) {
        return (float) (this.height - 192) / 2 + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/OrderedText;FFI)I"), index = 3)
    public float fbg$render3(float y) {
        return (float) (this.height - 192) / 2 + y;
    }
}

