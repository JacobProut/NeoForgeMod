package com.jacobpmods.neomod.item.custom.enchantment;


import com.jacobpmods.util.glm.Vector3;
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
        Vector3 vector = new Vector3(origin.x(), origin.y(), origin.z());

        BlockPos blockPos = vector.toBlockPos();
        BlockState blockState = level.getBlockState(blockPos);

        // If the enchantment level is 1, replace the block with the air and drop the cooked variant of the ore
        if (enchantmentLevel == 1) {
            if (blockState.is(Blocks.IRON_ORE)) {
                level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState()); // Remove the ore block
                Block.popResource(level, blockPos, new ItemStack(Items.IRON_INGOT)); // Drop iron ingot
            } else if (blockState.is(Blocks.GOLD_ORE)) {
                level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState()); // Remove the ore block
                Block.popResource(level, blockPos, new ItemStack(Items.GOLD_INGOT)); // Drop gold ingot
            }
            // You can add more ores and their respective drops here
        }
    }


    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
