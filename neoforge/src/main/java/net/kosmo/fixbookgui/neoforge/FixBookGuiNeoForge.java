package net.kosmo.fixbookgui.neoforge;

import net.kosmo.fixbookgui.common.FixBookGui;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = FixBookGui.MOD_ID/*? =1.20.4 {*//*?} else {*/, dist = Dist.CLIENT/*?}*/)
public class FixBookGuiNeoForge {
    public FixBookGuiNeoForge(ModContainer modContainer, IEventBus modEventBus) {
        FixBookGui.init();
    }
}
