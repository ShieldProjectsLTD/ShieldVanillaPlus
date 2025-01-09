package com.shield.shieldvanillaplus.events;

import com.shield.shieldvanillaplus.ShieldVanillaPlusMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.Map;

@EventBusSubscriber(modid = ShieldVanillaPlusMod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

  private static final Map<Block, Block> STRIPPED_TO_ORIGINAL = Map.of(
          net.minecraft.world.level.block.Blocks.STRIPPED_OAK_LOG, net.minecraft.world.level.block.Blocks.OAK_LOG,
          net.minecraft.world.level.block.Blocks.STRIPPED_SPRUCE_LOG, net.minecraft.world.level.block.Blocks.SPRUCE_LOG,
          net.minecraft.world.level.block.Blocks.STRIPPED_BIRCH_LOG, net.minecraft.world.level.block.Blocks.BIRCH_LOG,
          net.minecraft.world.level.block.Blocks.STRIPPED_JUNGLE_LOG, net.minecraft.world.level.block.Blocks.JUNGLE_LOG,
          net.minecraft.world.level.block.Blocks.STRIPPED_ACACIA_LOG, net.minecraft.world.level.block.Blocks.ACACIA_LOG,
          net.minecraft.world.level.block.Blocks.STRIPPED_DARK_OAK_LOG, net.minecraft.world.level.block.Blocks.DARK_OAK_LOG,
          net.minecraft.world.level.block.Blocks.STRIPPED_MANGROVE_LOG, net.minecraft.world.level.block.Blocks.MANGROVE_LOG,
          net.minecraft.world.level.block.Blocks.STRIPPED_CRIMSON_STEM, net.minecraft.world.level.block.Blocks.CRIMSON_STEM,
          net.minecraft.world.level.block.Blocks.STRIPPED_WARPED_STEM, net.minecraft.world.level.block.Blocks.WARPED_STEM
  );

  @SubscribeEvent
  public static void RepairAnvilMechanic(PlayerInteractEvent.RightClickBlock event) {
    if(event.getLevel().isClientSide) {
      return;
    }

    if (event.getEntity() instanceof Player player && player.isShiftKeyDown()) {
      ItemStack itemStack = player.getItemInHand(event.getHand());
      Item item = itemStack.getItem();
      if(!item.equals(Items.IRON_INGOT) && !item.equals(Items.OBSIDIAN)) {
        return;
      }

      BlockPos pos = event.getPos();
      BlockState state = event.getLevel().getBlockState(pos);
      Block block = state.getBlock();

      BlockState newState;
      if(block.equals(Blocks.ANVIL) && item.equals(Items.OBSIDIAN)) {
        newState = Blocks.CHIPPED_ANVIL.defaultBlockState();
      } else if (block.equals(Blocks.CHIPPED_ANVIL)) {
        if(item.equals(Items.IRON_INGOT)) {
          newState = Blocks.ANVIL.defaultBlockState();
        }
        else { // obsidian
          newState = Blocks.DAMAGED_ANVIL.defaultBlockState();
        }
      } else if (block.equals(Blocks.DAMAGED_ANVIL) && item.equals(Items.IRON_INGOT)) {
        newState = Blocks.CHIPPED_ANVIL.defaultBlockState();
      }
      else {
        return;
      }

      Direction rotation = state.getValue(AnvilBlock.FACING);
      event.getLevel().setBlock(pos, newState.setValue(AnvilBlock.FACING, rotation), 3);

      if (item.equals(Items.IRON_INGOT)) {
        if (!player.isCreative()) {
          itemStack.shrink(1);
        }
        event.getLevel().playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 0.5f, 0.8f);
      }
      else {
        event.getLevel().playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ANVIL_BREAK, SoundSource.BLOCKS, 0.5f, 0.8f);
      }
    }
  }

  @SubscribeEvent
  public static void TNTFurnaceBoom(LevelTickEvent.Pre event) {
    Level level = event.getLevel();
    if (level.isClientSide) {
      return;
    }

  }

  @SubscribeEvent
  public static void OnPlantSeeds(PlayerInteractEvent.RightClickBlock event) {
    Level level = event.getLevel();
    if (level.isClientSide) {
      return;
    }

    Player player = event.getEntity();
    InteractionHand hand = event.getHand();
    BlockPos pos = event.getPos();
    BlockState state = level.getBlockState(pos);

    if (player.getItemInHand(hand).is(Items.WHEAT_SEEDS) && state.is(Blocks.DIRT)) {
      level.setBlock(pos, Blocks.GRASS_BLOCK.defaultBlockState(), 3);

      if (!player.isCreative()) {
        player.getItemInHand(hand).shrink(1);
      }

      if (level instanceof ServerLevel serverLevel) {
        serverLevel.sendParticles(
                net.minecraft.core.particles.ParticleTypes.HAPPY_VILLAGER,
                pos.getX() + 0.5,
                pos.getY() + 1.0,
                pos.getZ() + 0.5,
                5,
                0.2, 0.2, 0.2, 0.05
        );
      }

      event.setCanceled(true);
      event.setCancellationResult(InteractionResult.SUCCESS);
    }
  }

  @SubscribeEvent
  public static void RestorationWoodWithBoneMeal(PlayerInteractEvent.RightClickBlock event) {
    Level level = event.getLevel();
    if (level.isClientSide) {
      return;
    }

    Player player = event.getEntity();
    BlockPos pos = event.getPos();
    BlockState state = level.getBlockState(pos);
    InteractionHand hand = event.getHand();

    if (player.getItemInHand(hand).is(Items.BONE_MEAL)) {
      Block originalBlock = STRIPPED_TO_ORIGINAL.get(state.getBlock());

      if (originalBlock != null) {
        level.setBlock(pos, originalBlock.defaultBlockState(), 3);

        if (!player.isCreative()) {
          player.getItemInHand(hand).shrink(1);
        }

        if (level instanceof ServerLevel serverLevel) {
          serverLevel.sendParticles(
            ParticleTypes.HAPPY_VILLAGER,
            pos.getX() + 0.5,
            pos.getY() + 1.0,
            pos.getZ() + 0.5,
            5,
            0.2, 0.2, 0.2, 0.05
          );
        }

        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.SUCCESS);
      }
    }
  }
}
