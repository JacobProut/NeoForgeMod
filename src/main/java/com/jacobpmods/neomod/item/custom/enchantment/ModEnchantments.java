package com.jacobpmods.neomod.item.custom.enchantment;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.item.custom.enchantment.effects.IceBarrageMeleeEnchantmentEffect;
import com.jacobpmods.neomod.item.custom.enchantment.effects.MagmaMineEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> MAGMA_MINE = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "magma_mine"));

    public static final ResourceKey<Enchantment> ICE_BARRAGE_MELEE = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "ice_barrage_melee"));



    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantment = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        register(context, MAGMA_MINE, Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                items.getOrThrow(ItemTags.PICKAXES), 5, 1,
                Enchantment.dynamicCost(2, 4), Enchantment.dynamicCost(2, 8), 3, EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantment.getOrThrow(EnchantmentTags.MINING_EXCLUSIVE))
                .withEffect(EnchantmentEffectComponents.HIT_BLOCK, new MagmaMineEnchantmentEffect(1))
        );

        register(context, ICE_BARRAGE_MELEE, Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        items.getOrThrow(ItemTags.SWORDS), 5, 3,
                        Enchantment.dynamicCost(2, 4), Enchantment.dynamicCost(2, 8), 3, EquipmentSlotGroup.MAINHAND))
                        .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM, new IceBarrageMeleeEnchantmentEffect(1))
        );
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }
}
