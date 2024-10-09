package com.jacobpmods.neomod.item.custom.food;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class speedapple extends Item {

    public speedapple(Properties properties) {
        super(properties);
    }


    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        // Apply Speed II for 30 seconds (600 ticks)
        if (!level.isClientSide) {
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 1)); // Speed II
        }

        // Call the parent method to handle regular item eating logic
        return super.finishUsingItem(stack, level, entity);
    }

    // Override this to allow eating when not hungry
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        // Allows consuming the item even if the player is not hungry
        player.startUsingItem(hand);
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}