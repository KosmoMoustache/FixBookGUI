package net.kosmo.fixbookgui.mixins;

import net.kosmo.fixbookgui.FixBookGui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author mworzala (Implementation: KosmoMoustache)
 * @reason <a href="https://bugs.mojang.com/projects/MC/issues/MC-61489">Minecraft Bug Tracker</a>
 * @see <a href="https://gist.github.com/mworzala/9a8d86803784c9c81aac77d9a7f9fb2b">Gist</a>
 */
@Mixin(BookViewScreen.class)
public abstract class MixinBookScreen extends Screen {

    protected MixinBookScreen() {
        super(null);
    }

    @Inject(
            method = "renderBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/inventory/BookViewScreen;renderTransparentBackground(Lnet/minecraft/client/gui/GuiGraphics;)V",
                    shift = At.Shift.AFTER
            )
    )
    public void fbg$translateBackground(@NotNull GuiGraphics context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        context.pose().pushPose();
        context.pose().translate(0, FixBookGui.getFixedY(this), 0.0f);
    }

    @Inject(method = "renderBackground", at = @At(value = "RETURN"))
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
            method = "createMenuControls",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/inventory/BookViewScreen;addRenderableWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;"
            )
    )
    public <T extends GuiEventListener & Renderable & NarratableEntry> T fbg$translateCloseButton(BookViewScreen screen, T element) {
        if (element instanceof LayoutElement widget) {
            widget.setY(widget.getY() + FixBookGui.getFixedY(this));
        }
        return screen.addRenderableWidget(element);
    }

    @Redirect(
            method = "createPageControlButtons",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/inventory/BookViewScreen;addRenderableWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;"
            )
    )
    public <T extends GuiEventListener & Renderable & NarratableEntry> T fbg$translatePageButtons(BookViewScreen screen, T element) {
        if (element instanceof LayoutElement widget) {
            widget.setY(widget.getY() + FixBookGui.getFixedY(this));
        }
        return screen.addRenderableWidget(element);
    }

    @ModifyVariable(
            method = "getClickedComponentStyleAt",
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
                    target = "Lnet/minecraft/client/gui/GuiGraphics;renderComponentHoverEffect(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Style;II)V"
            ),
            index = 3
    )
    public int fbg$fixHoverEvent(int y) {
        return y - FixBookGui.getFixedY(this);
    }
}
