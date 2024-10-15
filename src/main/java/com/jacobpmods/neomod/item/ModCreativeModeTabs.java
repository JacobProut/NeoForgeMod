package com.jacobpmods.neomod.item;

import com.jacobpmods.block.ModBlocks;
import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.item.custom.enchantment.ModEnchantmentEffects;
import com.jacobpmods.neomod.item.custom.enchantment.ModEnchantments;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
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

                        //Nexon Items
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
                        output.accept(ModItems.nexonhelmet);
                        output.accept(ModItems.nexonchestplate);
                        output.accept(ModItems.nexonleggings);
                        output.accept(ModItems.nexonboots);

                        //Nexon Blocks
                        output.accept(ModBlocks.NEXON_BLOCK.get());
                        output.accept(ModBlocks.NEXON_ORE_BLOCK.get());

                        //Ghostly Blocks
                        output.accept(ModBlocks.GHOSTLY_GRASS_BLOCK.get());
                        output.accept(ModBlocks.GHOSTLY_DIRT.get());
                        output.accept(ModBlocks.LOG_GHOSTLY.get());
                        output.accept(ModBlocks.PLANKS_GHOSTLY.get());
                        output.accept(ModBlocks.GHOSTLY_SAPLING.get());
                        output.accept(ModBlocks.GHOSTLY_LEAVES.get());
                        output.accept(ModBlocks.GHOSTLY_WEB.get());


                        //Find a way to add magma mine book

                    }).build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
