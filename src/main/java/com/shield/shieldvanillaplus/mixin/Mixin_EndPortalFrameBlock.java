package com.shield.shieldvanillaplus.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EndPortalFrameBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EndPortalFrameBlock.class)
public class Mixin_EndPortalFrameBlock {

    @ModifyArgs(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;strength(FF)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;"
            )
    )
    private static void modifyStrength(Block instance, BlockBehaviour.Properties properties) {
        properties.strength(5.0F, 6.0F);
    }
}
