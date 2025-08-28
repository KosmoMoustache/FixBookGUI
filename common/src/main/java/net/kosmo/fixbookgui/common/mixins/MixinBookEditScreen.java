package net.kosmo.fixbookgui.common.mixins;

import net.kosmo.fixbookgui.common.FixBookGui;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
//? if <1.21.8 {
/*import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
*///?}

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

    // ! SAME
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;",
            ordinal = 0), index = 1)
    private int fbg$InitSignBtn(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    // ! SAME
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;",
            ordinal = 1), index = 1)
    private int fbg$InitDoneBtn(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    //? if <=1.21.5 {
    /*// ! SAME
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;",
            ordinal = 2), index = 1)
    private int fbg$InitFinalizeBtn(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    // ! SAME
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;",
            ordinal = 3), index = 1)
    private int fbg$InitCancelBtn(int y) {
        return FixBookGui.getFixedY(this) + y;
    }
    *///?}

    // ! SAME
    @Redirect(method = "init", at = @At(value = "NEW", target = "net/minecraft/client/gui/screens/inventory/PageButton"))
    private PageButton fbg$InitPageButton(int x, int y, boolean isForward, Button.OnPress onPress, boolean playTurnSound) {
        return new PageButton(x, FixBookGui.getFixedY(this) + y, isForward, onPress, playTurnSound);
    }

    //? if >=1.21.8 {

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/MultiLineEditBox$Builder;setY(I)Lnet/minecraft/client/gui/components/MultiLineEditBox$Builder;"))
    public int fbg$MultiLineEditBox$Builder$setY(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "renderBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V"),
            index = 3)
    public int fbg$blit(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V"),
            index = 3)
    public int fbg$drawEditTitleLabel(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    //?} elif >=1.20.2 {

    /*//? if >=1.21.2 {
    @ModifyArg(method = "renderBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V"), index = 3)
            //?} else {
    /^@ModifyArg(method = "renderBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"), index = 2)
     ^///?}
    public int fbg$renderBackgroundBlit(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)I",
            ordinal = 0), index = 3)
    public int fbg$renderDrawStringEditTitleLabel(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;IIIZ)I",
            ordinal = 0), index = 3)
    public int fbg$renderDrawStringFormattedCharSequence(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)I",
            ordinal = 1), index = 3)
    public int fbg$renderDrawStringOwnerText(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    //? if >=1.21.4 {
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawWordWrap(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/FormattedText;IIIIZ)V"), index = 3)
     //?} else {
    /^@ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawWordWrap(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/FormattedText;IIII)V"), index = 3)
            ^///?}
    public int fbg$renderDrawWordWrapFinalizeWarningLabel(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)I",
            ordinal = 2), index = 3)
    public int fbg$renderPageMsg(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)I",
            ordinal = 3), index = 3)
    public int fbg$renderDrawStringLineInfo(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArgs(method = "renderCursor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V"))
    public void fbg$renderCursorFill(Args args) {
        args.set(1, FixBookGui.getFixedY(this) + (int) args.get(1));
        args.set(3, FixBookGui.getFixedY(this) + (int) args.get(3));
    }

    @ModifyArg(method = "renderCursor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)I"),
            index = 3)
    public int fbg$renderCursorDrawStringUnderscore(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArgs(method = "renderHighlight", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(Lnet/minecraft/client/renderer/RenderType;IIIII)V"))
    public void fbg$renderHighlightFill(Args args) {
        args.set(2, FixBookGui.getFixedY(this) + (int) args.get(2));
        args.set(4, FixBookGui.getFixedY(this) + (int) args.get(4));
    }

    @Redirect(method = "convertScreenToLocal", at = @At(value = "NEW", target = "net/minecraft/client/gui/screens/inventory/BookEditScreen$Pos2i"))
    public BookEditScreen.Pos2i fbg$convertScreenToLocal(int x, int y) {
        return new BookEditScreen.Pos2i(x, y - FixBookGui.getFixedY(this));
    }
    *///?} elif =1.19.4 {
    /*@ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/BookEditScreen;blit(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIII)V"),
            index = 2)
    public int fbg$renderBlit(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/network/chat/Component;FFI)I",
            ordinal = 0), index = 3)
    public float fbg$renderDrawEditTitleLabel(float y) {
        return (float) FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/util/FormattedCharSequence;FFI)I",
            ordinal = 0), index = 3)
    public float fbg$renderDrawFormattedCharSequence(float y) {
        return (float) FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/network/chat/Component;FFI)I",
            ordinal = 1), index = 3)
    public float fbg$renderDrawOwnerText(float y) {
        return (float) FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;drawWordWrap(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/network/chat/FormattedText;IIII)V"),
            index = 3)
    public int fbg$renderDrawWordWrapFinalizeWarningLabel(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/network/chat/Component;FFI)I",
            ordinal = 2), index = 3)
    public float fbg$renderDrawPageMsg(float y) {
        return (float) FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/network/chat/Component;FFI)I",
            ordinal = 3), index = 3)
    public float fbg$renderDrawLineInfo(float y) {
        return (float) FixBookGui.getFixedY(this) + y;
    }

    @ModifyArgs(method = "renderCursor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiComponent;fill(Lcom/mojang/blaze3d/vertex/PoseStack;IIIII)V"))
    public void fbg$renderCursorFillCursor(Args args) {
        args.set(2, FixBookGui.getFixedY(this) + (int) args.get(2));
        args.set(4, FixBookGui.getFixedY(this) + (int) args.get(4));
    }

    @ModifyArg(method = "renderCursor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Ljava/lang/String;FFI)I"),
            index = 3)
    public float fbg$renderCursorDrawUnderscore(float y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArgs(method = "renderHighlight", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/BookEditScreen;fill(Lcom/mojang/blaze3d/vertex/PoseStack;IIIII)V"))
    public void fbg$renderHighlightFill(Args args) {
        args.set(2, FixBookGui.getFixedY(this) + (int) args.get(2));
        args.set(4, FixBookGui.getFixedY(this) + (int) args.get(4));
    }

    @Redirect(method = "convertScreenToLocal", at = @At(value = "NEW", target = "net/minecraft/client/gui/screens/inventory/BookEditScreen$Pos2i"))
    public BookEditScreen.Pos2i fbg$convertScreenToLocal(int x, int y) {
        return new BookEditScreen.Pos2i(x, y - FixBookGui.getFixedY(this));
    }
    *///?}
}
