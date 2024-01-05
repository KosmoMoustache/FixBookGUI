package net.kosmo.fixbookgui.mixins;

import net.kosmo.fixbookgui.FixBookGui;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.client.gui.screen.ingame.LecternScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.screen.LecternScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author KosmoMoustache
 * @reason <a href="https://bugs.mojang.com/projects/MC/issues/MC-61489">Minecraft Bug Tracker</a>
 */
@Mixin(LecternScreen.class)
public abstract class MixinLecternScreen extends BookScreen implements ScreenHandlerProvider<LecternScreenHandler> {

    protected MixinLecternScreen() {
        super(null);
    }

    @Redirect(
            method = "addCloseButton",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/ingame/LecternScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;"
            )
    )
    public <T extends Element & Drawable & Selectable> T fbg$translateButtons(LecternScreen screen, T element) {
        if (element instanceof Widget widget) {
            widget.setY(widget.getY() + FixBookGui.getFixedY(this));
        }
        return screen.addDrawableChild(element);
    }
}
