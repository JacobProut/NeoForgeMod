package com.jacobpmods.neomod.item.custom.block;

import com.jacobpmods.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class GhostlyBlock extends GrassBlock {
    public GhostlyBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(0.6f)  // You can adjust this value
                .sound(SoundType.GRASS)
                .randomTicks()   // Required for grass-like spreading behavior
        );
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!world.isAreaLoaded(pos, 3)) return; // Only perform actions if area is loaded

        // Check light level for spreading
        if (world.getMaxLocalRawBrightness(pos.above()) >= 9) {
            BlockState blockAbove = world.getBlockState(pos.above());
            BlockPos belowPos = pos.below();

            // If there's air or a snow block above, spread to adjacent blocks
            if (blockAbove.isAir() || blockAbove.is(Blocks.SNOW)) {
                for (int i = 0; i < 4; i++) {
                    BlockPos targetPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    BlockState targetBlockState = world.getBlockState(targetPos);

                    // Spread only if it's Ghostly Dirt (not regular dirt)
                    if (targetBlockState.is(ModBlocks.GHOSTLY_DIRT.get())) {
                        world.setBlockAndUpdate(targetPos, ModBlocks.GHOSTLY_GRASS_BLOCK.get().defaultBlockState());
                    }
                }
            }
        }
    }
}
