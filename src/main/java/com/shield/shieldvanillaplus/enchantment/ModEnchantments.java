package com.shield.shieldvanillaplus.enchantment;

import com.shield.shieldvanillaplus.ShieldVanillaPlusMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.*;


public class ModEnchantments {
  public static final ResourceKey<Enchantment> PHARAOH_WORKER = ResourceKey.create(Registries.ENCHANTMENT,
          ResourceLocation.fromNamespaceAndPath(ShieldVanillaPlusMod.MODID, "pharaoh_worker"));

  public static void bootstrap(BootstrapContext<Enchantment> context) {
    var items = context.lookup(Registries.ITEM);

    register(context, PHARAOH_WORKER, Enchantment.enchantment(Enchantment.definition(
            items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
            1,
            1,
            Enchantment.dynamicCost(1, 1),
            Enchantment.dynamicCost(1, 1),
            1,
            EquipmentSlotGroup.MAINHAND))
    );
  }

  private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key,
                               Enchantment.Builder builder) {
    registry.register(key, builder.build(key.location()));
  }
}
