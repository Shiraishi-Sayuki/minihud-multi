package com.sayuki.minihud.fabric;

import net.fabricmc.api.ModInitializer;
import fi.dy.masa.minihud.MiniHUD;
import fi.dy.masa.minihud.Reference;
import fi.dy.masa.minihud.gui.GuiConfigs;
import fi.dy.masa.malilib.registry.Registry;
import fi.dy.masa.malilib.util.data.ModInfo;

// Fabricエントリポイント - fabric.mod.jsonのmainから呼ばれる、共通初期化に橋渡しするだけ
// MOD自体はクライアント限定(environment: client)なのでModInitializerで問題ない
public class MiniHudFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MiniHUD.onInitialize();
        Registry.CONFIG_SCREEN.registerConfigScreenFactory(
                new ModInfo(Reference.MOD_ID, Reference.MOD_NAME, GuiConfigs::new)
        );
    }
}
