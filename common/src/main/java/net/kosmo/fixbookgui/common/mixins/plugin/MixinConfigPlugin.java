//? if 1.21 || 1.21.1 {
/*package net.kosmo.fixbookgui.common.mixins.plugin;

import net.kosmo.fixbookgui.common.FixBookGui;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public abstract class MixinConfigPlugin implements IMixinConfigPlugin {

    @Override
    public boolean shouldApplyMixin(
            String targetClassName,
            String mixinClassName
    ) {
        String modId = getRequiredMod(mixinClassName);

        return modId == null || isModLoaded(modId);
    }

    protected abstract boolean isModLoaded(String modId);

    private String getRequiredMod(String mixinClassName) {
        if (mixinClassName.contains(".compat.amendments.")) {
            return "amendments";
        }

        return null;
    }

    /^*
     *
     *
     ^/

    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return "";
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return List.of();
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
*///? }
