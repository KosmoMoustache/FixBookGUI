package net.kosmo.fixbookgui.mixins;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.client.gui.screen.ingame.LecternScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.LecternScreenHandler;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/**
 * @author KosmoMoustache
 * @reason <a href="https://bugs.mojang.com/projects/MC/issues/MC-61489">Minecraft Bug Tracker</a>
 */
@Mixin(LecternScreen.class)
public abstract class MixinLecternScreen extends BookScreen implements ScreenHandlerProvider<LecternScreenHandler> {

    @Shadow
    private void sendButtonPressPacket(int i) {
    }

    protected MixinLecternScreen() {
        super(null);
    }

    private int getY() {
        return (this.height - HEIGHT) / 3 + 196;
    }

    @ModifyArg(method = "addCloseButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/LecternScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 0))
    public Element fbg$addCloseButton1(Element par1) {
        return ButtonWidget.builder(ScreenTexts.DONE, (button) -> {
            this.close();
        }).dimensions(this.width / 2 - 100, getY(), 98, 20).build();
    }

    @ModifyArg(method = "addCloseButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/LecternScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal =1))
    public Element fbg$addCloseButton2(Element par1) {
        return ButtonWidget.builder(Text.translatable("lectern.take_book"), (button) -> {
            this.sendButtonPressPacket(3);
        }).dimensions(this.width / 2 + 2, getY(), 98, 20).build();
    }
}
