package net.kosmo.fixbookgui.neoforge;

import net.kosmo.fixbookgui.common.FixBookGui;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

//? if = 1.20.4 {
/*@Mod(value = FixBookGui.MOD_ID)
 *///?} else {
@Mod(value = FixBookGui.MOD_ID, dist = Dist.CLIENT)
//?}
public class FixBookGuiNeoForge {
    public FixBookGuiNeoForge(ModContainer modContainer, IEventBus modEventBus) {
        FixBookGui.init();
    }
}
