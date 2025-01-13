package com.shield.shieldvanillaplus.mixin;

import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlockBehaviour.Properties.class)
public interface BlockBehaviour_Properties {
  @Accessor("destroyTime")
  void setDestroyTime(float destroyTime);

  @Accessor("explosionResistance")
  void setExplosionResistance(float explosionResistance);

  @Accessor("hasCollision")
  void setHasCollision(boolean hasCollision);
}
