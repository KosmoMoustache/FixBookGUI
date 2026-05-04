package net.kosmo.fixbookgui.common.mixins;

import net.kosmo.fixbookgui.common.FixBookGui;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
//? if <=1.21.5 {
/*import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
*///?}
//? if <=1.21.10 {
/*import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.PageButton;
*///?}
//? if >=1.21.11 {
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
 //?}


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

    //? if <=1.21.10 {
    /*// init
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;"), index = 1)
    private int fbg$initButtonBuilderBounds(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @Redirect(method = "init", at = @At(value = "NEW", target = "net/minecraft/client/gui/screens/inventory/PageButton"))
    private PageButton fbg$initPageButton(int x, int y, boolean isForward, Button.OnPress onPress, boolean playTurnSound) {
        return new PageButton(x, FixBookGui.getFixedY(this) + y, isForward, onPress, playTurnSound);
    }

    //? if >=1.21.6 {
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/MultiLineEditBox$Builder;setY(I)Lnet/minecraft/client/gui/components/MultiLineEditBox$Builder;"))
    public int fbg$MultiLineEditBox$Builder$setY(int y) {
        return FixBookGui.getFixedY(this) + y;
    }
    //?}

    // render
    //? if >=1.19.4 <1.20.2{
    /^@ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/BookEditScreen;blit(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIII)V"), index = 2)
    ^///?} elif >=1.20.2 <1.21.2 {
    /^@ModifyArg(method = "renderBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/Identifier;IIIIII)V"), index = 2)
    ^///?} elif >=1.21.2 <1.21.6 {
    /^@ModifyArg(method = "renderBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Ljava/util/function/Function;Lnet/minecraft/resources/Identifier;IIFFIIII)V"), index = 3)
    ^///?} elif >=1.21.6 {
    @ModifyArg(method = "renderBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V"), index = 3)
    //?}
    public int fbg$renderBlit(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    //? if >=1.19.4 <1.20.2{
    /^@ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/network/chat/Component;FFI)I"), index = 3)
    ^///?} elif >=1.20.2 <1.21.6 {
    /^@ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)I"), index = 3)
    ^///?} elif >=1.21.6 {
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V"), index = 3)
    //?}
    //~ if >=1.20.2 'float' -> 'int'
    public int fbg$renderDrawComponent(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    //? if <1.21.8 {
    /^//? if >=1.19.4 <1.20.2 {
    /^¹@ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/util/FormattedCharSequence;FFI)I"), index = 3)
    ¹^///?} elif >=1.20.2 {
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;IIIZ)I"), index = 3)
    //?}
    //~ if >=1.20.2 'float' -> 'int'
    public int fbg$renderDrawFormattedCharSequence(int y) {
        return FixBookGui.getFixedY(this) + y;
    }


    //? if >=1.19.4 <1.20.2{
    /^¹@ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;drawWordWrap(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/network/chat/FormattedText;IIII)V"), index = 3)
    ¹^///?} elif >=1.20.2 <1.21.4 {
    /^¹@ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawWordWrap(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/FormattedText;IIII)V"), index = 3)
    ¹^///?} elif >=1.21.4 {
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawWordWrap(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/FormattedText;IIIIZ)V"), index = 3)
    //?}
    public int fbg$renderDrawWordWrapFinalizeWarningLabel(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    //? if >=1.19.4 <1.20.2 {
    /^¹@ModifyArgs(method = "renderCursor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiComponent;fill(Lcom/mojang/blaze3d/vertex/PoseStack;IIIII)V"))
    ¹^///?} elif >=1.20.2 {
    @ModifyArgs(method = "renderCursor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V"))
    //?}
    public void fbg$renderCursorFillCursor(Args args) {
        args.set(2, FixBookGui.getFixedY(this) + (int) args.get(2));
        args.set(4, FixBookGui.getFixedY(this) + (int) args.get(4));
    }

    //? if >=1.19.4 <1.20.2{
    /^¹@ModifyArg(method = "renderCursor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Ljava/lang/String;FFI)I"), index = 3)
    ¹^///?} elif >=1.20.2 {
    @ModifyArg(method = "renderCursor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)I"), index = 3)
    //?}
    //~ if >=1.20.2 'float' -> 'int'
    public int fbg$renderCursorDrawUnderscore(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    //? if >=1.19.4 <1.20.2{
    /^¹@ModifyArgs(method = "renderHighlight", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/BookEditScreen;fill(Lcom/mojang/blaze3d/vertex/PoseStack;IIIII)V"))
    ¹^///?} elif >=1.20.2 {
    @ModifyArgs(method = "renderHighlight", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(Lnet/minecraft/client/renderer/RenderType;IIIII)V"))
    //?}
    public void fbg$renderHighlightFill(Args args) {
        args.set(2, FixBookGui.getFixedY(this) + (int) args.get(2));
        args.set(4, FixBookGui.getFixedY(this) + (int) args.get(4));
    }

    @Redirect(method = "convertScreenToLocal", at = @At(value = "NEW", target = "net/minecraft/client/gui/screens/inventory/BookEditScreen$Pos2i"))
    public BookEditScreen.Pos2i fbg$convertScreenToLocal(int x, int y) {
        return new BookEditScreen.Pos2i(x, y - FixBookGui.getFixedY(this));
    }
    ^///?}
    *///?} else {
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/MultiLineEditBox$Builder;setY(I)Lnet/minecraft/client/gui/components/MultiLineEditBox$Builder;"))
    public int fbg$initSetY(int y) {
        return FixBookGui.getFixedY(this) + y;
    }
    @ModifyReturnValue(method = "backgroundTop", at = @At("RETURN"))
    public int fbg$backgroundTop(int original) {
        return FixBookGui.getFixedY(this) + original;
    }
    //?}
}
