package com.shield.vanillaplusmod.datagen;

import com.shield.vanillaplusmod.ShieldVanillaPlusMod;
import com.shield.vanillaplusmod.items.ModItems;
import com.shield.vanillaplusmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, ShieldVanillaPlusMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(ItemTags.AXES)
            .add(ModItems.EXCALIBUR_AXE.get());

        tag(ItemTags.PICKAXES)
            .add(ModItems.SANDSTONE_PICKAXE.get());

        tag(ItemTags.SHOVELS)
            .add(ModItems.SANDSTONE_SHOVEL.get());

        tag(ModTags.Items.TNT_ITEMS)
            .add(Items.TNT)
            .add(Items.TNT_MINECART);
    }
}
