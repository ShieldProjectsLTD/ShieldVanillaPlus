package com.shield.shieldvanillaplus.util;

import net.minecraft.world.InteractionHand;

public class PlayerFunctions {
  public static InteractionHand getOtherHand(InteractionHand hand) {
    if (hand.equals(InteractionHand.MAIN_HAND)) {
      return InteractionHand.OFF_HAND;
    }
    return InteractionHand.MAIN_HAND;
  }
}
