package com.shield.shieldvanillaplus.mechanics;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;

import java.util.Random;

public class BottleOxygen {
  public static Random random = new Random();

  public static InteractionResult onBottleClick(Player player, InteractionHand hand) {
    ItemStack stack = player.getItemInHand(hand);
    if (!player.isInWater()) {
      return InteractionResult.PASS;
    }

    Item stackItem = stack.getItem();
    if (!stackItem.equals(Items.GLASS_BOTTLE)) {
      if (stackItem.equals(Items.POTION)) {
        PotionContents potionContents = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        if (potionContents.is(Potions.WATER)) {
          if (player.isUnderWater()) {
            return InteractionResult.SUCCESS;
          }
        }
      }
      return InteractionResult.PASS;
    }

    int maxAir = player.getMaxAirSupply();
    int air = player.getAirSupply();

    if (air >= maxAir) {
      return InteractionResult.PASS;
    }

    int newAir = air + 100;
    if (newAir > maxAir) {
      newAir = maxAir;
    }

    player.setAirSupply(newAir);

    return InteractionResult.PASS;
  }
}
