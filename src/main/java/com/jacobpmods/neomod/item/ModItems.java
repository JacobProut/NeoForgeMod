package com.jacobpmods.neomod.item;

import com.jacobpmods.neomod.entity.ModEntities;
import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.item.custom.ModArmorItem;
import com.jacobpmods.neomod.item.custom.food.speedapple;
import com.jacobpmods.neomod.item.custom.tools.NexonAxe;
import com.jacobpmods.neomod.item.custom.tools.NexonPickaxe;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FirstNeoMod.MOD_ID);

    public static final DeferredItem<Item> speedapple = ITEMS.register("speedapple", () -> new speedapple(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F).build())));
    public static final DeferredItem<Item> fireball = ITEMS.register("throwablefireball", () -> new Item(new Item.Properties()));


    public static final DeferredItem<Item> nexon = ITEMS.register("nexon", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> heatednexon = ITEMS.register("nexonheated", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> nexoningot = ITEMS.register("nexoningot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> nexonreinforcedingot = ITEMS.register("nexonreinforcedingot", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> nexonpickaxe = ITEMS.register("nexonpickaxe",
            () -> new NexonPickaxe(ModToolTiers.NEXON, new Item.Properties().fireResistant()
                    .attributes(NexonPickaxe.createAttributes(ModToolTiers.NEXON,1.0F, -2.8F))));

    public static final DeferredItem<Item> nexonshovel = ITEMS.register("nexonshovel",
            () -> new ShovelItem(ModToolTiers.NEXON, new Item.Properties().fireResistant()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.NEXON,1.5F, -3.0F))));

    public static final DeferredItem<Item> nexonhoe = ITEMS.register("nexonhoe",
            () -> new HoeItem(ModToolTiers.NEXON, new Item.Properties().fireResistant()
                    .attributes(HoeItem.createAttributes(ModToolTiers.NEXON,0, -3.0F))));

    public static final DeferredItem<Item> nexonsword = ITEMS.register("nexonsword",
            () -> new SwordItem(ModToolTiers.NEXON, new Item.Properties().fireResistant()
                    .attributes(SwordItem.createAttributes(ModToolTiers.NEXON,5, -2.4F))));

    public static final DeferredItem<Item> nexonaxe = ITEMS.register("nexonaxe",
            () -> new NexonAxe(ModToolTiers.NEXON, new Item.Properties().fireResistant()
                    .attributes(NexonAxe.createAttributes(ModToolTiers.NEXON,6, -3.2F))));

    public static final DeferredItem<Item> nexonhelmet = ITEMS.register("nexonhelmet",
            () -> new ModArmorItem(ModArmorMaterials.NEXON, ArmorItem.Type.HELMET, new Item.Properties().fireResistant().durability(ArmorItem.Type.HELMET.getDurability(42))));
    public static final DeferredItem<Item> nexonchestplate = ITEMS.register("nexonchestplate",
            () -> new ModArmorItem(ModArmorMaterials.NEXON, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(42))));
    public static final DeferredItem<Item> nexonleggings = ITEMS.register("nexonleggings",
            () -> new ModArmorItem(ModArmorMaterials.NEXON, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant().durability(ArmorItem.Type.LEGGINGS.getDurability(42))));
    public static final DeferredItem<Item> nexonboots = ITEMS.register("nexonboots",
            () -> new ModArmorItem(ModArmorMaterials.NEXON, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant().durability(ArmorItem.Type.BOOTS.getDurability(42))));


    public static final DeferredItem<Item> SKELETAL_ZOMBIE_SPAWN_EGG = ITEMS.register("skeletal_zombie_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SKELETAL_ZOMBIE, 0xFFFFFF, 0xD3D3D3, new Item.Properties()));

    public static final DeferredItem<Item> SKELETAL_ENDERMAN_SPAWN_EGG = ITEMS.register("skeletal_enderman_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SKELETAL_ENDERMAN, 0xFFFFFF, 0xD3D3D3, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
