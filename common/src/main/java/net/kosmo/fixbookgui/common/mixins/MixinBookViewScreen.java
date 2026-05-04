package net.kosmo.fixbookgui.common.mixins;

import net.kosmo.fixbookgui.common.FixBookGui;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//? if <=1.21.10 {
/*import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.client.gui.components.Button;
*///?} else {
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
//? }

/**
 * @author mworzala (Implementation: KosmoMoustache)
 * @reason <a href="https://bugs.mojang.com/projects/MC/issues/MC-61489">Minecraft Bug Tracker</a>
 * @see <a href="https://gist.github.com/mworzala/9a8d86803784c9c81aac77d9a7f9fb2b">Gist</a>
 */
@Debug(export = true)
@Mixin(BookViewScreen.class)
public abstract class MixinBookViewScreen extends Screen {

    protected MixinBookViewScreen() {
        super(null);
    }

    //? if <=1.21.10 {
    /*@ModifyArg(method = "createMenuControls", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;"), index = 1)
    public int fbg$createMenuControls(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @Redirect(method = "createPageControlButtons", at = @At(value = "NEW", target = "net/minecraft/client/gui/screens/inventory/PageButton"))
    public PageButton fbg$createPageControlButtons(int x, int y, boolean isForward, Button.OnPress onPress, boolean playTurnSound) {
        return new PageButton(x, FixBookGui.getFixedY(this) + y, isForward, onPress, playTurnSound);
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/BookViewScreen;getClickedComponentStyleAt(DD)Lnet/minecraft/network/chat/Style;"), index = 1)
    public double fbg$getTextStyleAt(double y) {
        return y - (double) FixBookGui.getFixedY(this);
    }

    @ModifyArg(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/BookViewScreen;getClickedComponentStyleAt(DD)Lnet/minecraft/network/chat/Style;"), index = 1)
    public double fbg$mouseClicked(double y) {
        return y - (double) FixBookGui.getFixedY(this);
    }
    *///?}

    //? if <1.21.11 {

    /*//? if >=1.19.4 <1.20.2{
    /^@ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/BookViewScreen;blit(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIII)V"), index = 2)
    ^///?} elif >=1.20.2 <1.21.1{
    /^@ModifyArg(method = "renderBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/Identifier;IIIIII)V"), index = 2)
     ^///?} elif >=1.21.2 <1.21.6{
    /^@ModifyArg(method = "renderBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Ljava/util/function/Function;Lnet/minecraft/resources/Identifier;IIFFIIII)V"), index = 3)
     ^///?} elif >=1.21.6 {
    @ModifyArg(method = "renderBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V"), index = 3)
     //?}
    public int fbg$renderBlit(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    //? if >=1.19.4 <1.20 {
    /^@ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/network/chat/Component;FFI)I"), index = 3)
    ^///?} elif >=1.20 <1.21.10{
    /^@ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)I"), index = 3)
     ^///?} elif >=1.21.10 {
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V"), index = 3)
     //?}
            //~ if >=1.20.2 'float' -> 'int'
    public int fbg$renderStringComponent(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    //? if >=1.19.4 <1.20.2{
    /^@ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/util/FormattedCharSequence;FFI)I"), index = 3)
    ^///?} elif >=1.20.2 <1.20.10 {
    /^@ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;IIIZ)I"), index = 3)
     ^///?} elif >=1.20.10 {
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;IIIZ)V"), index = 3)
     //?}
            //~ if >=1.20.2 'float' -> 'int'
    public int fbg$renderStringFormattedCharSequence(int y) {
        return FixBookGui.getFixedY(this) + y;
    }
    *///?} else {
    @ModifyReturnValue(method = "backgroundTop", at = @At("RETURN"))
    public int fbg$backgroundTop(int original) {
        return FixBookGui.getFixedY(this) + original;
    }
    //?}
}
