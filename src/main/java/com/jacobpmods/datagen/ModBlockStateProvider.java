package com.jacobpmods.datagen;

import com.jacobpmods.block.ModBlocks;
import com.jacobpmods.neomod.FirstNeoMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FirstNeoMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        System.out.println("Registering block states and models");
        blockWithItem(ModBlocks.NEXON_BLOCK);
        blockWithItem(ModBlocks.NEXON_ORE_BLOCK);
        blockWithItem(ModBlocks.GHOSTLY_GRASS_BLOCK);
        blockWithItem(ModBlocks.GHOSTLY_DIRT);


        System.out.println("Block states and models:logBlock-axisBlock's");
        logBlock(((RotatedPillarBlock) ModBlocks.LOG_GHOSTLY.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.WOOD_GHOSTLY.get()), blockTexture(ModBlocks.LOG_GHOSTLY.get()), blockTexture(ModBlocks.LOG_GHOSTLY.get()));
        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_GHOSTLY_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_GHOSTLY_WOOD.get()), blockTexture(ModBlocks.STRIPPED_GHOSTLY_LOG.get()), blockTexture(ModBlocks.STRIPPED_GHOSTLY_LOG.get()));


        //Error when these are added
        blockItem(ModBlocks.LOG_GHOSTLY);
        blockItem(ModBlocks.WOOD_GHOSTLY);
        blockItem(ModBlocks.STRIPPED_GHOSTLY_LOG);
        blockItem(ModBlocks.STRIPPED_GHOSTLY_WOOD);


        blockWithItem(ModBlocks.PLANKS_GHOSTLY);
        leavesBlock(ModBlocks.GHOSTLY_LEAVES);
        saplingBlock(ModBlocks.GHOSTLY_SAPLING);

        System.out.println("Finished registering block states and models");
    }


    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void leavesBlock(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(deferredBlock.get())).renderType("cutout"));
    }

    private void saplingBlock(DeferredBlock<Block> deferredBlock) {
        simpleBlock(deferredBlock.get(), models().cross(BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath(), blockTexture(deferredBlock.get())).renderType("cutout"));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("mccourse:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("mccourse:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
