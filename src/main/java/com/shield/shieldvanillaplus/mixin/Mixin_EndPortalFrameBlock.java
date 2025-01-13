package com.shield.shieldvanillaplus.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EndPortalFrameBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(EndPortalFrameBlock.class)
public abstract class Mixin_EndPortalFrameBlock extends Block {
  public Mixin_EndPortalFrameBlock(BlockBehaviour.Properties properties) {
    super(properties);
  }

  @ModifyArgs(
          method = "<init>",
          at = @At(
                  value = "INVOKE",
                  target = "Lnet/minecraft/world/level/block/Block;<init>(Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V"
          )
  )
  private static void modifyConstructorArgs(Args args) {
    BlockBehaviour.Properties properties = args.get(0);
    // Изменяем параметры блока
    properties.destroyTime(5.0F);
    properties.explosionResistance(1200.0F);
    args.set(0, properties);
  }
}
