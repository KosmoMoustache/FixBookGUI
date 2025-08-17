package net.kosmo.fixbookgui.common.mixins;

import net.kosmo.fixbookgui.common.FixBookGui;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

//? >= 1.20.2 {
/*import net.kosmo.fixbookgui.common.FixBookGui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.narration.NarratableEntry;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
*/// }

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

    // ! SAME
    @ModifyArg(method = "createMenuControls", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;"), index = 1)
    public int fbg$createMenuControls(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    // ! SAME
    @Redirect(method = "createPageControlButtons", at = @At(value = "NEW", target = "net/minecraft/client/gui/screens/inventory/PageButton"))
    public PageButton fbg$addPageButtons(int x, int y, boolean isForward, Button.OnPress onPress, boolean playTurnSound) {
        return new PageButton(x, FixBookGui.getFixedY(this) + y, isForward, onPress, playTurnSound);
    }

    // ! SAME
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/BookViewScreen;getClickedComponentStyleAt(DD)Lnet/minecraft/network/chat/Style;"), index = 1)
    public double fbg$getTextStyleAt(double y) {
        return y - (double) FixBookGui.getFixedY(this);
    }

    // ! SAME
    @ModifyArg(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/BookViewScreen;getClickedComponentStyleAt(DD)Lnet/minecraft/network/chat/Style;"), index = 1)
    public double fbg$mouseClicked(double y) {
        return y - (double) FixBookGui.getFixedY(this);
    }

    //? >= 1.20.2 {
    @ModifyArg(method = "renderBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"), index = 2)
    public int fbg$renderBlit(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)I"), index = 3)
    public int fbg$renderDrawPageMsg(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;IIIZ)I"), index = 3)
    public int fbg$renderDrawFormattedCharSequence(int y) {
        return FixBookGui.getFixedY(this) + y;
    }
    //?} elif =1.19.4 {
    /*@ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/BookViewScreen;blit(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIII)V"), index = 2)
    public int fbg$renderBlit(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/network/chat/Component;FFI)I"), index = 3)
    public float fbg$renderDraw1(float y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/util/FormattedCharSequence;FFI)I"), index = 3)
    public float fbg$renderDraw2(float y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/BookViewScreen;getClickedComponentStyleAt(DD)Lnet/minecraft/network/chat/Style;"), index = 1)
    public double fbg$getTextStyleAt(double y) {
        return y - (double) FixBookGui.getFixedY(this);
    }
    *///?}
}
