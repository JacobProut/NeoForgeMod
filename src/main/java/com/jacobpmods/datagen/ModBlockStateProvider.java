package com.jacobpmods.datagen;

import com.jacobpmods.block.ModBlocks;
import com.jacobpmods.neomod.FirstNeoMod;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FirstNeoMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.NEXON_BLOCK);
        blockWithItem(ModBlocks.NEXON_ORE_BLOCK);
        blockWithItem(ModBlocks.GHOSTLY_GRASS_BLOCK);
        blockWithItem(ModBlocks.GHOSTLY_DIRT);
        blockWithItem(ModBlocks.LOG_GHOSTLY);
        blockWithItem(ModBlocks.PLANKS_GHOSTLY);
    }


    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
