package com.sayuki.minihud.neoforge;

import fi.dy.masa.malilib.registry.Registry;
import fi.dy.masa.malilib.util.data.ModInfo;
import fi.dy.masa.minihud.MiniHUD;
import fi.dy.masa.minihud.gui.GuiConfigs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import com.sayuki.malilib.neoforge.NeoForgeNetworkHelper;

@Mod(value = "minihud", dist = Dist.CLIENT)
public class MiniHudNeoForge {
    public MiniHudNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        MiniHUD.onInitialize();

        Registry.CONFIG_SCREEN.registerConfigScreenFactory(new ModInfo("minihud", "MiniHUD", GuiConfigs::new));
        modContainer.registerExtensionPoint(IConfigScreenFactory.class,
                (java.util.function.Supplier<IConfigScreenFactory>) () -> (IConfigScreenFactory) (minecraft, parent) -> {
                    GuiConfigs gui = new GuiConfigs();
                    gui.setParent(parent);
                    return gui;
                });

        modEventBus.addListener((RegisterPayloadHandlersEvent event) -> {
            NeoForgeNetworkHelper.setRegistrar(event.registrar("minihud"));
        });
    }
}
