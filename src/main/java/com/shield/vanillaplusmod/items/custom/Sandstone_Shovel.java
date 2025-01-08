package com.shield.vanillaplusmod.items.custom;

import com.shield.vanillaplusmod.enchantment.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.RegistryManager;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Sandstone_Shovel extends ShovelItem {
  private static final Logger log = LoggerFactory.getLogger(Sandstone_Shovel.class);
  private final float sandDestroyBoost = 2.0F;

  public Sandstone_Shovel(Tier tier, Properties properties) {
    super(tier, properties);
  }

  @Override
  public boolean isFoil(@NotNull ItemStack stack) {
    return true;
  }

  @Override
  public float getDestroySpeed(@NotNull ItemStack stack, BlockState state) {
    if (state.is(BlockTags.SAND)) {
      return super.getDestroySpeed(stack, state) * sandDestroyBoost;
    }
    return super.getDestroySpeed(stack, state);
  }

  @Override
  public @NotNull ItemStack getDefaultInstance() {
    ItemStack stack = super.getDefaultInstance();

    Registry<Enchantment> enchantmentRegistry = RegistryAccess.EMPTY.registryOrThrow(Registries.ENCHANTMENT);
    Holder<Enchantment> enchantmentHolder = enchantmentRegistry.getHolderOrThrow(ModEnchantments.PHARAOH_WORKER);
    EnchantmentInstance pharaohWorkerEnchantment = new EnchantmentInstance(enchantmentHolder, 1);
    List<EnchantmentInstance> enchantments = List.of(pharaohWorkerEnchantment);

    stack = applyEnchantments(stack, enchantments);
    return stack;
  }
}
