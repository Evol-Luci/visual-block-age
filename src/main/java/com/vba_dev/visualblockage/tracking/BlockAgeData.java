package com.vba_dev.visualblockage.tracking;

import net.minecraft.util.math.BlockPos;

public class BlockAgeData {
    private final BlockPos pos;
    private long placementTime;
    private int estimatedAge;

    public BlockAgeData(BlockPos pos, long placementTime) {
        this.pos = pos;
        this.placementTime = placementTime;
        this.estimatedAge = 0;
    }

    public BlockPos getPos() {
        return pos;
    }

    public long getPlacementTime() {
        return placementTime;
    }

    public int getEstimatedAge() {
        return estimatedAge;
    }

    public void setEstimatedAge(int estimatedAge) {
        this.estimatedAge = estimatedAge;
    }
}
