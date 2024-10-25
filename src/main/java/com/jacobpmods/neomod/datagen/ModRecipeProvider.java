package com.jacobpmods.neomod.datagen;

import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        //Nexon Ore Smelting/Blasting Methods
        List<ItemLike> NEXON_SMELTABLES= List.of(ModItems.nexon.get()); //Can add other nexon items here that can be smelted.
        oreSmelting(recipeOutput, NEXON_SMELTABLES, RecipeCategory.MISC, ModItems.heatednexon.get(), 0.30f, 200, "nexon");
        oreBlasting(recipeOutput, NEXON_SMELTABLES, RecipeCategory.MISC, ModItems.heatednexon.get(), 0.30f, 100, "nexon");

        //Nexon Block Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.NEXON_BLOCK.get(), 1)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.nexoningot.get())
                .unlockedBy(getHasName(ModItems.nexoningot.get()), has(ModItems.nexoningot.get())).save(recipeOutput);

        // 9 Nexon from Block Recipe
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.nexon.get(), 9)
                .requires(ModBlocks.NEXON_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.NEXON_BLOCK.get()), has(ModBlocks.NEXON_BLOCK.get())).save(recipeOutput);


        //Nexon ingot Craft Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.nexoningot.get(), 1)
                .pattern("HHH")
                .pattern("HHH")
                .pattern("HHH")
                .define('H', ModItems.heatednexon.get())
                .unlockedBy(getHasName(ModItems.nexoningot.get()), has(ModItems.nexoningot.get())).save(recipeOutput);

        //Nexon Reinforced Ingot Craft Recipe
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.nexonreinforcedingot.get(), 1)
                .requires(ModItems.nexoningot.get(),1)
                .requires(Items.NETHERITE_INGOT, 1)
                .unlockedBy(getHasName(ModItems.nexoningot.get()), has(ModItems.nexoningot.get())).save(recipeOutput);

        //Nexon Pickaxe Craft Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.nexonpickaxe.get(), 1)
                .pattern("AAA")
                .pattern(" S ")
                .pattern(" S ")
                .define('A', ModItems.nexonreinforcedingot.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.nexonreinforcedingot.get()), has(ModItems.nexonreinforcedingot.get())).save(recipeOutput);


        //Nexon Helmet Craft Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.nexonhelmet.get(), 1)
                .pattern("HRH")
                .pattern("R R")
                .pattern("   ")
                .define('H', ModItems.heatednexon.get())
                .define('R', ModItems.nexonreinforcedingot.get())
                .unlockedBy(getHasName(ModItems.nexonreinforcedingot.get()), has(ModItems.nexonreinforcedingot.get())).save(recipeOutput);

        //Nexon Chestplate Craft Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.nexonchestplate.get(), 1)
                .pattern("R R")
                .pattern("HRH")
                .pattern("RHR")
                .define('H', ModItems.heatednexon.get())
                .define('R', ModItems.nexonreinforcedingot.get())
                .unlockedBy(getHasName(ModItems.nexonreinforcedingot.get()), has(ModItems.nexonreinforcedingot.get())).save(recipeOutput);

        //Nexon Leggings Craft Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.nexonleggings.get(), 1)
                .pattern("RHR")
                .pattern("H H")
                .pattern("R R")
                .define('H', ModItems.heatednexon.get())
                .define('R', ModItems.nexonreinforcedingot.get())
                .unlockedBy(getHasName(ModItems.nexonreinforcedingot.get()), has(ModItems.nexonreinforcedingot.get())).save(recipeOutput);

        //Nexon Boots Craft Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.nexonboots.get(), 1)
                .pattern("R R")
                .pattern("H H")
                .pattern("   ")
                .define('H', ModItems.heatednexon.get())
                .define('R', ModItems.nexonreinforcedingot.get())
                .unlockedBy(getHasName(ModItems.nexonreinforcedingot.get()), has(ModItems.nexonreinforcedingot.get())).save(recipeOutput);

        //Speed apple
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.speedapple.get(), 1)
                .pattern("SSS")
                .pattern("SAS")
                .pattern("SSS")
                .define('A', Items.APPLE)
                .define('S', Items.SUGAR)
                .unlockedBy(getHasName(Items.APPLE), has(Items.APPLE)).save(recipeOutput);


        //Ghostly Log to planks Craft Recipe
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.PLANKS_GHOSTLY.get(), 4)
                .requires(ModBlocks.LOG_GHOSTLY.get(),1)
                .unlockedBy(getHasName(ModBlocks.LOG_GHOSTLY.get()), has(ModBlocks.LOG_GHOSTLY.get())).save(recipeOutput);

    }
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, FirstNeoMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}