package com.shield.vanillaplusmod.datagen;

import com.shield.vanillaplusmod.items.ModItems;
import com.shield.vanillaplusmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.DIAMOND_HORSE_ARMOR.asItem())
                .pattern(" SM")
                .pattern("MLM")
                .pattern("MMM")
                .define('S', Items.STRING.asItem())
                .define('M', Items.DIAMOND.asItem())
                .define('L', ItemTags.WOOL)
                .unlockedBy("has_diamond", has(Items.DIAMOND.asItem()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.GOLDEN_HORSE_ARMOR.asItem())
                .pattern(" SM")
                .pattern("MLM")
                .pattern("MMM")
                .define('S', Items.STRING.asItem())
                .define('M', Items.GOLD_INGOT.asItem())
                .define('L', ItemTags.WOOL)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT.asItem()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.IRON_HORSE_ARMOR.asItem())
                .pattern(" SM")
                .pattern("MLM")
                .pattern("MMM")
                .define('S', Items.STRING.asItem())
                .define('M', Items.IRON_INGOT.asItem())
                .define('L', ItemTags.WOOL)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT.asItem()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.LEATHER_HORSE_ARMOR.asItem())
                .pattern(" SM")
                .pattern("MLM")
                .pattern("MMM")
                .define('S', Items.STRING.asItem())
                .define('M', Items.LEATHER.asItem())
                .define('L', ItemTags.WOOL)
                .unlockedBy("has_leather_ingot", has(Items.LEATHER.asItem()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.END_PORTAL_FRAME.asItem())
                .pattern("ECE")
                .pattern("DSD")
                .pattern("ECE")
                .define('S', Items.NETHER_STAR.asItem())
                .define('E', Items.END_STONE.asItem())
                .define('C', Items.END_CRYSTAL.asItem())
                .define('D', Items.DIAMOND_BLOCK.asItem())
                .unlockedBy("has_nether_star", has(Items.NETHER_STAR.asItem()))
                .save(recipeOutput);


        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.RAW_COPPER_BLOCK), RecipeCategory.BUILDING_BLOCKS, Items.COPPER_BLOCK.asItem(), 1.0f, 600)
                .group("smelting_raw_copper_block_to_copper_block").unlockedBy("has_raw_copper_block", has(Items.RAW_COPPER_BLOCK))
                .save(recipeOutput, "smelting_raw_copper_block_to_copper_block");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.RAW_IRON_BLOCK), RecipeCategory.BUILDING_BLOCKS, Items.IRON_BLOCK.asItem(), 1.0f, 600)
                .group("smelting_raw_iron_block_to_iron_block").unlockedBy("has_raw_iron_block", has(Items.RAW_IRON_BLOCK))
                .save(recipeOutput, "smelting_raw_iron_block_to_iron_block");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.RAW_GOLD_BLOCK), RecipeCategory.BUILDING_BLOCKS, Items.GOLD_BLOCK.asItem(), 1.0f, 600)
                .group("smelting_raw_gold_block_to_gold_block").unlockedBy("has_raw_gold_block", has(Items.RAW_GOLD_BLOCK))
                .save(recipeOutput, "smelting_raw_gold_block_to_gold_block");

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.RAW_COPPER_BLOCK), RecipeCategory.BUILDING_BLOCKS, Items.COPPER_BLOCK.asItem(), 1.0f, 450)
                .group("blasting_raw_copper_block_to_copper_block").unlockedBy("has_raw_copper_block", has(Items.RAW_COPPER_BLOCK))
                .save(recipeOutput, "blasting_raw_copper_block_to_copper_block");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.RAW_IRON_BLOCK), RecipeCategory.BUILDING_BLOCKS, Items.IRON_BLOCK.asItem(), 1.0f, 450)
                .group("blasting_raw_iron_block_to_iron_block").unlockedBy("has_raw_iron_block", has(Items.RAW_IRON_BLOCK))
                .save(recipeOutput, "blasting_raw_iron_block_to_iron_block");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.RAW_GOLD_BLOCK), RecipeCategory.BUILDING_BLOCKS, Items.GOLD_BLOCK.asItem(), 1.0f, 450)
                .group("blasting_raw_gold_block_to_gold_block").unlockedBy("has_raw_gold_block", has(Items.RAW_GOLD_BLOCK))
                .save(recipeOutput, "blasting_raw_gold_block_to_gold_block");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModTags.Items.TNT_ITEMS), RecipeCategory.MISC, Items.GUNPOWDER.asItem(), 1.0F, 200)
                .group("smelting_tnt_to_gunpowder").unlockedBy("has_tnt_or_tntminecart", has(ModTags.Items.TNT_ITEMS))
                .save(recipeOutput, "smelting_tnt_to_gunpowder");

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModTags.Items.TNT_ITEMS), RecipeCategory.MISC, Items.GUNPOWDER.asItem(), 1.0F, 150)
                .group("blasting_tnt_to_gunpowder").unlockedBy("has_tnt_or_tntminecart", has(ModTags.Items.TNT_ITEMS))
                .save(recipeOutput, "blasting_tnt_to_gunpowder");

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemTags.SAND), RecipeCategory.MISC, Items.GLASS.asItem(), 1.0F, 150)
                .group("blasting_sand_to_glass").unlockedBy("has_sand", has(ItemTags.SAND))
                .save(recipeOutput, "blasting_sand_to_glass");


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.AWAKENED_HEART_TREE.get())
                .pattern("LGL")
                .pattern("TST")
                .pattern("LNL")
                .define('L', Items.OAK_LEAVES.asItem())
                .define('T', Items.OAK_WOOD.asItem())
                .define('G', Items.GOLD_INGOT.asItem())
                .define('N', Items.NETHERITE_INGOT.asItem())
                .define('S', ModItems.SLEEPING_HEART_TREE.get())
                .unlockedBy("has_sleeping_heart_tree", has(ModItems.SLEEPING_HEART_TREE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.EXCALIBUR_AXE.get())
                .pattern("GHT")
                .pattern(" NG")
                .pattern(" N ")
                .define('G', Items.GOLD_INGOT.asItem())
                .define('T', Items.GHAST_TEAR.asItem())
                .define('N', Items.STICK.asItem())
                .define('H', ModItems.AWAKENED_HEART_TREE.get())
                .unlockedBy("has_ghast_tear", has(Items.GHAST_TEAR.asItem()))
                .unlockedBy("has_awakened_heart_tree", has(ModItems.AWAKENED_HEART_TREE.get()))
                .unlockedBy("has_sleeping_heart_tree", has(ModItems.SLEEPING_HEART_TREE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.SANDSTONE_PICKAXE.get())
                .pattern("TTT")
                .pattern(" S ")
                .pattern(" S ")
                .define('T', Items.SANDSTONE)
                .define('S', Items.STICK)
                .unlockedBy("has_sandstone", has(Items.SANDSTONE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.SANDSTONE_SHOVEL.get())
                .pattern("T")
                .pattern("S")
                .pattern("S")
                .define('T', Items.SANDSTONE)
                .define('S', Items.STICK)
                .unlockedBy("has_sandstone", has(Items.SANDSTONE)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.WITHER_BONE_MEAL.get(), 3)
                .requires(ModItems.WITHER_BONE.get())
                .unlockedBy("has_wither_bone", has(ModItems.WITHER_BONE.get())).save(recipeOutput);
    }
}
