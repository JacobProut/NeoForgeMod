package com.jacobpmods.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ModSaplingBlock extends SaplingBlock {
    private Block block;
    public ModSaplingBlock(TreeGrower treeGrower, Properties properties, Block block) {
        super(treeGrower, properties);
        this.block = block;
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, BlockGetter plevel, BlockPos blockPos) {
        return blockState.is(block);
    }

}
