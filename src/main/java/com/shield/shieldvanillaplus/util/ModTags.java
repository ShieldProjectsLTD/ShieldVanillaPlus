package com.shield.shieldvanillaplus.util;

import com.shield.shieldvanillaplus.ShieldVanillaPlusMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
  public static class Blocks {
    public static final TagKey<Block> NEEDS_EXCALIBUR_TOOL = createTag("needs_excalibur_tool");
    public static final TagKey<Block> INCORRECT_FOR_EXCALIBUR_TOOl = createTag("incorrect_for_excalibur_tool");

    public static final TagKey<Block> NEEDS_SANDSTONE_TOOL = createTag("needs_sandstone_tool");
    public static final TagKey<Block> INCORRECT_FOR_SANDSTONE_TOOl = createTag("incorrect_for_sandstone_tool");

    public static final TagKey<Block> TRANSFORMABLE_BLOCKS_WITHER_BM = createTag("transformable_blocks_wither_bm");
    public static final TagKey<Block> DESTROY_NATURE_BLOCKS_WITHER_BM = createTag("destroy_nature_blocks_wither_bm");
    public static final TagKey<Block> TRANSFORM_TO_DEAD_BUSH_WITHER_BM = createTag("transform_to_dead_bush_wither_bm");

    private static TagKey<Block> createTag(String name) {
      return BlockTags.create(ResourceLocation.fromNamespaceAndPath(ShieldVanillaPlusMod.MODID, name));
    }
  }

  public static class Items {
    public static final TagKey<Item> TNT_ITEMS = createTag("tnt_items");
    public static final TagKey<Item> LANTERNS = createTag("lanterns");
    public static final TagKey<Item> PISTONS = createTag("pistons");
    public static final TagKey<Item> MINECARTS = createTag("minecarts");


    private static TagKey<Item> createTag(String name) {
      return ItemTags.create(ResourceLocation.fromNamespaceAndPath(ShieldVanillaPlusMod.MODID, name));
    }
  }
}
