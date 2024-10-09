package com.jacobpmods.block;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FirstNeoMod.MOD_ID);

    public static final DeferredBlock<Block> NEXON_BLOCK = registerBlock("nexon_block", () ->new Block(BlockBehaviour.Properties.of()
            .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST_CLUSTER)));

    public static final DeferredBlock<Block> NEXON_ORE_BLOCK = registerBlock("nexon_ore_block", () ->new Block(BlockBehaviour.Properties.of()
            .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST_CLUSTER)));

    public static final DeferredBlock<Block> GHOSTLY_GRASS_BLOCK = registerBlock("ghostly_grass_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(1f).sound(SoundType.GRASS)));

    public static final DeferredBlock<Block> GHOSTLY_DIRT = registerBlock("ghostly_dirt",
            () -> new Block(BlockBehaviour.Properties.of().strength(1f).sound(SoundType.GRASS)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
