package com.jacobpmods.neomod.block.fluids;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

public class PoisonedWaterBlock extends LiquidBlock {
    public PoisonedWaterBlock(FlowingFluid fluid, Properties properties) {
        super(fluid, properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            // Check if the entity already has a poison effect
            if (!livingEntity.hasEffect(MobEffects.POISON)) {
                // Apply poison effect continuously while the entity is in the fluid
                // Apply poison effect (duration 200 ticks = 10 seconds, level 0 = Poison I)
                livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 40, 1, true, false, true));
            }
            super.entityInside(state, level, pos, entity);
        }
    }
}
