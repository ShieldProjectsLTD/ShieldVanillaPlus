package com.shield.shieldvanillaplus.loot;

import com.mojang.serialization.MapCodec;
import com.shield.shieldvanillaplus.ShieldVanillaPlusMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ShieldVanillaPlusMod.MODID);

    public static final Supplier<MapCodec<AddItemModifier>> MOD_LOOT_MODIFIER =
            LOOT_MODIFIER_SERIALIZERS.register("mod_loot_modifier", () -> AddItemModifier.CODEC);

    public static void register(IEventBus bus) {
        LOOT_MODIFIER_SERIALIZERS.register(bus);
    }
}
