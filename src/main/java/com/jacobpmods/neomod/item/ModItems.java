package com.jacobpmods.neomod.item;

import com.jacobpmods.neomod.FirstNeoMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FirstNeoMod.MOD_ID);

    public static final DeferredItem<Item> nexon = ITEMS.register("nexon", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> heatednexon = ITEMS.register("nexonheated", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
