package com.vba_dev.visualblockage.rendering;

import com.vba_dev.visualblockage.config.ConfigManager;
import com.vba_dev.visualblockage.tracking.BlockAgeTracker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class BlockPlaceMixin {

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "placeBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getStackInHand(Lnet/minecraft/util/Hand;)Lnet/minecraft/item/ItemStack;", shift = At.Shift.AFTER), cancellable = true)
    private void onPlaceBlock(Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        if (ConfigManager.getConfig().enabled && this.client.world != null) {
            BlockPos pos = hitResult.getBlockPos().offset(hitResult.getSide());
            ItemStack stack = client.player.getStackInHand(hand);
            String blockId = net.minecraft.util.registry.Registry.ITEM.getId(stack.getItem()).toString();

            if (ConfigManager.getConfig().trackedBlocks.contains(blockId)) {
                BlockAgeTracker.addTrackedBlock(pos, this.client.world.getTime());
            }
        }
    }
}
