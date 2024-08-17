package net.kosmo.fixbookgui.neoforge;

import net.kosmo.fixbookgui.common.FixBookGui;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod("fixbookgui")
public class NeoForgeClient {
    public NeoForgeClient(IEventBus modEventBus) {
        FixBookGui.init();
    }
}
