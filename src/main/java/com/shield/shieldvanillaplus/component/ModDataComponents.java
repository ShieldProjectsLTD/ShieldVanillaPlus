package com.shield.shieldvanillaplus.component;

import com.mojang.serialization.Codec;
import com.shield.shieldvanillaplus.ShieldVanillaPlusMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class ModDataComponents {
  public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
          DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, ShieldVanillaPlusMod.MODID);

  public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> COORDINATES = register("coordinates",
          builder -> builder.persistent(BlockPos.CODEC));

  public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> MINING_SPEED = register("mining_speed",
          builder -> builder.persistent(Codec.FLOAT));  // Используем Float для хранения скорости майнинга



  private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
                                                                                        UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
    return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
  }

  public static void register(IEventBus eventBus) {
    DATA_COMPONENT_TYPES.register(eventBus);
  }
}
