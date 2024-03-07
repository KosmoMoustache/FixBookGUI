package net.kosmo.fixbookgui.mixins;

import net.kosmo.fixbookgui.FixBookGui;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.client.gui.widget.Widget;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

    @Inject(
            method = "renderBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/ingame/BookScreen;renderInGameBackground(Lnet/minecraft/client/gui/DrawContext;)V",
                    shift = At.Shift.AFTER
            )
    )
    public void fbg$translateBackground(@NotNull DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        context.getMatrices().push();
        context.getMatrices().translate(0, FixBookGui.getFixedY(this), 0.0f);
    }

    @Inject(method = "renderBackground", at = @At(value = "RETURN"))
    public void fbg$popBackgroundMatrices(@NotNull DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        context.getMatrices().pop();
    }

    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;render(Lnet/minecraft/client/gui/DrawContext;IIF)V",
                    shift = At.Shift.AFTER
            )
    )
    public void fbg$translateRender(@NotNull DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        context.getMatrices().push();
        context.getMatrices().translate(0, FixBookGui.getFixedY(this), 0.0f);
    }

    @Inject(
            method = "render",
            at = @At(value = "RETURN")
    )
    public void fbg$popRenderMatrices(@NotNull DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        context.getMatrices().pop();
    }

    @Redirect(
            method = "addCloseButton",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/ingame/BookScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;"
            )
    )
    public <T extends Element & Drawable & Selectable> T fbg$translateCloseButton(BookScreen screen, T element) {
        if (element instanceof Widget widget) {
            widget.setY(widget.getY() + FixBookGui.getFixedY(this));
        }
        return screen.addDrawableChild(element);
    }

    @Redirect(
            method = "addPageButtons",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/ingame/BookScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;"
            )
    )
    public <T extends Element & Drawable & Selectable> T fbg$translatePageButtons(BookScreen screen, T element) {
        if (element instanceof Widget widget) {
            widget.setY(widget.getY() + FixBookGui.getFixedY(this));
        }
        return screen.addDrawableChild(element);
    }

    @ModifyVariable(
            method = "getTextStyleAt",
            at = @At("HEAD"),
            ordinal = 1,
            argsOnly = true
    )
    private double fbg$fixGetTextStyleAt(double y) {
        return y - FixBookGui.getFixedY(this);
    }

    @ModifyArg(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawHoverEvent(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Style;II)V"
            ),
            index = 3
    )
    public int fbg$fixHoverEvent(int y) {
        return y - FixBookGui.getFixedY(this);
    }
}
