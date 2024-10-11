package com.jacobpmods.neomod.item.custom;

import com.google.common.collect.ImmutableMap;
import com.jacobpmods.neomod.item.ModArmorMaterials;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import java.util.List;
import java.util.Map;
public class ModArmorItem extends ArmorItem {
    private static final Map<Holder<ArmorMaterial>, List<MobEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<Holder<ArmorMaterial>, List<MobEffectInstance>>())
                    .put(ModArmorMaterials.NEXON,
                            List.of(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false)))
                    .build();

    public ModArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pEntity instanceof Player player) {
            if (!pLevel.isClientSide()) {
                if (hasFullSuitOfCorrectArmorOn(player, ModArmorMaterials.NEXON)) {
                    evaluateArmorEffects(player);
                } else {
                    // Remove the effect if the full armor is no longer worn
                    removeArmorEffects(player);
                }
            }
        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<Holder<ArmorMaterial>, List<MobEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            Holder<ArmorMaterial> mapArmorMaterial = entry.getKey();
            List<MobEffectInstance> mapEffect = entry.getValue();
            if (hasPlayerCorrectArmorOn(mapArmorMaterial, player)) {
                addEffectToPlayer(player, mapEffect);
            }
        }
    }

    private void addEffectToPlayer(Player player, List<MobEffectInstance> mapEffect) {
        for (MobEffectInstance effect : mapEffect) {
            if (!player.hasEffect(effect.getEffect())) {
                player.addEffect(new MobEffectInstance(effect.getEffect(),
                        effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.isVisible()));
            }
        }
    }

    private void removeArmorEffects(Player player) {
        for (Map.Entry<Holder<ArmorMaterial>, List<MobEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            List<MobEffectInstance> mapEffect = entry.getValue();
            for (MobEffectInstance effect : mapEffect) {
                if (player.hasEffect(effect.getEffect())) {
                    player.removeEffect(effect.getEffect());
                }
            }
        }
    }

    private boolean hasPlayerCorrectArmorOn(Holder<ArmorMaterial> mapArmorMaterial, Player player) {
        for (ItemStack armorStack : player.getArmorSlots()) {
            if (!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
            ArmorItem armorItem = (ArmorItem) armorStack.getItem();
            if (armorItem.getMaterial() != mapArmorMaterial) {
                return false;
            }
        }
        return true;
    }

    private boolean hasFullSuitOfCorrectArmorOn(Player player, Holder<ArmorMaterial> material) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !boots.isEmpty() && ((ArmorItem) boots.getItem()).getMaterial() == material &&
                !leggings.isEmpty() && ((ArmorItem) leggings.getItem()).getMaterial() == material &&
                !chestplate.isEmpty() && ((ArmorItem) chestplate.getItem()).getMaterial() == material &&
                !helmet.isEmpty() && ((ArmorItem) helmet.getItem()).getMaterial() == material;
    }
}