package com.jacobpmods.neomod.block.custom.portal.setup;

import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.block.custom.portal.GhostlyPortalBlock;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class GhostlyPortalForcer  {
    public static final int TICKET_RADIUS = 3;
    private static final int GHOSTLY_PORTAL_RADIUS = 16;
    private static final int OVERWORLD_PORTAL_RADIUS = 128;
    private static final int FRAME_HEIGHT = 5;
    private static final int FRAME_WIDTH = 4;
    private static final int FRAME_BOX = 3;
    private static final int FRAME_HEIGHT_START = -1;
    private static final int FRAME_HEIGHT_END = 4;
    private static final int FRAME_WIDTH_START = -1;
    private static final int FRAME_WIDTH_END = 3;
    private static final int FRAME_BOX_START = -1;
    private static final int FRAME_BOX_END = 2;
    private static final int NOTHING_FOUND = -1;
    protected final ServerLevel level;

    public GhostlyPortalForcer(ServerLevel level) {
        this.level = level;
    }

    public Stream<BlockPos> findClosestPortalPosition(BlockPos exitPos, boolean isGhostly, WorldBorder worldBorder) {
        PoiManager poiManager = this.level.getPoiManager();
        int searchRadius = isGhostly ? 16 : 128;
        poiManager.ensureLoadedAndValid(this.level, exitPos, searchRadius);

        return poiManager.getInSquare(
                        poi -> poi.is(ModPoiTypes.GHOSTLY_PORTAL),
                        exitPos,
                        searchRadius,
                        PoiManager.Occupancy.ANY
                )
                .map(PoiRecord::getPos)
                .filter(worldBorder::isWithinBounds)
                .filter(p -> this.level.getBlockState(p).hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
                .sorted(Comparator.comparingDouble((BlockPos p) -> p.distSqr(exitPos))
                        .thenComparingInt(Vec3i::getY));
    }

    public Optional<BlockUtil.FoundRectangle> createPortal(BlockPos pos, Direction.Axis axis) {
        Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        double d0 = -1.0;
        BlockPos blockpos = null;
        double d1 = -1.0;
        BlockPos blockpos1 = null;
        WorldBorder worldborder = this.level.getWorldBorder();
        int i = Math.min(this.level.getMaxBuildHeight(), this.level.getMinBuildHeight() + this.level.getLogicalHeight()) - 1;
        boolean j = true;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();
        Iterator var14 = BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH).iterator();

        while(true) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos1;
            int l2;
            int l;
            int j3;
            int j1;
            do {
                do {
                    if (!var14.hasNext()) {
                        if (d0 == -1.0 && d1 != -1.0) {
                            blockpos = blockpos1;
                            d0 = d1;
                        }

                        int l1;
                        int k2;
                        if (d0 == -1.0) {
                            l1 = Math.max(this.level.getMinBuildHeight() - -1, 70);
                            k2 = i - 9;
                            if (k2 < l1) {
                                return Optional.empty();
                            }

                            blockpos = (new BlockPos(pos.getX() - direction.getStepX(), Mth.clamp(pos.getY(), l1, k2), pos.getZ() - direction.getStepZ())).immutable();
                            blockpos = worldborder.clampToBounds(blockpos);
                            Direction direction1 = direction.getClockWise();

                            for(l = -1; l < 2; ++l) {
                                for(j3 = 0; j3 < 2; ++j3) {
                                    for(j1 = -1; j1 < 3; ++j1) {
                                        BlockState blockstate1 = j1 < 0 ? ModBlocks.BONE_BRICK.get().defaultBlockState() : Blocks.AIR.defaultBlockState();
                                        blockpos$mutableblockpos.setWithOffset(blockpos, j3 * direction.getStepX() + l * direction1.getStepX(), j1, j3 * direction.getStepZ() + l * direction1.getStepZ());
                                        this.level.setBlockAndUpdate(blockpos$mutableblockpos, blockstate1);
                                    }
                                }
                            }
                        }

                        for(l1 = -1; l1 < 3; ++l1) {
                            for(k2 = -1; k2 < 4; ++k2) {
                                if (l1 == -1 || l1 == 2 || k2 == -1 || k2 == 3) {
                                    blockpos$mutableblockpos.setWithOffset(blockpos, l1 * direction.getStepX(), k2, l1 * direction.getStepZ());
                                    this.level.setBlock(blockpos$mutableblockpos, ModBlocks.BONE_BRICK.get().defaultBlockState(), 3);
                                }
                            }
                        }

                        BlockState blockstate = ModBlocks.GHOSTLY_PORTAL_BLOCK.get().defaultBlockState().setValue(GhostlyPortalBlock.AXIS, axis);

                        for(k2 = 0; k2 < 2; ++k2) {
                            for(l2 = 0; l2 < 3; ++l2) {
                                blockpos$mutableblockpos.setWithOffset(blockpos, k2 * direction.getStepX(), l2, k2 * direction.getStepZ());
                                this.level.setBlock(blockpos$mutableblockpos, blockstate, 18);
                            }
                        }

                        return Optional.of(new BlockUtil.FoundRectangle(blockpos.immutable(), 2, 3));
                    }

                    blockpos$mutableblockpos1 = (BlockPos.MutableBlockPos)var14.next();
                    l2 = Math.min(i, this.level.getHeight(Heightmap.Types.MOTION_BLOCKING, blockpos$mutableblockpos1.getX(), blockpos$mutableblockpos1.getZ()));
                } while(!worldborder.isWithinBounds(blockpos$mutableblockpos1));
            } while(!worldborder.isWithinBounds(blockpos$mutableblockpos1.move(direction, 1)));

            blockpos$mutableblockpos1.move(direction.getOpposite(), 1);

            for(l = l2; l >= this.level.getMinBuildHeight(); --l) {
                blockpos$mutableblockpos1.setY(l);
                if (this.canPortalReplaceBlock(blockpos$mutableblockpos1)) {
                    for(j3 = l; l > this.level.getMinBuildHeight() && this.canPortalReplaceBlock(blockpos$mutableblockpos1.move(Direction.DOWN)); --l) {
                    }

                    if (l + 4 <= i) {
                        j1 = j3 - l;
                        if (j1 <= 0 || j1 >= 3) {
                            blockpos$mutableblockpos1.setY(l);
                            if (this.canHostFrame(blockpos$mutableblockpos1, blockpos$mutableblockpos, direction, 0)) {
                                double d2 = pos.distSqr(blockpos$mutableblockpos1);
                                if (this.canHostFrame(blockpos$mutableblockpos1, blockpos$mutableblockpos, direction, -1) && this.canHostFrame(blockpos$mutableblockpos1, blockpos$mutableblockpos, direction, 1) && (d0 == -1.0 || d0 > d2)) {
                                    d0 = d2;
                                    blockpos = blockpos$mutableblockpos1.immutable();
                                }

                                if (d0 == -1.0 && (d1 == -1.0 || d1 > d2)) {
                                    d1 = d2;
                                    blockpos1 = blockpos$mutableblockpos1.immutable();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean canPortalReplaceBlock(BlockPos.MutableBlockPos pos) {
        BlockState blockstate = this.level.getBlockState(pos);
        return blockstate.canBeReplaced() && blockstate.getFluidState().isEmpty();
    }

    private boolean canHostFrame(BlockPos originalPos, BlockPos.MutableBlockPos offsetPos, Direction p_direction, int offsetScale) {
        Direction direction = p_direction.getClockWise();

        for(int i = -1; i < 3; ++i) {
            for(int j = -1; j < 4; ++j) {
                offsetPos.setWithOffset(originalPos, p_direction.getStepX() * i + direction.getStepX() * offsetScale, j, p_direction.getStepZ() * i + direction.getStepZ() * offsetScale);
                if (j < 0 && !this.level.getBlockState(offsetPos).isSolid()) {
                    return false;
                }

                if (j >= 0 && !this.canPortalReplaceBlock(offsetPos)) {
                    return false;
                }
            }
        }

        return true;
    }
}
