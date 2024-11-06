package com.jacobpmods.neomod.block.custom;

import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.block.terrainblocks.GhostlyBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModFlowerBlock extends FlowerBlock {

    public ModFlowerBlock(Holder<MobEffect> effect, float seconds, Properties properties) {
        super(effect, seconds, properties);
    }
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(ModBlocks.GHOSTLY_GRASS_BLOCK)  || state.is(ModBlocks.GHOSTLY_DIRT)|| state.getBlock() instanceof FarmBlock;
    }
}
