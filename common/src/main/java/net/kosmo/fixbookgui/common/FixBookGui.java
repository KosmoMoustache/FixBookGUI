package net.kosmo.fixbookgui.common;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;

public class FixBookGui {
    public static final String MOD_ID = "fixbookgui";

    public static void init() {
        LogUtils.getLogger().info("FixBookGui initialized");
    }

    public static int getFixedY(Screen screen) {
        return (screen.height - BookViewScreen.IMAGE_HEIGHT) / 3;
    }

    public static BookEditScreen.Pos2i getFixedPosition(BookEditScreen.Pos2i position, Screen screen) {
        return new BookEditScreen.Pos2i(position.x, position.y - FixBookGui.getFixedY(screen));
    }
}
