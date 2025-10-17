package com.vba_dev.visualblockage;

import com.vba_dev.visualblockage.config.ConfigManager;
import com.vba_dev.visualblockage.tracking.BlockAgeTracker;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VisualBlockAgeClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (ConfigManager.getConfig().enabled) {
                BlockAgeTracker.tick();
            }
        });

        PlayerBlockBreakEvents.AFTER.register(this::onBlockBroken);
    }

    private void onBlockBroken(World world, PlayerEntity player, BlockPos pos, BlockState state, net.minecraft.block.entity.BlockEntity blockEntity) {
        if (world.isClient) {
            BlockAgeTracker.removeTrackedBlock(pos);
        }
    }
}
