package com.shield.shieldvanillaplus.datagen;

import com.shield.shieldvanillaplus.ShieldVanillaPlusMod;
import com.shield.shieldvanillaplus.enchantment.ModEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackProvider extends DatapackBuiltinEntriesProvider {
  public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
          .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap);

  public ModDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
    super(output, registries, BUILDER, Set.of(ShieldVanillaPlusMod.MODID));
  }
}