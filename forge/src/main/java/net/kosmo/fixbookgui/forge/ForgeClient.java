package net.kosmo.fixbookgui.forge;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.kosmo.fixbookgui.common.FixBookGui;

@Mod("fixbookgui")
public class ForgeClient {
    public ForgeClient(IEventBus modEventBus) {
        FixBookGui.init();
    }
}
