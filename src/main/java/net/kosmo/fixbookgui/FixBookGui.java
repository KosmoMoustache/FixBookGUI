package net.kosmo.fixbookgui;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.client.gui.screen.ingame.BookScreen;

@Environment(EnvType.CLIENT)
public class FixBookGui implements ClientModInitializer {

    public static int getFixedY(Screen screen) {
        return (screen.height - BookScreen.HEIGHT) / 3;
    }

    public static BookEditScreen.Position getFixedPosition(BookEditScreen.Position position, Screen screen ) {
        return new BookEditScreen.Position(position.x, position.y - FixBookGui.getFixedY(screen));
    }

    @Override
    public void onInitializeClient() {
        LogUtils.getLogger().info("FixBookGui initialized");
    }
}
