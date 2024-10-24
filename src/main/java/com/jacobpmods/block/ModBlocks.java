package com.jacobpmods.block;

import com.jacobpmods.block.custom.ModFlammableRotatedPillarBlock;
import com.jacobpmods.block.custom.ModSaplingBlock;
import com.jacobpmods.block.custom.PedestalBlock;
import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.item.ModItems;
import com.jacobpmods.neomod.item.custom.block.GhostlyBlock;
import com.jacobpmods.neomod.item.custom.block.GhostlyDirtBlock;
import com.jacobpmods.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
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

    //Ghostly Grass/Dirt
    public static final DeferredBlock<Block> GHOSTLY_GRASS_BLOCK = registerBlock("ghostly_grass_block",
            GhostlyBlock::new);
    public static final DeferredBlock<Block> GHOSTLY_DIRT = registerBlock("ghostly_dirt",
            GhostlyDirtBlock::new);

    //Ghostly Wood items
    public static final DeferredBlock<Block> LOG_GHOSTLY = registerBlock("log_ghostly", () ->new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
            .strength(2f).sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> STRIPPED_GHOSTLY_LOG = registerBlock("stripped_ghostly_log", () ->new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
            .strength(1f).sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> WOOD_GHOSTLY = registerBlock("wood_ghostly", () ->new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
            .strength(2f).sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> STRIPPED_GHOSTLY_WOOD = registerBlock("stripped_ghostly_wood", () ->new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
            .strength(1f).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> PLANKS_GHOSTLY = registerBlock("planks_ghostly", () ->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            .strength(1f).sound(SoundType.WOOD)) {


        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });

    public static final DeferredBlock<Block> GHOSTLY_LEAVES = registerBlock("ghostly_leaves", () ->new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
            .strength(1f).sound(SoundType.CHERRY_LEAVES)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 60;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 30;
        }
    });

    public static final DeferredBlock<Block> GHOSTLY_SAPLING = registerBlock("ghostly_sapling",
            () -> new ModSaplingBlock(ModTreeGrowers.GHOSTLY, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SAPLING)
            .strength(0.2f).sound(SoundType.CHERRY_SAPLING),  ModBlocks.GHOSTLY_GRASS_BLOCK.get(), ModBlocks.GHOSTLY_DIRT.get(), Blocks.GRASS_BLOCK, Blocks.DIRT));


    //will edit later. PLACEHOLDER FOR TELEPORTER TO DIMENSION
    public static final DeferredBlock<Block> GHOSTLY_WEB = registerBlock("ghostly_web", () ->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBWEB)
            .strength(1f).sound(SoundType.COBWEB)));

    public static final DeferredBlock<Block> GHOSTLY_STONE = registerBlock("ghostly_stone", () ->new Block(BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> PEDESTAL = registerBlock("pedestal", () ->new PedestalBlock(BlockBehaviour.Properties.of()
            .noOcclusion().sound(SoundType.STONE)));

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
