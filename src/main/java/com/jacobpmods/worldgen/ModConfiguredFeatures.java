package com.jacobpmods.worldgen;

import com.jacobpmods.block.ModBlocks;
import com.jacobpmods.neomod.FirstNeoMod;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class ModConfiguredFeatures {
    // Configured Features -> Placed Features -> Placed inside of world Via BiomeModifiers
    public static final ResourceKey<ConfiguredFeature<?, ?>> GHOSTLY_KEY = registerKey("ghostly");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        LogUtils.getLogger().debug("BOOTSTRAP: Registering Configured Features for GHOSTLY_KEY");
        register(context, GHOSTLY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LOG_GHOSTLY.get()),
                new StraightTrunkPlacer(3, 3, 3), //|Defines trunk of the tree| Base height of the trunk, Random additional height that can be added, Height of the trunk where branches might appear or additional foliage can grow.
                BlockStateProvider.simple(ModBlocks.GHOSTLY_LEAVES.get()), //Specifies what blocks for leaves
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 3), //|Determines how leaves are placed around the trunk| Radius of foliage blob at top most part of tree, Controls the foliage radius as you go downwards from the top[For each level, the radius decrease 'Said' blocks, The number of layers of leaves that will be placed starting from the top of the trunk downwards.
                new TwoLayersFeatureSize(1, 0, 2)).build() //|Controls the number of layers at the top of the tree where branches can spawn| The size of the truck that can be uninterrupted at the base, the additional radius added to first layer, additional height for second layer
        );
        // Add debug logs to ensure this runs only once.
        LogUtils.getLogger().debug("Registering Configured Feature: GHOSTLY_KEY");
    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        LogUtils.getLogger().debug("REGISTER_KEY: Creating ResourceKey for " + name);
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        LogUtils.getLogger().debug("REGISTER: Registering ConfiguredFeature with key " + key.location());
                context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
