package com.jacobpmods.neomod.worldgen;

import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.neomod.fluid.ModFluids;
import com.jacobpmods.neomod.worldgen.biome.surface.ModSurfaceRules;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;

public class ModNoiseGeneratorSettings {
    public static final ResourceKey<NoiseGeneratorSettings> GHOSTLY_BIOME_GENERATION = ResourceKey.create(
            Registries.NOISE_SETTINGS, ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID,"ghostly_dim")
    );
    public static void bootstrap(BootstrapContext<NoiseGeneratorSettings> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        // Trees
        NoiseGeneratorSettings settings =  NoiseGeneratorSettings.overworld(context, false, true);
        context.register(GHOSTLY_BIOME_GENERATION,
                new NoiseGeneratorSettings(
                        new NoiseSettings(0, 384, 1, 2),
                        ModBlocks.GHOSTLY_STONE.get().defaultBlockState(),
                        ModFluids.SOURCE_POISONED_WATER.get().defaultFluidState().createLegacyBlock(),  // Use poisoned water as the default fluid
                        settings.noiseRouter(),
                        ModSurfaceRules.makeRules(),
                        settings.spawnTarget(),
                        settings.seaLevel(),
                        false,
                        false,
                        false,
                        false
                )
        );
    }
}