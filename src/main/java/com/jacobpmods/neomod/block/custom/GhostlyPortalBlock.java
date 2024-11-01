package com.jacobpmods.neomod.block.custom;

import com.jacobpmods.neomod.entity.ModEntities;
import com.jacobpmods.neomod.worldgen.dimension.ModDimensions;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.slf4j.Logger;

import javax.annotation.Nullable;

public class GhostlyPortalBlock extends Block implements Portal {
    public static final MapCodec<GhostlyPortalBlock> CODEC = simpleCodec(GhostlyPortalBlock::new);
    public static final EnumProperty<Direction.Axis> AXIS;
    private static final Logger LOGGER;
    protected static final VoxelShape X_AXIS_AABB;
    protected static final VoxelShape Z_AXIS_AABB;

    static {
        AXIS = BlockStateProperties.HORIZONTAL_AXIS;
        LOGGER = LogUtils.getLogger();
        // Define shapes for the ghostly portal
        X_AXIS_AABB = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);
        Z_AXIS_AABB = Block.box(0.0D, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D);
    }

    public MapCodec<GhostlyPortalBlock> codec() {
        return CODEC;
    }

    public GhostlyPortalBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(AXIS, Direction.Axis.X));
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(AXIS) == Direction.Axis.Z ? Z_AXIS_AABB : X_AXIS_AABB;
    }

    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(100) == 0) {
            // Spawn ghostly entities or particles, for example
            Entity ghostEntity = ModEntities.SKELETAL_ZOMBIE.get().spawn(level, pos.above(), MobSpawnType.STRUCTURE);
            if (ghostEntity != null) {
                ghostEntity.setPortalCooldown();
            }
        }
    }

    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity.canUsePortal(false)) {
            entity.setAsInsidePortal(this, pos);
        }
    }

    @Nullable
    public DimensionTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos pos) {
        // Check if the current dimension is the Overworld
        if (level.dimension() == Level.OVERWORLD) {
            ServerLevel ghostlyLevel = level.getServer().getLevel(ModDimensions.GHOSTLY_LEVEL_KEY);
            if (ghostlyLevel != null) {
                // Specify the position you want to teleport to in the ghostly dimension
                return new DimensionTransition(ghostlyLevel, entity, DimensionTransition.PLAY_PORTAL_SOUND);
            }
        }

        // Optionally, add logic for other dimensions if needed
        return null;
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextInt(10) == 0) {
            level.playLocalSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int i = 0; i < 4; ++i) {
            double d0 = pos.getX() + random.nextDouble();
            double d1 = pos.getY() + random.nextDouble();
            double d2 = pos.getZ() + random.nextDouble();
            level.addParticle(ParticleTypes.PORTAL, d0, d1, d2, 0, 0, 0);
        }
    }

    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    protected BlockState rotate(BlockState state, Rotation rot) {
        switch (rot) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch (state.getValue(AXIS)) {
                    case Z:
                        return state.setValue(AXIS, Direction.Axis.X);
                    case X:
                        return state.setValue(AXIS, Direction.Axis.Z);
                }
            default:
                return state;
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }
}