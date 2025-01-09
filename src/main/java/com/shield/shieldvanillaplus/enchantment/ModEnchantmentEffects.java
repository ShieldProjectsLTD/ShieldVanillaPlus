package com.shield.shieldvanillaplus.enchantment;

import com.mojang.serialization.MapCodec;
import com.shield.shieldvanillaplus.ShieldVanillaPlusMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEnchantmentEffects {
  public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
          DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, ShieldVanillaPlusMod.MODID);

  public static void register(IEventBus eventBus) {
    ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
  }
}
