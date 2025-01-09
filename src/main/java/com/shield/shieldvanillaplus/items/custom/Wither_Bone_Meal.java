package com.shield.shieldvanillaplus.items.custom;

import com.shield.shieldvanillaplus.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Random;

public class Wither_Bone_Meal extends Item {
  public Wither_Bone_Meal(Properties properties) {
    super(properties);
  }

  @Override
  public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
    Level world = context.getLevel();
    Player player = context.getPlayer();
    if (world.isClientSide()) return InteractionResult.FAIL;
    assert player != null;

    BlockPos clickedPos = context.getClickedPos();
    Block clickedBlock = world.getBlockState(clickedPos).getBlock();
    BlockState clickedState = world.getBlockState(clickedPos);

    if(clickedBlock instanceof NetherWartBlock) {
      if(increaseGrowthStage(world, clickedPos, clickedState) == InteractionResult.SUCCESS) {
        // Success
        if(!player.isCreative()) {
          context.getItemInHand().shrink(1);
          playServerSound(world, clickedPos);
          return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS_NO_ITEM_USED;
      }
    }

    if(transformSurroundingBlocks(world, clickedPos) == InteractionResult.SUCCESS) {
      // Success
      if (!player.isCreative()) {
        context.getItemInHand().shrink(1);
        playServerSound(world, clickedPos);
        return InteractionResult.CONSUME;
      }
      return InteractionResult.SUCCESS_NO_ITEM_USED;
    }


    if (isCrop(clickedBlock)) {
      if(reduceGrowthStage(world, clickedPos, clickedState) == InteractionResult.SUCCESS) {
        // Success
        if(!player.isCreative()) {
          context.getItemInHand().shrink(1);
          playServerSound(world, clickedPos);
          return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS_NO_ITEM_USED;
      }
    }

    return InteractionResult.FAIL;
  }

  private void playServerSound(@NotNull Level world, BlockPos clickedPos) {
    if (world.isClientSide()) return;
    world.playSound(
            null,
            clickedPos,
            SoundEvents.BONE_MEAL_USE,
            SoundSource.BLOCKS,
            1.0f,
            0.8f + new Random().nextFloat() * (1.0f - 0.8f)
    );
  }

  private InteractionResult reduceGrowthStage(@NotNull Level world, BlockPos pos, BlockState state) {
    if (world.isClientSide()) return InteractionResult.FAIL;

    Optional<Property<?>> optionalProperty = state.getProperties()
            .stream()
            .filter(p -> p instanceof IntegerProperty && p.getName().equals("age"))
            .findFirst();

    if (optionalProperty.isPresent()) {
      IntegerProperty ageProperty = (IntegerProperty) optionalProperty.get();
      int currentAge = state.getValue(ageProperty);

      if (currentAge > 0) {
        int newAge = Math.max(0, currentAge - (new Random().nextInt(2) + 1));
        world.setBlock(pos, state.setValue(ageProperty, newAge), 3);

        if (world instanceof ServerLevel serverLevel) {
          serverLevel.sendParticles(
            ParticleTypes.ASH,
            pos.getX() + 0.5,
            pos.getY() + 0.5,
            pos.getZ() + 0.5,
            20,
            0.3, 0.3, 0.3, 0.05
          );
        }

        return InteractionResult.SUCCESS;
      } else if (currentAge == 0) {
        return InteractionResult.PASS;
      }
    } else {
      return InteractionResult.FAIL;
    }
    return InteractionResult.PASS;
  }

  private InteractionResult increaseGrowthStage(@NotNull Level world, BlockPos pos, BlockState state) {
    if (world.isClientSide()) return InteractionResult.FAIL;

    IntegerProperty ageProperty = null;
    if (state.getBlock() instanceof NetherWartBlock) {
      ageProperty = NetherWartBlock.AGE;
    }

    if (ageProperty != null) {
      int currentAge = state.getValue(ageProperty);
      if (currentAge <= 0) {
        int newAge = Math.max(0, currentAge + (new Random().nextInt(2) + 1));
        world.setBlock(pos, state.setValue(ageProperty, newAge), 3);

        if (world instanceof ServerLevel serverLevel) {
          serverLevel.sendParticles(
                  ParticleTypes.ASH,
                  pos.getX() + 0.5,
                  pos.getY() + 0.5,
                  pos.getZ() + 0.5,
                  20,
                  0.3, 0.3, 0.3, 0.05
          );
        }

        return InteractionResult.SUCCESS;
      } else if (currentAge == NetherWartBlock.MAX_AGE) {
        return InteractionResult.PASS;
      }
      return InteractionResult.FAIL;
    }
    return InteractionResult.PASS;
  }

  private InteractionResult transformSurroundingBlocks(@NotNull Level world, BlockPos centerPos) {
    if (world.isClientSide()) return InteractionResult.FAIL;

    int radius = 1;
    boolean hasChanged = false;

    for (int x = -radius; x <= radius; x++) {
      for (int y = -radius; y <= radius; y++) {
        for (int z = -radius; z <= radius; z++) {
          BlockPos targetPos = centerPos.offset(x, y, z);
          BlockState targetState = world.getBlockState(targetPos);

          if (targetState.is(ModTags.Blocks.TRANSFORMABLE_BLOCKS_WITHER_BM)) {
            if(targetState.is(Blocks.FARMLAND)) {
              BlockPos cropPos = targetPos.above();
              BlockState cropState = world.getBlockState(cropPos);

              if(isCrop(cropState.getBlock())) continue;
            }

            world.setBlock(targetPos, Blocks.DIRT.defaultBlockState(), 3);
            hasChanged = true;
          }

          else if (targetState.is(ModTags.Blocks.DESTROY_NATURE_BLOCKS_WITHER_BM)) {
            world.removeBlock(targetPos, false);
            hasChanged = true;
          }

          else if (targetState.is(ModTags.Blocks.TRANSFORM_TO_DEAD_BUSH_WITHER_BM)) {
            world.setBlock(targetPos, Blocks.DEAD_BUSH.defaultBlockState(), 3);
            hasChanged = true;
          }

          else {
            continue;
          }

          if (world instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.ASH,
                    targetPos.getX() + 0.5,
                    targetPos.getY() + 1,
                    targetPos.getZ() + 0.5,
                    10,
                    0.3, 0.3, 0.3, 0.05
            );
          }
        }
      }
    }

    return hasChanged ? InteractionResult.SUCCESS : InteractionResult.PASS;
  }

  private boolean isCrop(Block block) {
    return block instanceof CropBlock ||
            block == Blocks.MELON_STEM ||
            block == Blocks.PUMPKIN_STEM ||
            block == Blocks.COCOA ||
            block == Blocks.SWEET_BERRY_BUSH;
  }
}
