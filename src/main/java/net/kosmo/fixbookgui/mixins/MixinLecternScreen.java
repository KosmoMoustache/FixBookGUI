package net.kosmo.fixbookgui.mixins;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.LecternScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LecternScreen.class)
public abstract class MixinLecternScreen extends Screen {
    protected MixinLecternScreen() {
        super(null);
    }

    @Redirect(method = "addCloseButton", at = @At(value = "NEW", target = "net/minecraft/client/gui/widget/ButtonWidget"))
    public ButtonWidget fbg$addCloseButton(int x, int y, int width, int height, Text message, ButtonWidget.PressAction onPress) {
        return new ButtonWidget(x, y + (this.height - 192) / 2, width, height, message, onPress);
    }
}
