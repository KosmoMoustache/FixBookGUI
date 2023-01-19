package net.kosmo.fixbookgui;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class FixBookGui implements ClientModInitializer {
        @Override
        public void onInitializeClient() {
            LogUtils.getLogger().info("FixBookGui initialized");
        }
}
