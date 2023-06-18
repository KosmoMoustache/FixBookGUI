package net.kosmo.fixbookgui.mixins;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.PageTurnWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.OrderedText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author KosmoMoustache
 * @reason <a href="https://bugs.mojang.com/projects/MC/issues/MC-61489">Minecraft Bug Tracker</a>
 */
@Mixin(BookEditScreen.class)
public abstract class MixinBookEditScreen extends Screen {

    @Final
    @Shadow
    static int HEIGHT;

    @Shadow private boolean signing;
    @Shadow private void finalizeBook(boolean sign) {}

    @Shadow
    private void updateButtons() {}

    protected MixinBookEditScreen() {
        super(null);
    }

    private int getY() {
        return (this.height - HEIGHT) / 3 + 196;
    }

    private int getY(int y) {
        return (this.height - HEIGHT) / 3 + y;
    }

    // Sign Button
    @ModifyArg(method = "init", at=@At(value="INVOKE",
            target="Lnet/minecraft/client/gui/screen/ingame/BookEditScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;",
            ordinal = 0)
    )
    public Element fbg$initSignButton(Element par1) {
        return ButtonWidget.builder(Text.translatable("book.signButton"),button -> {
            this.signing = true;
            this.updateButtons();
        }).dimensions(this.width / 2 - 100, getY(), 98, 20).build();
    }

    // Done Button
    @ModifyArg(method = "init", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/screen/ingame/BookEditScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;",
            ordinal = 1)
    )
    public Element fbg$initDoneButton(Element par1) {
        return ButtonWidget.builder(ScreenTexts.DONE, button -> this.close())
                .dimensions(this.width / 2 + 2, getY(), 98, 20).build();
    }

    // Finalize Button (Sign)
    @ModifyArg(method = "init", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/screen/ingame/BookEditScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;",
            ordinal = 2)
    )
    public Element fbg$initFinalizeButton(Element par1) {
        return ButtonWidget.builder(Text.translatable("book.finalizeButton"), button -> {
            if (this.signing) {
                this.finalizeBook(true);
                this.client.setScreen(null);
            }
        }).dimensions(this.width / 2 - 100, getY(), 98, 20).build();
    }

    // Cancel Button
    @ModifyArg(method = "init", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/screen/ingame/BookEditScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;",
            ordinal = 3)
    )
    public Element fbg$initCancelButton(Element par1) {
        return ButtonWidget.builder(ScreenTexts.CANCEL, button -> {
            if (this.signing) {
                this.signing = false;
            }
            this.updateButtons();
        }).dimensions(this.width / 2 + 2, getY(), 98, 20).build();
    }

    // Page buttons
    @Redirect(method="init", at = @At(value="NEW",
            target = "(IIZLnet/minecraft/client/gui/widget/ButtonWidget$PressAction;Z)Lnet/minecraft/client/gui/widget/PageTurnWidget;")
    )
    public PageTurnWidget fbg$addPageButtons(int x, int y, boolean isNextPageButton, ButtonWidget.PressAction action, boolean playPageTurnSound) {
        return new PageTurnWidget(x, getY(y), isNextPageButton, action, playPageTurnSound);
    }

    // drawTexture
    @ModifyArg(method = "render", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"),
            index = 2
    )
    public int fbg$renderDrawTexture(int y) {
        return getY(y);
    }


    // Text
    @Redirect(method = "render", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)I"
    ))
    public int fbg$renderDrawTextText(DrawContext context, TextRenderer textRenderer, Text text, int x, int y, int color, boolean shadow) {
        return context.drawText(textRenderer, text, x, getY(y), color, shadow);
    }

    // OrderedText
    @Redirect(method = "render", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/OrderedText;IIIZ)I"
    ))
    public int fbg$renderDrawTextOrderedText(DrawContext context, TextRenderer textRenderer, OrderedText orderedText, int x, int y, int color, boolean shadow) {
        return context.drawText(textRenderer, orderedText, x, getY(y), color, shadow);
    }

    // TextWrapper
    @Redirect(method = "render", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/DrawContext;drawTextWrapped(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/StringVisitable;IIII)V"
    ))
    public void fbg$renderDrawTextOWrapper(DrawContext context, TextRenderer textRenderer, StringVisitable text, int x, int y, int width, int color) {
        context.drawTextWrapped(textRenderer, text, x, getY(y), width, color);
    }

    @Redirect(method="drawCursor", at=@At(value="INVOKE",
            target="Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V")
    )
    public void fbg$drawCursor1(DrawContext context, int x1, int y1, int x2, int y2, int color) {
        context.fill(x1, getY(y1), x2, getY(y2), color);
    }

    @ModifyArg(method = "drawCursor", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;IIIZ)I"),
            index = 3
    )
    public int fbg$drawCursor2(int y) {
        return getY(y);
    }


    @ModifyArg(method = "drawSelection", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/DrawContext;fill(Lnet/minecraft/client/render/RenderLayer;IIIII)V"),
            index = 2
    )
    public int fbg$drawSelectionFillY(int j) {
        return j + (this.height - HEIGHT) / 2;
    }
    @ModifyArg(method = "drawSelection", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/DrawContext;fill(Lnet/minecraft/client/render/RenderLayer;IIIII)V"),
            index = 4
    )
    public int fbg$drawSelectionFillHeight(int l) {
        return l + (this.height - HEIGHT) / 2;
    }
}
