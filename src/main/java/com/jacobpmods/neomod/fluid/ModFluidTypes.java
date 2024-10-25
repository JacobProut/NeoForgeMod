package com.jacobpmods.neomod.fluid;

import com.jacobpmods.neomod.FirstNeoMod;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.joml.Vector3f;

import java.util.function.Supplier;

public class ModFluidTypes {
    public static final ResourceLocation WATER_STILL_RL = ResourceLocation.parse("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = ResourceLocation.parse("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = ResourceLocation.parse("block/water_overlay");
    public static final DeferredRegister<FluidType> FLUID_TYPE =
            DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, FirstNeoMod.MOD_ID);



    public static final Supplier<FluidType> POISONED_WATER_FLUID_TYPE = registerFluidType("poisoned_water_fluid",
            new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, WATER_OVERLAY_RL, 0xFF32CD32,  // Lime Green hex color with alpha
                    new Vector3f(50f / 255f, 205f / 255f, 50f / 255f),  // Lime Green RGB values normalized to [0,1]
                    FluidType.Properties.create()));


    private static Supplier<FluidType> registerFluidType(String name, FluidType fluidType) {
        return FLUID_TYPE.register(name, () -> fluidType);
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPE.register(eventBus);
    }
}
