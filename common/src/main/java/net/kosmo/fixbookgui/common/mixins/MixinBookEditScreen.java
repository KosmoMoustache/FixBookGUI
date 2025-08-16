package net.kosmo.fixbookgui.common.mixins;

import net.kosmo.fixbookgui.common.FixBookGui;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

/**
 * @author KosmoMoustache
 * @reason <a href="https://bugs.mojang.com/projects/MC/issues/MC-61489">Minecraft Bug Tracker</a>
 */
@Debug(export = true)
@Mixin(BookEditScreen.class)
public abstract class MixinBookEditScreen extends Screen {

    protected MixinBookEditScreen() {
        super(null);
    }

    //? =1.20.2 {
    // Buttons
    // ! SAME
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;", ordinal = 0), index = 1)
    private int fbg$signBtn(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    // ! SAME
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;", ordinal = 1), index = 1)
    private int fbg$doneBtn(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    // ! SAME
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;", ordinal = 2), index = 1)
    private int fbg$finalizeBtn(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    // ! SAME
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;", ordinal = 3), index = 1)
    private int fbg$cancelBtn(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    // ! SAME
    @Redirect(method = "init", at = @At(value = "NEW", target = "net/minecraft/client/gui/screens/inventory/PageButton"))
    private PageButton fbg$pageButton(int x, int y, boolean isForward, Button.OnPress onPress, boolean playTurnSound) {
        return new PageButton(x, FixBookGui.getFixedY(this) + y, isForward, onPress, playTurnSound);
    }

    // Render Background
    @ModifyArg(method = "renderBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"),
            index = 2)
    public int fbg$blit(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    // Render
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)I",
            ordinal = 0), index = 3)
    public int fbg$drawEditTitleLabel(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;IIIZ)I",
            ordinal = 0), index = 3)
    public int fbg$formattedCharSequence(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)I",
            ordinal = 1), index = 3)
    public int fbg$ownerText(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawWordWrap(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/FormattedText;IIII)V"),
            index = 3)
    public int fbg$finalizeWarningLabel(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)I",
            ordinal = 2), index = 3)
    public int fbg$pageMsg(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)I",
            ordinal = 3), index = 3)
    public int fbg$lineInfo(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    // Cursor
    @ModifyArgs(method = "renderCursor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V"))
    public void fbg$renderCursorFill(Args args) {
        args.set(1, FixBookGui.getFixedY(this) + (int) args.get(1));
        args.set(3, FixBookGui.getFixedY(this) + (int) args.get(3));
    }

    @ModifyArg(method = "renderCursor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)I"),
            index = 3)
    public int fbg$drawCursor(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    // Selection
    @ModifyArgs(method = "renderHighlight", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(Lnet/minecraft/client/renderer/RenderType;IIIII)V"))
    public void fbg$drawSelectionFillY(Args args) {
        args.set(2, FixBookGui.getFixedY(this) + (int) args.get(2));
        args.set(4, FixBookGui.getFixedY(this) + (int) args.get(4));
    }

    // Mouse clicks/drags
    @Redirect(method = "convertScreenToLocal", at = @At(value = "NEW", target = "net/minecraft/client/gui/screens/inventory/BookEditScreen$Pos2i"))
    public BookEditScreen.Pos2i fbg$convertScreenToLocal(int x, int y) {
        return new BookEditScreen.Pos2i(x, y - FixBookGui.getFixedY(this));
    }
    // } elif >= 1.20.5 {
    /*@Inject(
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
    *///?} elif =1.19.4  {
    /*// Buttons
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;", ordinal = 0), index = 1)
    private int fbg$signBtn(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;", ordinal = 1), index = 1)
    private int fbg$doneBtn(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;", ordinal = 2), index = 1)
    private int fbg$finalizeBtn(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;", ordinal = 3), index = 1)
    private int fbg$cancelBtn(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @Redirect(method = "init", at = @At(value = "NEW", target = "net/minecraft/client/gui/screens/inventory/PageButton"))
    private PageButton fbg$pageButton(int x, int y, boolean isForward, Button.OnPress onPress, boolean playTurnSound) {
        return new PageButton(x, FixBookGui.getFixedY(this) + y, isForward, onPress, playTurnSound);
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/BookEditScreen;blit(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIII)V"),
            index = 2)
    public int fbg$blit(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/network/chat/Component;FFI)I",
            ordinal = 0), index = 3)
    public float fbg$drawEditTitleLabel(float y) {
        return (float) FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/util/FormattedCharSequence;FFI)I",
            ordinal = 0), index = 3)
    public float fbg$formattedCharSequence(float y) {
        return (float) FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/network/chat/Component;FFI)I",
            ordinal = 1), index = 3)
    public float fbg$ownerText(float y) {
        return (float) FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;drawWordWrap(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/network/chat/FormattedText;IIII)V"),
            index = 3)
    public int fbg$render5(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/network/chat/Component;FFI)I",
            ordinal = 2), index = 3)
    public float fbg$pageMsg(float y) {
        return (float) FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/network/chat/Component;FFI)I",
            ordinal = 3), index = 3)
    public float fbg$lineInfo(float y) {
        return (float) FixBookGui.getFixedY(this) + y;
    }

    // Cursor
    @ModifyArg(method = "renderCursor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiComponent;fill(Lcom/mojang/blaze3d/vertex/PoseStack;IIIII)V"),
            index = 2)
    public int fbg$fillCursorMinY(int y1) {
        return FixBookGui.getFixedY(this) + y1;
    }

    @ModifyArg(method = "renderCursor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiComponent;fill(Lcom/mojang/blaze3d/vertex/PoseStack;IIIII)V"),
            index = 4)
    public int fbg$fillCursorMaxY(int y2) {
        return FixBookGui.getFixedY(this) + y2;
    }

    @ModifyArg(method = "renderCursor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Ljava/lang/String;FFI)I"),
            index = 3)
    public float fbg$drawCursor(float y) {
        return FixBookGui.getFixedY(this) + y;
    }

    // Selection
    @ModifyArg(method = "renderHighlight", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/BookEditScreen;fill(Lcom/mojang/blaze3d/vertex/PoseStack;IIIII)V"),
            index = 2)
    public int fbg$drawSelectionFillY(int y1) {
        return FixBookGui.getFixedY(this) + y1;
    }

    @ModifyArg(method = "renderHighlight", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/BookEditScreen;fill(Lcom/mojang/blaze3d/vertex/PoseStack;IIIII)V"),
            index = 4)
    public int fbg$drawSelectionFillHeight(int y2) {
        return FixBookGui.getFixedY(this) + y2;
    }

    // Mouse clicks/drags
    @Redirect(method = "convertScreenToLocal", at = @At(value = "NEW", target = "net/minecraft/client/gui/screens/inventory/BookEditScreen$Pos2i"))
    public BookEditScreen.Pos2i fbg$convertScreenToLocal(int x, int y) {
        // TODO: Remplacer par FixBookGui.getFixedPosition
        return new BookEditScreen.Pos2i(x, y - FixBookGui.getFixedY(this));
    }
    *///?}
}
