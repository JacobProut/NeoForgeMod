package com.jacobpmods.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ModSaplingBlock extends SaplingBlock {
    private Block block;
    private Block block2;
    private Block block3;
    private Block block4;
    public ModSaplingBlock(TreeGrower treeGrower, Properties properties, Block block) {
        super(treeGrower, properties);
        this.block = block;
    }

    public ModSaplingBlock(TreeGrower treeGrower, Properties properties, Block block, Block block2) {
        super(treeGrower, properties);
        this.block = block;
        this.block2 = block2;
    }

    public ModSaplingBlock(TreeGrower treeGrower, Properties properties, Block block, Block block2, Block block3, Block block4) {
        super(treeGrower, properties);
        this.block = block;
        this.block2 = block2;
        this.block3 = block3;
        this.block4 = block4;
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, BlockGetter plevel, BlockPos blockPos) {
        return blockState.is(block) ||
                (block2 != null && blockState.is(block2)) ||
                (block3 != null && blockState.is(block3)) ||
                (block4 != null && blockState.is(block4));
    }

}
