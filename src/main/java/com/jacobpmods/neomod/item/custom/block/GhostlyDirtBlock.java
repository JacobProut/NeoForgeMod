package com.jacobpmods.neomod.item.custom.block;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class GhostlyDirtBlock extends Block {
    public GhostlyDirtBlock() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)
                .strength(0.5f)  // Set block hardness
                .sound(SoundType.GRAVEL)  // Set the sound type
                .isSuffocating((state, world, pos) -> false)  // Mimics dirt behavior
                .isViewBlocking((state, world, pos) -> false) // Mimics dirt behavior

        );
    }
}
