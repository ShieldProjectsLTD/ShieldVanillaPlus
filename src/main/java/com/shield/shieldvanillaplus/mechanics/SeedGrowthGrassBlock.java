package com.shield.shieldvanillaplus.mechanics;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;


public class SeedGrowthGrassBlock {
  public static InteractionResult growthGrass(Level world, Player player, InteractionHand hand, BlockPos pos) {
    if (world.isClientSide) {
      return InteractionResult.FAIL;
    }

    BlockState state = world.getBlockState(pos);

    if (player.getItemInHand(hand).is(Items.WHEAT_SEEDS) && state.is(Blocks.DIRT)) {
      world.setBlock(pos, Blocks.GRASS_BLOCK.defaultBlockState(), 3);

      if (!player.isCreative()) {
        player.getItemInHand(hand).shrink(1);
      }

      if (world instanceof ServerLevel serverLevel) {
        serverLevel.sendParticles(
                net.minecraft.core.particles.ParticleTypes.HAPPY_VILLAGER,
                pos.getX() + 0.5,
                pos.getY() + 1.0,
                pos.getZ() + 0.5,
                5,
                0.2, 0.2, 0.2, 0.05
        );
      }

      return InteractionResult.SUCCESS;
    }
    return InteractionResult.PASS;
  }

  private static void upgradeGrass(Level world, BlockPos pos) {
    DoublePlantBlock blockDoublePlant = (DoublePlantBlock)Blocks.TALL_GRASS;
    BlockState doublePlantState = blockDoublePlant.defaultBlockState();
    if (doublePlantState.canSurvive(world, pos) && world.isEmptyBlock(pos.above())) {
      world.setBlock(pos, blockDoublePlant.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER), 2);
      world.setBlock(pos.above(), blockDoublePlant.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER), 2);
    }
  }
}
