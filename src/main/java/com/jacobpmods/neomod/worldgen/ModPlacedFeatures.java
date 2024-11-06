package com.jacobpmods.neomod.worldgen;

import com.jacobpmods.neomod.block.ModBlocks;
import com.jacobpmods.neomod.FirstNeoMod;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> GHOSTLY_PLACED_KEY = registerKey("ghostly_placed");

    public static final ResourceKey<PlacedFeature> OOZING_FLOWER_PLACED_KEY = registerKey("oozing_flower_placed");
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        LogUtils.getLogger().debug("BOOTSTRAP: Registering Placed Features for GHOSTLY_PLACED_KEY");
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, GHOSTLY_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.GHOSTLY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.GHOSTLY_SAPLING.get()));

        register(context,OOZING_FLOWER_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OOZING_FLOWER),
                List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));


    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        LogUtils.getLogger().debug("REGISTER_KEY: Creating ResourceKey for " + name);
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        LogUtils.getLogger().debug("REGISTER: Registering PlacedFeature with key " + key.location());
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
