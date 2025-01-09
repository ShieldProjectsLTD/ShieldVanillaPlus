package com.shield.shieldvanillaplus.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
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

@Mixin(AbstractFurnaceBlockEntity.class)
public class Mixin_FurnaceTNTBoom {

  @Unique
  private static void shieldVanillaPlus$Explode(ServerLevel world, BlockPos pos) {
    // Создаем источник урона для взрыва (например, от TNT)
    DamageSource damageSource = Explosion.getDefaultDamageSource(world, null); // null — означает, что нет конкретного источника (например, может быть использован взрыв TNT)

    // Создаем калькулятор урона для взрыва
    ExplosionDamageCalculator damageCalculator = new ExplosionDamageCalculator();

    // Создаем взрыв с дополнительными параметрами
    Explosion explosion = world.explode(null, damageSource, damageCalculator, pos.getX(), pos.getY(), pos.getZ(), 6, false, Level.ExplosionInteraction.TNT);

    // Запускаем обработку взрыва и нанесение урона
    explosion.finalizeExplosion(true);  // Это гарантирует нанесение урона сущностям и обработку взрыва
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
