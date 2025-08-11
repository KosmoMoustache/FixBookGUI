package net.kosmo.fixbookgui.common.mixins;

import net.kosmo.fixbookgui.common.FixBookGui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
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
                    target = "Lnet/minecraft/client/gui/screens/inventory/BookEditScreen;renderTransparentBackground(Lnet/minecraft/client/gui/GuiGraphics;)V",
                    shift = At.Shift.AFTER
            )
    )
    public void fbg$translateBackground(GuiGraphics context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        context.pose().pushPose();
        context.pose().translate(0, FixBookGui.getFixedY(this), 0.0f);
    }

    @Inject(
            method = "renderBackground",
            at = @At(value = "RETURN")
    )
    public void fbg$popBackgroundMatrices(@NotNull GuiGraphics context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        context.pose().popPose();
    }

    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/Screen;render(Lnet/minecraft/client/gui/GuiGraphics;IIF)V",
                    shift = At.Shift.AFTER
            )
    )
    public void fbg$translateRender(@NotNull GuiGraphics context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        context.pose().pushPose();
        context.pose().translate(0, FixBookGui.getFixedY(this), 0.0f);
    }

    @Inject(
            method = "render",
            at = @At(value = "RETURN")
    )
    public void fbg$popRenderMatrices(@NotNull GuiGraphics context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        context.pose().popPose();
    }

    @Redirect(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/inventory/BookEditScreen;addRenderableWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;"
            )
    )
    public <T extends GuiEventListener & Renderable & NarratableEntry> T fbg$translateButtons(BookEditScreen screen, T element) {
        if (element instanceof LayoutElement widget) {
            widget.setY(widget.getY() + FixBookGui.getFixedY(this));
        }
        return this.addRenderableWidget(element);
    }

    @ModifyArg(
            method = "mouseClicked",
            index = 0,
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/inventory/BookEditScreen;convertScreenToLocal(Lnet/minecraft/client/gui/screens/inventory/BookEditScreen$Pos2i;)Lnet/minecraft/client/gui/screens/inventory/BookEditScreen$Pos2i;"
            )
    )
    public BookEditScreen.Pos2i fbg$fixMouseClickPosition(BookEditScreen.Pos2i position) {
        return FixBookGui.getFixedPosition(position, this);
    }

    @ModifyArg(
            method = "mouseDragged",
            index = 0,
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/inventory/BookEditScreen;convertScreenToLocal(Lnet/minecraft/client/gui/screens/inventory/BookEditScreen$Pos2i;)Lnet/minecraft/client/gui/screens/inventory/BookEditScreen$Pos2i;"
            )
    )
    public BookEditScreen.Pos2i fbg$fixMouseDragPosition(BookEditScreen.Pos2i position) {
        return FixBookGui.getFixedPosition(position, this);
    }
}
