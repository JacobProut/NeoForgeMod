package com.jacobpmods.neomod.fluid;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.block.fluids.PoisonedWaterBlock;
import com.jacobpmods.neomod.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(BuiltInRegistries.FLUID, FirstNeoMod.MOD_ID);

    public static final Supplier<FlowingFluid> SOURCE_POISONED_WATER = FLUIDS.register("source_poisoned_water",
            () -> new BaseFlowingFluid.Source(ModFluids.POISONED_WATER_PROPERTIES));
    public static final Supplier<FlowingFluid> FLOWING_POISONED_WATER = FLUIDS.register("flowing_poisoned_water",
            () -> new BaseFlowingFluid.Flowing(ModFluids.POISONED_WATER_PROPERTIES));
    public static final DeferredBlock<LiquidBlock> POISONED_WATER_BLOCK = ModBlocks.BLOCKS.register("poisoned_water_block",
            () -> new PoisonedWaterBlock(ModFluids.SOURCE_POISONED_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()));
    public static final DeferredItem<Item> POISONED_WATER_BUCKET = ModItems.ITEMS.registerItem("poisoned_water_bucket",
            properties -> new BucketItem(ModFluids.SOURCE_POISONED_WATER.get(), properties.craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final BaseFlowingFluid.Properties POISONED_WATER_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.POISONED_WATER_FLUID_TYPE, SOURCE_POISONED_WATER, FLOWING_POISONED_WATER)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModFluids.POISONED_WATER_BLOCK).bucket(ModFluids.POISONED_WATER_BUCKET);



    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
