package com.shield.shieldvanillaplus.mechanics;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class SeedGrowthGrassBlock {
  public static boolean growthGrass(Level world, Player player, InteractionHand hand, BlockPos pos) {
    if (world.isClientSide) {
      return true;
    }

    ItemStack stack = player.getItemInHand(hand);
    if (!stack.getItem().equals(Items.WHEAT_SEEDS)) {
      return true;
    }

    Block block = world.getBlockState(pos).getBlock();
    if (block.equals(Blocks.DIRT)) {
      world.setBlockAndUpdate(pos, Blocks.GRASS_BLOCK.defaultBlockState());
    }
    else if (block.equals(Blocks.GRASS_BLOCK)) {
      BlockPos up = pos.above();
      if (world.getBlockState(up).getBlock().equals(Blocks.AIR)) {
        world.setBlockAndUpdate(up, Blocks.SHORT_GRASS.defaultBlockState());
      }
      else if (world.getBlockState(up).getBlock().equals(Blocks.SHORT_GRASS)) {
        upgradeGrass(world, up);
      }
      else {
        return true;
      }
    }
    else if (block.equals(Blocks.SHORT_GRASS)) {
      upgradeGrass(world, pos);
    }
    else {
      return true;
    }

    if (!player.isCreative()) {
      stack.shrink(1);
    }

    return true;
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
