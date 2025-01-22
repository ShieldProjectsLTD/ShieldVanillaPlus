package com.shield.shieldvanillaplus.datagen;

import com.shield.shieldvanillaplus.items.ModItems;
import com.shield.shieldvanillaplus.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

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

        //Reproduction
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemTags.ANVIL), RecipeCategory.MISC, Items.IRON_BLOCK.asItem(), 0.1F, 400)
                .group("smelting_anvil").unlockedBy("has_anvil", has(ItemTags.ANVIL))
                .save(recipeOutput, "smelting_anvil");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemTags.ANVIL), RecipeCategory.MISC, Items.IRON_BLOCK.asItem(), 0.1F, 350)
                .group("blasting_anvil").unlockedBy("has_anvil", has(ItemTags.ANVIL))
                .save(recipeOutput, "blasting_anvil");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.BELL), RecipeCategory.MISC, Items.GOLD_INGOT.asItem(), 0.1F, 200)
                .group("smelting_bell").unlockedBy("has_bell", has(Items.BELL))
                .save(recipeOutput, "smelting_bell");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.BELL), RecipeCategory.MISC, Items.GOLD_INGOT.asItem(), 0.1F, 150)
                .group("blasting_bell").unlockedBy("has_bell", has(Items.BELL))
                .save(recipeOutput, "blasting_bell");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.BLAST_FURNACE), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 200)
                .group("smelting_blast_furnace").unlockedBy("has_blast_furnace", has(Items.BLAST_FURNACE))
                .save(recipeOutput, "smelting_blast_furnace");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.BLAST_FURNACE), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 150)
                .group("blasting_blast_furnace").unlockedBy("has_blast_furnace", has(Items.BLAST_FURNACE))
                .save(recipeOutput, "blasting_blast_furnace");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.BUCKET), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 200)
                .group("smelting_bucket").unlockedBy("has_bucket", has(Items.BUCKET))
                .save(recipeOutput, "smelting_bucket");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.BUCKET), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 150)
                .group("blasting_bucket").unlockedBy("has_bucket", has(Items.BUCKET))
                .save(recipeOutput, "blasting_bucket");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.CAULDRON), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 200)
                .group("smelting_cauldron").unlockedBy("has_cauldron", has(Items.CAULDRON))
                .save(recipeOutput, "smelting_cauldron");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.CAULDRON), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 150)
                .group("blasting_cauldron").unlockedBy("has_cauldron", has(Items.CAULDRON))
                .save(recipeOutput, "blasting_cauldron");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.CHAIN), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 200)
                .group("smelting_chain").unlockedBy("has_chain", has(Items.CHAIN))
                .save(recipeOutput, "smelting_chain");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.CHAIN), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 150)
                .group("blasting_chain").unlockedBy("has_chain", has(Items.CHAIN))
                .save(recipeOutput, "blasting_chain");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.FLINT_AND_STEEL), RecipeCategory.MISC, Items.IRON_NUGGET.asItem(), 0.1F, 200)
                .group("smelting_flint_and_steel").unlockedBy("has_flint_and_steel", has(Items.FLINT_AND_STEEL))
                .save(recipeOutput, "smelting_flint_and_steel");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.FLINT_AND_STEEL), RecipeCategory.MISC, Items.IRON_NUGGET.asItem(), 0.1F, 150)
                .group("blasting_flint_and_steel").unlockedBy("has_flint_and_steel", has(Items.FLINT_AND_STEEL))
                .save(recipeOutput, "blasting_flint_and_steel");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.HEAVY_WEIGHTED_PRESSURE_PLATE), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 200)
                .group("smelting_weighted_pressure_plate").unlockedBy("has_weighted_pressure_plate", has(Items.HEAVY_WEIGHTED_PRESSURE_PLATE))
                .save(recipeOutput, "smelting_weighted_pressure_plate");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.HEAVY_WEIGHTED_PRESSURE_PLATE), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 150)
                .group("blasting_weighted_pressure_plate").unlockedBy("has_weighted_pressure_plate", has(Items.HEAVY_WEIGHTED_PRESSURE_PLATE))
                .save(recipeOutput, "blasting_weighted_pressure_plate");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.LIGHT_WEIGHTED_PRESSURE_PLATE), RecipeCategory.MISC, Items.GOLD_INGOT.asItem(), 0.1F, 200)
                .group("smelting_light_pressure_plate").unlockedBy("has_light_pressure_plate", has(Items.LIGHT_WEIGHTED_PRESSURE_PLATE))
                .save(recipeOutput, "smelting_light_pressure_plate");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.LIGHT_WEIGHTED_PRESSURE_PLATE), RecipeCategory.MISC, Items.GOLD_INGOT.asItem(), 0.1F, 150)
                .group("blasting_light_pressure_plate").unlockedBy("has_light_pressure_plate", has(Items.LIGHT_WEIGHTED_PRESSURE_PLATE))
                .save(recipeOutput, "blasting_light_pressure_plate");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.HOPPER), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 200)
                .group("smelting_hopper").unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput, "smelting_hopper");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.HOPPER), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 150)
                .group("blasting_hopper").unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput, "blasting_hopper");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.IRON_BARS), RecipeCategory.MISC, Items.IRON_NUGGET.asItem(), 0.1F, 200)
                .group("smelting_iron_bars").unlockedBy("has_iron_bars", has(Items.IRON_BARS))
                .save(recipeOutput, "smelting_iron_bars");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.IRON_BARS), RecipeCategory.MISC, Items.IRON_NUGGET.asItem(), 0.1F, 150)
                .group("blasting_iron_bars").unlockedBy("has_iron_bars", has(Items.IRON_BARS))
                .save(recipeOutput, "blasting_iron_bars");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.IRON_DOOR), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 200)
                .group("smelting_iron_door").unlockedBy("has_iron_door", has(Items.IRON_DOOR))
                .save(recipeOutput, "smelting_iron_door");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.IRON_DOOR), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 150)
                .group("blasting_iron_door").unlockedBy("has_iron_door", has(Items.IRON_DOOR))
                .save(recipeOutput, "blasting_iron_door");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.IRON_TRAPDOOR), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 200)
                .group("smelting_iron_trapdoor").unlockedBy("has_iron_trapdoor", has(Items.IRON_TRAPDOOR))
                .save(recipeOutput, "smelting_iron_trapdoor");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.IRON_TRAPDOOR), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 150)
                .group("blasting_iron_trapdoor").unlockedBy("has_iron_trapdoor", has(Items.IRON_TRAPDOOR))
                .save(recipeOutput, "blasting_iron_trapdoor");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.COMPASS), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 200)
                .group("smelting_compass").unlockedBy("has_compass", has(Items.COMPASS))
                .save(recipeOutput, "smelting_compass");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.COMPASS), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 150)
                .group("blasting_compass").unlockedBy("has_compass", has(Items.COMPASS))
                .save(recipeOutput, "blasting_compass");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.IRON_HORSE_ARMOR), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 200)
                .group("smelting_iron_horse_armor").unlockedBy("has_iron_horse_armor", has(Items.IRON_HORSE_ARMOR))
                .save(recipeOutput, "smelting_iron_horse_armor");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.IRON_HORSE_ARMOR), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 150)
                .group("blasting_iron_horse_armor").unlockedBy("has_iron_horse_armor", has(Items.IRON_HORSE_ARMOR))
                .save(recipeOutput, "blasting_iron_horse_armor");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.GOLDEN_HORSE_ARMOR), RecipeCategory.MISC, Items.GOLD_INGOT.asItem(), 0.1F, 200)
                .group("smelting_golden_horse_armor").unlockedBy("has_golden_horse_armor", has(Items.GOLDEN_HORSE_ARMOR))
                .save(recipeOutput, "smelting_golden_horse_armor");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.GOLDEN_HORSE_ARMOR), RecipeCategory.MISC, Items.GOLD_INGOT.asItem(), 0.1F, 150)
                .group("blasting_golden_horse_armor").unlockedBy("has_golden_horse_armor", has(Items.GOLDEN_HORSE_ARMOR))
                .save(recipeOutput, "blasting_golden_horse_armor");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModTags.Items.LANTERNS), RecipeCategory.MISC, Items.IRON_NUGGET.asItem(), 0.1F, 200)
                .group("smelting_lanterns").unlockedBy("has_lanterns", has(ModTags.Items.LANTERNS))
                .save(recipeOutput, "smelting_lanterns");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModTags.Items.LANTERNS), RecipeCategory.MISC, Items.IRON_NUGGET.asItem(), 0.1F, 150)
                .group("blasting_lanterns").unlockedBy("has_lanterns", has(ModTags.Items.LANTERNS))
                .save(recipeOutput, "blasting_lanterns");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModTags.Items.MINECARTS), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 200)
                .group("smelting_minecarts").unlockedBy("has_minecarts", has(ModTags.Items.MINECARTS))
                .save(recipeOutput, "smelting_minecarts");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModTags.Items.MINECARTS), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 150)
                .group("blasting_minecart").unlockedBy("has_minecarts", has(ModTags.Items.MINECARTS))
                .save(recipeOutput, "blasting_minecarts");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModTags.Items.PISTONS), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 200)
                .group("smelting_pistons").unlockedBy("has_pistons", has(ModTags.Items.PISTONS))
                .save(recipeOutput, "smelting_pistons");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModTags.Items.PISTONS), RecipeCategory.MISC, Items.IRON_INGOT.asItem(), 0.1F, 150)
                .group("blasting_pistons").unlockedBy("has_pistons", has(ModTags.Items.PISTONS))
                .save(recipeOutput, "blasting_pistons");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.SHEARS), RecipeCategory.MISC, Items.IRON_NUGGET.asItem(), 0.1F, 200)
                .group("smelting_shears").unlockedBy("has_shears", has(Items.SHEARS))
                .save(recipeOutput, "smelting_shears");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.SHEARS), RecipeCategory.MISC, Items.IRON_NUGGET.asItem(), 0.1F, 150)
                .group("blasting_shears").unlockedBy("has_shears", has(Items.SHEARS))
                .save(recipeOutput, "blasting_shears");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.SHIELD), RecipeCategory.MISC, Items.IRON_NUGGET.asItem(), 0.1F, 200)
                .group("smelting_shield").unlockedBy("has_shield", has(Items.SHIELD))
                .save(recipeOutput, "smelting_shield");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.SHIELD), RecipeCategory.MISC, Items.IRON_NUGGET.asItem(), 0.1F, 150)
                .group("blasting_shield").unlockedBy("has_shield", has(Items.SHIELD))
                .save(recipeOutput, "blasting_shield");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.SMITHING_TABLE), RecipeCategory.MISC, Items.IRON_NUGGET.asItem(), 0.1F, 200)
                .group("smelting_smithing_table").unlockedBy("has_smithing_table", has(Items.SMITHING_TABLE))
                .save(recipeOutput, "smelting_smithing_table");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.SMITHING_TABLE), RecipeCategory.MISC, Items.IRON_NUGGET.asItem(), 0.1F, 150)
                .group("blasting_smithing_table").unlockedBy("has_smithing_table", has(Items.SMITHING_TABLE))
                .save(recipeOutput, "blasting_smithing_table");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.STONECUTTER), RecipeCategory.MISC, Items.IRON_NUGGET.asItem(), 0.1F, 200)
                .group("smelting_stonecutter").unlockedBy("has_stonecutter", has(Items.STONECUTTER))
                .save(recipeOutput, "smelting_stonecutter");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.STONECUTTER), RecipeCategory.MISC, Items.IRON_NUGGET.asItem(), 0.1F, 150)
                .group("blasting_stonecutter").unlockedBy("has_stonecutter", has(Items.STONECUTTER))
                .save(recipeOutput, "blasting_stonecutter");

        Smelting(Ingredient.of(Items.TRIPWIRE_HOOK), Items.IRON_NUGGET.asItem(), RecipeCategory.MISC, 0.1F, 150F);


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

    private void Smelting(RecipeOutput recipe, Ingredient input, Item output, RecipeCategory category, float experience, int time) {
        SimpleCookingRecipeBuilder.smelting(input, category, output, experience, time)
                .group("smelting_tripwire_hook").unlockedBy("has_tripwire_hook", has(Items.TRIPWIRE_HOOK))
                .save(recipe, "smelting_tripwire_hook");
        SimpleCookingRecipeBuilder.blasting(input, category, output, experience, time-50)
                .group("blasting_tripwire_hook").unlockedBy("has_tripwire_hook", has( ))
                .save(recipe, "blasting_tripwire_hook");
    }
}
