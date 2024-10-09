package com.jacobpmods.neomod.item.custom.enchantment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public record MagmaMineEnchantmentEffect(int level) implements EnchantmentEntityEffect {
    public static final MapCodec<MagmaMineEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(Codec.INT.fieldOf("level").forGetter(MagmaMineEnchantmentEffect::level))
                    .apply(instance, MagmaMineEnchantmentEffect::new));


    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if (enchantmentLevel == 1) {
            BlockPos blockPos = new BlockPos((int) origin.x(), (int) origin.y(), (int) origin.z());
            BlockState blockState = level.getBlockState(blockPos);

            // Check if the block is iron ore
            if (blockState.is(Blocks.IRON_ORE)) {
                // Destroy the original block without drops
                level.destroyBlock(blockPos, false);

                // Drop an iron ingot instead of the iron ore
                Block.popResource(level, blockPos, new ItemStack(Items.IRON_INGOT));
            } else if (blockState.is(Blocks.GOLD_ORE)) {
                // Similar behavior for gold ore, if desired
                level.destroyBlock(blockPos, false);

                // Drop a gold ingot instead of the gold ore
                Block.popResource(level, blockPos, new ItemStack(Items.GOLD_INGOT));
            }
        }
    }


    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
