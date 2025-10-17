package com.vba_dev.visualblockage.rendering;

import com.vba_dev.visualblockage.config.ConfigManager;
import com.vba_dev.visualblockage.tracking.BlockAgeData;
import com.vba_dev.visualblockage.tracking.BlockAgeTracker;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientWorld.class)
public abstract class CustomBlockStateProvider {

    @Inject(method = "getBlockState", at = @At("RETURN"), cancellable = true)
    private void getBlockState(BlockPos pos, CallbackInfoReturnable<BlockState> cir) {
        if (!ConfigManager.getConfig().enabled) {
            return;
        }

        BlockState originalState = cir.getReturnValue();
        Block block = originalState.getBlock();
        String blockId = Registry.BLOCK.getId(block).toString();

        if (ConfigManager.getConfig().trackedBlocks.contains(blockId)) {
            BlockAgeData data = BlockAgeTracker.getTrackedBlock(pos);
            if (data != null && originalState.contains(Properties.AGE_15)) {
                int age = data.getEstimatedAge();
                age = Math.min(15, Math.max(0, age));
                try {
                    BlockState modifiedState = originalState.with(Properties.AGE_15, age);
                    cir.setReturnValue(modifiedState);
                } catch (IllegalArgumentException e) {
                    // This can happen if the block doesn't have the AGE property,
                    // but the contains() check should prevent this.
                }
            }
        }
    }
}
