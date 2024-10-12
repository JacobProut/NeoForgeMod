package com.jacobpmods.datagen;

import com.jacobpmods.block.ModBlocks;
import com.jacobpmods.neomod.FirstNeoMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.jacobpmods.util.ModTags.Blocks.NEEDS_NEXON_TOOL;
import static net.minecraft.tags.BlockTags.*;
import static net.neoforged.neoforge.common.Tags.Blocks.NEEDS_NETHERITE_TOOL;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FirstNeoMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.NEXON_BLOCK.get())
                .add(ModBlocks.NEXON_ORE_BLOCK.get());

        tag(MINEABLE_WITH_AXE)
                .add(ModBlocks.LOG_GHOSTLY.get())
                .add(ModBlocks.PLANKS_GHOSTLY.get());

        tag(NEEDS_NEXON_TOOL)
                .add(ModBlocks.NEXON_ORE_BLOCK.get())
                .add(ModBlocks.NEXON_BLOCK.get())
                        .addTag(NEEDS_NEXON_TOOL);


        tag(NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.NEXON_ORE_BLOCK.get())
                .add(ModBlocks.NEXON_BLOCK.get())
                .addTag(NEEDS_NETHERITE_TOOL);

        tag(INCORRECT_FOR_DIAMOND_TOOL)
                .add(ModBlocks.NEXON_ORE_BLOCK.get())
                .add(ModBlocks.NEXON_BLOCK.get());

        tag(INCORRECT_FOR_IRON_TOOL)
                .add(ModBlocks.NEXON_ORE_BLOCK.get())
                .add(ModBlocks.NEXON_BLOCK.get());

        tag(INCORRECT_FOR_GOLD_TOOL)
                .add(ModBlocks.NEXON_ORE_BLOCK.get())
                .add(ModBlocks.NEXON_BLOCK.get());

        tag(INCORRECT_FOR_STONE_TOOL)
                .add(ModBlocks.NEXON_ORE_BLOCK.get())
                .add(ModBlocks.NEXON_BLOCK.get());

        tag(INCORRECT_FOR_WOODEN_TOOL)
                .add(ModBlocks.NEXON_ORE_BLOCK.get())
                .add(ModBlocks.NEXON_BLOCK.get());

        tag(DIRT)
                .add(ModBlocks.GHOSTLY_DIRT.get());

        this.tag(LOGS_THAT_BURN)
                .add(ModBlocks.LOG_GHOSTLY.get())
                .add(ModBlocks.WOOD_GHOSTLY.get())
                .add(ModBlocks.STRIPPED_GHOSTLY_LOG.get())
                .add(ModBlocks.STRIPPED_GHOSTLY_WOOD.get());


    }
}
