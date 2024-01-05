package net.kosmo.fixbookgui.mixins;

import net.kosmo.fixbookgui.FixBookGui;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.client.gui.widget.Widget;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author KosmoMoustache
 * @reason <a href="https://bugs.mojang.com/projects/MC/issues/MC-61489">Minecraft Bug Tracker</a>
 */
@Mixin(BookEditScreen.class)
public abstract class MixinBookEditScreen extends Screen {

    protected MixinBookEditScreen() {
        super(null);
    }

    @Inject(
            method = "renderBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;renderBackground(Lnet/minecraft/client/gui/DrawContext;IIF)V",
                    shift = At.Shift.AFTER
            )
    )
    public void fbg$translateBackground(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        context.getMatrices().push();
        context.getMatrices().translate(0, FixBookGui.getFixedY(this), 0.0f);
    }

    @Inject(
            method = "renderBackground",
            at = @At(value = "RETURN")
    )
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
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/ingame/BookEditScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;"
            )
    )
    public <T extends Element & Drawable & Selectable> T fbg$translateButtons(BookEditScreen screen, T element) {
        if (element instanceof Widget widget) {
            widget.setY(widget.getY() + FixBookGui.getFixedY(this));
        }
        return screen.addDrawableChild(element);
    }
}
