package com.vba_dev.visualblockage.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.RangeConstraint;
import io.wispforest.owo.config.annotation.SectionHeader;

import java.util.ArrayList;
import java.util.List;

@Modmenu(modId = "visualblockage")
@Config(name = "visualblockage-config", wrapperName = "ModConfigWrapper")
public class ModConfig {

    @SectionHeader("General")
    public boolean enabled = true;

    @SectionHeader("Tracking")
    @RangeConstraint(min = 1, max = 1000)
    public int tickRate = 100;
    public List<String> trackedBlocks = new ArrayList<>(List.of("minecraft:cactus"));
}
