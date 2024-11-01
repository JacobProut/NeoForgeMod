package com.jacobpmods.neomod.block.custom.portal.setup;

import com.jacobpmods.neomod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.extensions.IBlockStateExtension;

public interface ModIBlockStateExtension extends IBlockStateExtension {

    private BlockState self() {
        return (BlockState) this;
    }

    // Checks if a specific state is a portal frame based on the type of block
    static boolean isItAPortalFrame(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(ModBlocks.BONE_BRICK.get());
    }

    // Checks if the block at the position is a portal frame, calling isItAPortalFrame
    static boolean itIsPortalFrame(BlockState adjacentState, BlockGetter level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return isItAPortalFrame(state, level, pos);
    }
}
