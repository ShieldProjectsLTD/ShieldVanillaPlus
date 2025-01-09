package com.shield.shieldvanillaplus.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

@Mixin(BoneMealItem.class)
public class Mixin_BoneMeal {
  @Unique
  private static final Map<Block, Block> STRIPPED_TO_ORIGINAL = new HashMap<>();

  static {
    STRIPPED_TO_ORIGINAL.put(Blocks.STRIPPED_OAK_LOG, Blocks.OAK_LOG);
    STRIPPED_TO_ORIGINAL.put(Blocks.STRIPPED_SPRUCE_LOG, Blocks.SPRUCE_LOG);
    STRIPPED_TO_ORIGINAL.put(Blocks.STRIPPED_BIRCH_LOG, Blocks.BIRCH_LOG);
    STRIPPED_TO_ORIGINAL.put(Blocks.STRIPPED_JUNGLE_LOG, Blocks.JUNGLE_LOG);
    STRIPPED_TO_ORIGINAL.put(Blocks.STRIPPED_ACACIA_LOG, Blocks.ACACIA_LOG);
    STRIPPED_TO_ORIGINAL.put(Blocks.STRIPPED_DARK_OAK_LOG, Blocks.DARK_OAK_LOG);
    STRIPPED_TO_ORIGINAL.put(Blocks.STRIPPED_MANGROVE_LOG, Blocks.MANGROVE_LOG);
    STRIPPED_TO_ORIGINAL.put(Blocks.STRIPPED_CRIMSON_STEM, Blocks.CRIMSON_STEM);
    STRIPPED_TO_ORIGINAL.put(Blocks.STRIPPED_WARPED_STEM, Blocks.WARPED_STEM);

    STRIPPED_TO_ORIGINAL.put(Blocks.STRIPPED_OAK_WOOD, Blocks.OAK_WOOD);
    STRIPPED_TO_ORIGINAL.put(Blocks.STRIPPED_SPRUCE_WOOD, Blocks.SPRUCE_WOOD);
    STRIPPED_TO_ORIGINAL.put(Blocks.STRIPPED_BIRCH_WOOD, Blocks.BIRCH_WOOD);
    STRIPPED_TO_ORIGINAL.put(Blocks.STRIPPED_JUNGLE_WOOD, Blocks.JUNGLE_WOOD);
    STRIPPED_TO_ORIGINAL.put(Blocks.STRIPPED_ACACIA_WOOD, Blocks.ACACIA_WOOD);
    STRIPPED_TO_ORIGINAL.put(Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.DARK_OAK_WOOD);
    STRIPPED_TO_ORIGINAL.put(Blocks.STRIPPED_MANGROVE_WOOD, Blocks.MANGROVE_WOOD);
    STRIPPED_TO_ORIGINAL.put(Blocks.STRIPPED_CRIMSON_HYPHAE, Blocks.CRIMSON_HYPHAE);
    STRIPPED_TO_ORIGINAL.put(Blocks.STRIPPED_WARPED_HYPHAE, Blocks.WARPED_HYPHAE);
  }


  @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
  public void useOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
    Level level = context.getLevel();
    if (level.isClientSide) return;

    Player player = context.getPlayer();
    assert player != null;
    ItemStack itemStack = player.getItemInHand(context.getHand());
    BlockPos pos = context.getClickedPos();
    BlockState state = level.getBlockState(pos);

    if (STRIPPED_TO_ORIGINAL.containsKey(state.getBlock())) {
      Block originalBlock = STRIPPED_TO_ORIGINAL.get(state.getBlock());
      level.setBlock(pos, originalBlock.defaultBlockState(), 3);

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

      if (!player.isCreative()) {
        itemStack.shrink(1);
        cir.setReturnValue(InteractionResult.CONSUME);
      }

      cir.setReturnValue(InteractionResult.SUCCESS);
    }
  }
}
