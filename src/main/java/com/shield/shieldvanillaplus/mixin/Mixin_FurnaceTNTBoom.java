package com.shield.shieldvanillaplus.mixin;

import com.shield.shieldvanillaplus.util.CustomExplosionDamageCalculator;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

import net.minecraft.world.level.Explosion;


@Mixin(AbstractFurnaceBlockEntity.class)
public class Mixin_FurnaceTNTBoom {

  @Unique
  private static void shieldVanillaPlus$Explode(ServerLevel world, BlockPos pos) {
    DamageSource damageSource = Explosion.getDefaultDamageSource(world, null);
    ExplosionDamageCalculator damageCalculator = new CustomExplosionDamageCalculator();
    Explosion explosion = world.explode(
            null,
            damageSource,
            damageCalculator,
            pos.getX(), pos.getY() + 1.0f, pos.getZ(),
            6.0F,
            false,
            Level.ExplosionInteraction.TNT
    );
    explosion.finalizeExplosion(true);
  }



  @Inject(method = "serverTick", at = @At("HEAD"))
  private static void serverTick(Level level, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity blockEntity, CallbackInfo ci) {
    if(blockEntity != null) {
      Vec3 vec3Furnace = new Vec3(pos.getX(), pos.getY(), pos.getZ());
      ServerLevel serverLevel = (ServerLevel) level;

      List<RecipeHolder<?>> d = blockEntity.getRecipesToAwardAndPopExperience(serverLevel, vec3Furnace);

      if (!d.isEmpty()) {
        RecipeHolder<?> recipe = d.getFirst();
        assert recipe != null;

        if (Objects.equals(recipe.id().toString(), "minecraft:smelting_tnt_to_gunpowder")) {
          shieldVanillaPlus$Explode(serverLevel, pos);
          System.out.println("ok boomer");
        }
      }
    }
  }

}
