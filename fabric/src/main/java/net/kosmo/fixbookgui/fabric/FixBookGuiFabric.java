package net.kosmo.fixbookgui.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.kosmo.fixbookgui.common.FixBookGui;
//? if mixin_debug
//import org.spongepowered.asm.mixin.MixinEnvironment;

public class FixBookGuiFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FixBookGui.init();
        //? if mixin_debug
        //MixinEnvironment.getCurrentEnvironment().audit();
    }
}
