package com.sayuki.minihud.fabric;

import net.fabricmc.api.ModInitializer;
import fi.dy.masa.minihud.MiniHUD;

public class MiniHudFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MiniHUD.onInitialize();
    }
}
