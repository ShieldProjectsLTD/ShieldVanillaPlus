package com.shield.shieldvanillaplus.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class CustomExplosionDamageCalculator extends ExplosionDamageCalculator {
  @Override
  public @NotNull Optional<Float> getBlockExplosionResistance(@NotNull Explosion explosion, @NotNull BlockGetter reader, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull FluidState fluid) {
    return Optional.of(0.0F);
  }

  @Override
  public boolean shouldBlockExplode(@NotNull Explosion explosion, @NotNull BlockGetter reader, @NotNull BlockPos pos, @NotNull BlockState state, float power) {
    return true;
  }
}
