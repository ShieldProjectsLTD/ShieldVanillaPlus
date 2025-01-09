package com.shield.shieldvanillaplus.datagen;

import com.shield.shieldvanillaplus.ShieldVanillaPlusMod;
import com.shield.shieldvanillaplus.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ShieldVanillaPlusMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(ModTags.Blocks.NEEDS_EXCALIBUR_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_EXCALIBUR_TOOl)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .remove(ModTags.Blocks.NEEDS_EXCALIBUR_TOOL);

        tag(ModTags.Blocks.NEEDS_SANDSTONE_TOOL)
                .addTag(BlockTags.NEEDS_STONE_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_EXCALIBUR_TOOl)
                .addTag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .remove(ModTags.Blocks.NEEDS_SANDSTONE_TOOL);

        tag(ModTags.Blocks.TRANSFORMABLE_BLOCKS_WITHER_BM)
            .add(Blocks.GRASS_BLOCK)
            .add(Blocks.PODZOL)
            .add(Blocks.COARSE_DIRT)
            .add(Blocks.ROOTED_DIRT)
            .add(Blocks.DIRT_PATH)
            .add(Blocks.FARMLAND)
            .add(Blocks.MUD);

        tag(ModTags.Blocks.DESTROY_NATURE_BLOCKS_WITHER_BM)
                .add(Blocks.SHORT_GRASS)
                .add(Blocks.TALL_GRASS)
                .add(Blocks.FERN)
                .add(Blocks.LARGE_FERN);
//                .add(Blocks.LEA)

        tag(ModTags.Blocks.TRANSFORM_TO_DEAD_BUSH_WITHER_BM)
                .addTag(BlockTags.FLOWERS)
                .addTag(BlockTags.TALL_FLOWERS)
                .addTag(BlockTags.SMALL_FLOWERS)
                .addTag(BlockTags.SAPLINGS)
                .add(Blocks.TORCHFLOWER);

    }
}
