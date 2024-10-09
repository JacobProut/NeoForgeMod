package com.jacobpmods.neomod.item;

import com.jacobpmods.block.ModBlocks;
import com.jacobpmods.neomod.FirstNeoMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FirstNeoMod.MOD_ID);

    public static final Supplier<CreativeModeTab> JACOBS_MODDED_ITEMS_TAB = CREATIVE_MODE_TABS.register("jacobs_modded_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.nexon.get()))
                    .title(Component.translatable("Jacobs Modded items")).displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.speedapple.get());
                        output.accept(ModItems.fireball.get());
                        output.accept(ModItems.nexon.get());
                        output.accept(ModItems.heatednexon.get());
                        output.accept(ModItems.nexoningot.get());
                        output.accept(ModItems.nexonreinforcedingot.get());
                        output.accept(ModBlocks.NEXON_BLOCK.get());
                        output.accept(ModBlocks.NEXON_ORE_BLOCK.get());
                        output.accept(ModItems.nexonpickaxe.get());
                        output.accept(ModItems.nexonhoe.get());
                        output.accept(ModItems.nexonshovel.get());
                        output.accept(ModItems.nexonsword.get());
                        output.accept(ModItems.nexonaxe.get());

                        output.accept(ModBlocks.NEXON_BLOCK.get());
                        output.accept(ModBlocks.NEXON_ORE_BLOCK.get());
                        output.accept(ModBlocks.GHOSTLY_GRASS_BLOCK.get());
                        output.accept(ModBlocks.GHOSTLY_DIRT.get());



                    }).build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
