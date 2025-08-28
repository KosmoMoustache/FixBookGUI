//? if >=1.21.8 {
package net.kosmo.fixbookgui.common.mixins;

import net.kosmo.fixbookgui.common.FixBookGui;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookSignScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author KosmoMoustache
 * @reason <a href="https://bugs.mojang.com/projects/MC/issues/MC-61489">Minecraft Bug Tracker</a>
 */
@Debug(export = true)
@Mixin(BookSignScreen.class)
public abstract class MixinBookSignScreen extends Screen {

    protected MixinBookSignScreen() {
        super(null);
    }

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;",
            ordinal = 0), index = 1)
    private int fbg$SignAndClose(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @Redirect(method = "init", at = @At(value = "NEW", target = "net/minecraft/client/gui/components/EditBox"))
    private EditBox fbg$EditBox(Font font, int x, int y, int width, int height, Component component) {
        return new EditBox(font, x, FixBookGui.getFixedY(this) + y, width, height, component);
    }

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;",
            ordinal = 1), index = 1)
    private int fbg$GuiCancel(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V",
            ordinal = 0), index = 3)
    private int fbg$renderDrawStringEditTitleLabel(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V",
            ordinal = 1), index = 3)
    private int fbg$renderDrawStringOwnerText(int y) {
        return FixBookGui.getFixedY(this) + y;
    }
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawWordWrap(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/FormattedText;IIIIZ)V"),
            index = 3)
    private int fbg$renderDrawWordWrapFinalizeWarningLabel(int y) {
        return FixBookGui.getFixedY(this) + y;
    }

    @ModifyArg(method = "renderBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V"),
            index = 3)
    private int fbg$renderBackgroundBlit(int y) {
        return FixBookGui.getFixedY(this) + y;
    }
}
//?}