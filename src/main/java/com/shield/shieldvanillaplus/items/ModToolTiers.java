package com.shield.shieldvanillaplus.items;

import com.shield.shieldvanillaplus.util.ModTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
  public static final Tier EXCALIBUR = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_EXCALIBUR_TOOl,
          2931, 8.5f, 2.0f, 15, () -> Ingredient.of(Items.NETHER_STAR));

  public static final Tier SANDSTONE = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_SANDSTONE_TOOl,
          101, 5.0f, 3.0f, 3, () -> Ingredient.of(Items.SANDSTONE));

}


//WOOD(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 2.0F, 0.0F, 15, () -> Ingredient.of(ItemTags.PLANKS)),
//STONE(BlockTags.INCORRECT_FOR_STONE_TOOL, 131, 4.0F, 1.0F, 5, () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)),
//IRON(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6.0F, 2.0F, 14, () -> Ingredient.of(new ItemLike[]{Items.IRON_INGOT})),
//DIAMOND(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1561, 8.0F, 3.0F, 10, () -> Ingredient.of(new ItemLike[]{Items.DIAMOND})),
//GOLD(BlockTags.INCORRECT_FOR_GOLD_TOOL, 32, 12.0F, 0.0F, 22, () -> Ingredient.of(new ItemLike[]{Items.GOLD_INGOT})),
//NETHERITE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 9.0F, 4.0F, 15, () -> Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT}));
