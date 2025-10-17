package com.vba_dev.visualblockage.config;

import io.wispforest.owo.config.ui.ConfigScreen;

public class ConfigManager {

    public static final ModConfigWrapper CONFIG = ModConfigWrapper.createAndLoad();

    public static ModConfig getConfig() {
        return CONFIG.config;
    }

    public static ConfigScreen getConfigScreen(net.minecraft.client.gui.screen.Screen parent) {
        return CONFIG.createScreen(parent);
    }
}
