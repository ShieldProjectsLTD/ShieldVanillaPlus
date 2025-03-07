package com.shield.shieldvanillaplus.events;

import com.shield.shieldvanillaplus.mechanics.BottleOxygen;
import com.shield.shieldvanillaplus.mechanics.DismountEntity;
import com.shield.shieldvanillaplus.ShieldVanillaPlusMod;
import com.shield.shieldvanillaplus.mechanics.SeedGrowthGrassBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = ShieldVanillaPlusMod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

  @SubscribeEvent
  public static void RepairAnvil(PlayerInteractEvent.RightClickBlock event) {
    if (event.getLevel().isClientSide) {
      return;
    }

    if (event.getEntity() instanceof Player player && player.isShiftKeyDown()) {
      ItemStack itemStack = player.getItemInHand(event.getHand());
      Item item = itemStack.getItem();
      if (!item.equals(Items.IRON_INGOT) && !item.equals(Items.OBSIDIAN)) {
        return;
      }

      BlockPos pos = event.getPos();
      BlockState state = event.getLevel().getBlockState(pos);
      Block block = state.getBlock();

      BlockState newState;
      if (block.equals(Blocks.ANVIL) && item.equals(Items.OBSIDIAN)) {
        newState = Blocks.CHIPPED_ANVIL.defaultBlockState();
      } else if (block.equals(Blocks.CHIPPED_ANVIL)) {
        if (item.equals(Items.IRON_INGOT)) {
          newState = Blocks.ANVIL.defaultBlockState();
        } else { // obsidian
          newState = Blocks.DAMAGED_ANVIL.defaultBlockState();
        }
      } else if (block.equals(Blocks.DAMAGED_ANVIL) && item.equals(Items.IRON_INGOT)) {
        newState = Blocks.CHIPPED_ANVIL.defaultBlockState();
      } else {
        return;
      }

      Direction rotation = state.getValue(AnvilBlock.FACING);
      event.getLevel().setBlock(pos, newState.setValue(AnvilBlock.FACING, rotation), 3);

      if (item.equals(Items.IRON_INGOT)) {
        if (!player.isCreative()) {
          itemStack.shrink(1);
        }
        event.getLevel().playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 0.5f, 0.8f);
      } else {
        event.getLevel().playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ANVIL_BREAK, SoundSource.BLOCKS, 0.5f, 0.8f);
      }
    }
  }

  @SubscribeEvent
  public static void OnPlantSeeds(PlayerInteractEvent.RightClickBlock event) {
    SeedGrowthGrassBlock.growthGrass(event.getLevel(), event.getEntity(), event.getHand(), event.getPos());
  }

  @SubscribeEvent
  public static void dismountEvent(PlayerInteractEvent.EntityInteract event) {
    if (DismountEntity.onPlayerInteract(event.getEntity(), event.getLevel(), event.getTarget()).equals(InteractionResult.SUCCESS)) {
      event.setCanceled(true);
    }
  }

  @SubscribeEvent
  public static void bottleOxygenEvent(PlayerInteractEvent.RightClickItem event) {
    if (BottleOxygen.onBottleClick(event.getEntity(), event.getHand()).equals(InteractionResult.SUCCESS)) {
      event.setCanceled(true);
    }
  }
}
