package com.shield.shieldvanillaplus.mechanics;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class PlaceMushroomOnGrass {
//  private static final Random random = new Random();

//  public static boolean placeMushroom(Level world, Player player, InteractionHand hand, BlockPos pos) {
//    if (world.isClientSide) {
//      return true;
//    }
//
//    ItemStack itemStack = player.getItemInHand(hand);
//    Item item = itemStack.getItem();
//    Block block = Block.byItem(item);
//    if (!(block instanceof MushroomBlock)) {
//      return true;
//    }
//
//    BlockState state = world.getBlockState(pos);
//    if (!state.isSolidRender(world, pos)) {
//      return true;
//    }
//
//    BlockPos abovePos = pos.above();
//    Block aboveBlock = world.getBlockState(abovePos).getBlock();
//    if (aboveBlock.equals(Blocks.AIR)) {
//      BlockState placeState = block.defaultBlockState();
//      world.setBlock(abovePos, placeState, 3);
//
//      player.swing(hand);
//
//      if (!player.isCreative()) {
//        itemStack.shrink(1);
//      }
//
//      world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GRASS_PLACE, SoundSource.BLOCKS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
//      return false;
//    }
//
//    return true;
//  }
}
