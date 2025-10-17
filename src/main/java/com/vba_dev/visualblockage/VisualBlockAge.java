package com.vba_dev.visualblockage;

import com.vba_dev.visualblockage.config.ConfigManager;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VisualBlockAge implements ModInitializer {
    public static final String MOD_ID = "visualblockage";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Visual Block Age is initializing.");
        ConfigManager.getConfig(); // Initialize the config
    }
}
