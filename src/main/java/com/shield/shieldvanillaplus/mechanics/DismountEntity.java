package com.shield.shieldvanillaplus.mechanics;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class DismountEntity {
  public static InteractionResult onPlayerInteract(Player player, Level world, Entity target) {
    if (world.isClientSide) {
      return InteractionResult.PASS;
    }

    if (!player.isCrouching()) {
      return InteractionResult.PASS;
    }

    List<Entity> passengers = target.getPassengers();
    if (!passengers.isEmpty()) {
      boolean nonPlayerDismounted = false;
      for (Entity entity : passengers) {
        if (!(entity instanceof Player)) {
          entity.stopRiding();
          nonPlayerDismounted = true;
        }
      }

      if (nonPlayerDismounted) return InteractionResult.SUCCESS;

      if (passengers.size() == 1 && passengers.getFirst() instanceof Player) {
        passengers.getFirst().stopRiding();
        return InteractionResult.SUCCESS;
      }
    }

    return InteractionResult.PASS;
  }
}
