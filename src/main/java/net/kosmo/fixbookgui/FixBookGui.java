package net.kosmo.fixbookgui;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;

@Environment(EnvType.CLIENT)
public class FixBookGui implements ClientModInitializer {

    public static int getFixedY(Screen screen) {
        return (screen.height - BookViewScreen.IMAGE_HEIGHT) / 3;
    }

    public static BookEditScreen.Pos2i getFixedPosition(BookEditScreen.Pos2i position, Screen screen ) {
        return new BookEditScreen.Pos2i(position.x, position.y - FixBookGui.getFixedY(screen));
    }

    @Override
    public void onInitializeClient() {
        LogUtils.getLogger().info("FixBookGui initialized");
    }
}
