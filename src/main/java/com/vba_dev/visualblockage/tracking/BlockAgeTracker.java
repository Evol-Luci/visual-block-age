package com.vba_dev.visualblockage.tracking;

import com.vba_dev.visualblockage.config.ConfigManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class BlockAgeTracker {
    private static final Map<BlockPos, BlockAgeData> trackedBlocks = new ConcurrentHashMap<>();
    private static final Random random = new Random();

    public static void addTrackedBlock(BlockPos pos, long placementTime) {
        trackedBlocks.put(pos, new BlockAgeData(pos, placementTime));
    }

    public static void removeTrackedBlock(BlockPos pos) {
        trackedBlocks.remove(pos);
    }

    public static BlockAgeData getTrackedBlock(BlockPos pos) {
        return trackedBlocks.get(pos);
    }

    public static void tick() {
        long currentTime = MinecraftClient.getInstance().world.getTime();
        int tickRate = ConfigManager.getConfig().tickRate;

        for (BlockAgeData data : trackedBlocks.values()) {
            long timePassed = currentTime - data.getPlacementTime();
            int estimatedAge = (int) (timePassed / tickRate);
            data.setEstimatedAge(Math.min(15, estimatedAge));
        }
    }
}
