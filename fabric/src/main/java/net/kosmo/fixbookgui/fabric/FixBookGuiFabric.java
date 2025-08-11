package net.kosmo.fixbookgui.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.kosmo.fixbookgui.common.FixBookGui;

public class FixBookGuiFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FixBookGui.init();
    }
}
