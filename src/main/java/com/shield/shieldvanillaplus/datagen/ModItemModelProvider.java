package com.shield.shieldvanillaplus.datagen;

import com.shield.shieldvanillaplus.ShieldVanillaPlusMod;
import com.shield.shieldvanillaplus.items.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ShieldVanillaPlusMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        handheldItem(ModItems.EXCALIBUR_AXE);
        handheldItem(ModItems.SANDSTONE_PICKAXE);
        handheldItem(ModItems.SANDSTONE_SHOVEL);

        basicItem(ModItems.WITHER_BONE.get());
        basicItem(ModItems.WITHER_BONE_MEAL.get());
        basicItem(ModItems.SLEEPING_HEART_TREE.get());
        basicItem(ModItems.AWAKENED_HEART_TREE.get());
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(ShieldVanillaPlusMod.MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void buttonItem(DeferredBlock<?> block, Block baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(ShieldVanillaPlusMod.MODID,
                        "block/" + BuiltInRegistries.BLOCK.getKey(baseBlock).getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(ShieldVanillaPlusMod.MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, Block baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(ShieldVanillaPlusMod.MODID,
                        "block/" + BuiltInRegistries.BLOCK.getKey(baseBlock).getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", ResourceLocation.fromNamespaceAndPath(ShieldVanillaPlusMod.MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, Block baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", ResourceLocation.fromNamespaceAndPath(ShieldVanillaPlusMod.MODID,
                        "block/" + BuiltInRegistries.BLOCK.getKey(baseBlock).getPath()));
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(ShieldVanillaPlusMod.MODID, "item/" + item.getId().getPath()));
    }


}
