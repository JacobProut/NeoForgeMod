package com.jacobpmods.neomod.item.custom.enchantment.effects;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public record IceBarrageMeleeEnchantmentEffect(int level) implements EnchantmentEntityEffect {
    public static final MapCodec<IceBarrageMeleeEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(Codec.INT.fieldOf("level").forGetter(IceBarrageMeleeEnchantmentEffect::level))
                    .apply(instance, IceBarrageMeleeEnchantmentEffect::new));

    private static final long ICE_DURATION_SECONDS_LVL_1 = 2; // Duration in seconds
    private static final long ICE_DURATION_SECONDS_LVL_2 = 4; // Duration in seconds
    private static final long ICE_DURATION_SECONDS_LVL_3 = 7; // Duration in seconds



    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if (enchantmentLevel == 1 && entity instanceof LivingEntity target) {
            BlockPos targetPos = new BlockPos(entity.blockPosition());

            // Loop to spawn ice blocks in a 3x3x3 area
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        BlockPos icePos = targetPos.offset(x, y, z);
                        BlockState iceBlock = Blocks.ICE.defaultBlockState();

                        // Only replace air or water blocks with ice
                        if (level.getBlockState(icePos).isAir() || level.getBlockState(icePos).is(Blocks.WATER)) {
                            level.setBlockAndUpdate(icePos, iceBlock);

                            // Schedule a task to remove the ice block after a delay
                            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                            executorService.schedule(() -> {
                                // Run this on the main server thread
                                level.getServer().execute(() -> {
                                    // Check if the block is ice before removing it
                                    if (level.getBlockState(icePos).is(Blocks.ICE)) {
                                        level.setBlockAndUpdate(icePos, Blocks.AIR.defaultBlockState());
                                    }
                                });
                            }, ICE_DURATION_SECONDS_LVL_1, TimeUnit.SECONDS);
                        }
                    }
                }
            }

            // Play sound effects
            level.playSound(null, targetPos, SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 1.0f, 1.0f);
            // Spawn particles for visual effect
            level.sendParticles(ParticleTypes.SNOWFLAKE, targetPos.getX(), targetPos.getY() + 1, targetPos.getZ(), 20, 0.5, 0.5, 0.5, 0.1);

            // Apply freezing effect
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 4)); // Slowness level 4 for 5 seconds
        }
        if (enchantmentLevel == 2 && entity instanceof LivingEntity target) {
            BlockPos targetPos = new BlockPos(entity.blockPosition());

            // Loop to spawn ice blocks in a 3x3x3 area
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        BlockPos icePos = targetPos.offset(x, y, z);
                        BlockState iceBlock = Blocks.ICE.defaultBlockState();

                        // Only replace air or water blocks with ice
                        if (level.getBlockState(icePos).isAir() || level.getBlockState(icePos).is(Blocks.WATER)) {
                            level.setBlockAndUpdate(icePos, iceBlock);

                            // Schedule a task to remove the ice block after a delay
                            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                            executorService.schedule(() -> {
                                // Run this on the main server thread
                                level.getServer().execute(() -> {
                                    // Check if the block is ice before removing it
                                    if (level.getBlockState(icePos).is(Blocks.ICE)) {
                                        level.setBlockAndUpdate(icePos, Blocks.AIR.defaultBlockState());
                                    }
                                });
                            }, ICE_DURATION_SECONDS_LVL_2, TimeUnit.SECONDS);
                        }
                    }
                }
            }

            // Play sound effects
            level.playSound(null, targetPos, SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 1.0f, 1.0f);
            // Spawn particles for visual effect
            level.sendParticles(ParticleTypes.SNOWFLAKE, targetPos.getX(), targetPos.getY() + 1, targetPos.getZ(), 20, 0.5, 0.5, 0.5, 0.1);

            // Apply freezing effect
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 4)); // Slowness level 4 for 5 seconds
        }

        if (enchantmentLevel == 3 && entity instanceof LivingEntity target) {
            BlockPos targetPos = new BlockPos(entity.blockPosition());

            // Loop to spawn ice blocks in a 3x3x3 area
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        BlockPos icePos = targetPos.offset(x, y, z);
                        BlockState iceBlock = Blocks.ICE.defaultBlockState();

                        // Only replace air or water blocks with ice
                        if (level.getBlockState(icePos).isAir() || level.getBlockState(icePos).is(Blocks.WATER)) {
                            level.setBlockAndUpdate(icePos, iceBlock);

                            // Schedule a task to remove the ice block after a delay
                            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                            executorService.schedule(() -> {
                                // Run this on the main server thread
                                level.getServer().execute(() -> {
                                    // Check if the block is ice before removing it
                                    if (level.getBlockState(icePos).is(Blocks.ICE)) {
                                        level.setBlockAndUpdate(icePos, Blocks.AIR.defaultBlockState());
                                    }
                                });
                            }, ICE_DURATION_SECONDS_LVL_3, TimeUnit.SECONDS);
                        }
                    }
                }
            }

            // Play sound effects
            level.playSound(null, targetPos, SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 1.0f, 1.0f);
            // Spawn particles for visual effect
            level.sendParticles(ParticleTypes.SNOWFLAKE, targetPos.getX(), targetPos.getY() + 1, targetPos.getZ(), 20, 0.5, 0.5, 0.5, 0.1);

            // Apply freezing effect
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 140, 4)); // Slowness level 4 for 5 seconds
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}