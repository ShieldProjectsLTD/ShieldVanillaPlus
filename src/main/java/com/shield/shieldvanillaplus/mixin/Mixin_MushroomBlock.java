package com.shield.shieldvanillaplus.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MushroomBlock.class)
public class Mixin_MushroomBlock {

  @Inject(method = "canSurvive", at = @At("HEAD"), cancellable = true)
  private void onCanSurvive(BlockState state, LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
    BlockPos blockpos = pos.below();
    BlockState blockstate = level.getBlockState(blockpos);

    if (
        blockstate.is(Blocks.DIRT) ||
        blockstate.is(Blocks.GRASS_BLOCK) ||
        blockstate.is(Blocks.PODZOL) ||
        blockstate.is(Blocks.COARSE_DIRT) ||
        blockstate.is(Blocks.ROOTED_DIRT)
    ){
      cir.setReturnValue(true);
    }
  }
}
