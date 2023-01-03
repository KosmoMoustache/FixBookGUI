package net.kosmo.fixbookgui;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.slf4j.Logger;

@Environment(EnvType.CLIENT)
public class FixBookGui implements ClientModInitializer {
    private static final Logger LOGGER = LogUtils.getLogger();
        @Override
        public void onInitializeClient() {
            LOGGER.info("FixBookGui initialized");
        }
}
