package com.shield.shieldvanillaplus.util;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ItemFunctions {
  public static void shrinkGiveOrDropItemStack(Player player, InteractionHand hand, ItemStack used, ItemStack give) {
    used.shrink(1);

    if (used.isEmpty()) {
      Item giveitem = give.getItem();
      int maxStackSize = give.getMaxStackSize();
      List<ItemStack> inventory = player.getInventory().items;

      boolean increased = false;
      for (ItemStack slot : inventory) {
        if (slot.getItem().equals(giveitem)) {
          int slotCount = slot.getCount();
          if (slotCount < maxStackSize) {
            slot.setCount(slotCount + 1);
            increased = true;
            break;
          }
        }
      }

      if (!increased) {
        player.setItemInHand(hand, give);
      }
    }
    else if (!player.getInventory().add(give)) {
      player.drop(give, false);
    }
  }

  public static void giveOrDropItemStack(Player player, ItemStack give) {
    if (!player.getInventory().add(give)) {
      player.drop(give, false);
    }
  }
}
