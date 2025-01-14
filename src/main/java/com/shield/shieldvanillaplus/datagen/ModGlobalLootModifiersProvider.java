package com.shield.shieldvanillaplus.datagen;

import com.mojang.serialization.MapCodec;
import com.shield.shieldvanillaplus.ShieldVanillaPlusMod;
import com.shield.shieldvanillaplus.items.ModItems;
import com.shield.shieldvanillaplus.loot.AddItemModifier;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {

  public ModGlobalLootModifiersProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
    super(output, registries, ShieldVanillaPlusMod.MODID);
  }

  @Override
  protected void start() {
    add("wither_bone_from_wither_skeleton", new AddItemModifier(new LootItemCondition[] {
        LootItemEntityPropertyCondition.hasProperties(
                LootContext.EntityTarget.THIS,
                EntityPredicate.Builder.entity().of(EntityType.WITHER_SKELETON)
        ).build(),
        LootItemRandomChanceCondition.randomChance(0.3f).build()
      },
      ModItems.WITHER_BONE.get())
    );

    add("wither_bone_from_skeleton", new AddItemModifier(new LootItemCondition[]{
        LootItemEntityPropertyCondition.hasProperties(
                LootContext.EntityTarget.THIS,
                EntityPredicate.Builder.entity().of(EntityType.SKELETON)
        ).build(),
        LootItemRandomChanceCondition.randomChance(0.035f).build()
      },
      ModItems.WITHER_BONE.get())
    );
  }
}
