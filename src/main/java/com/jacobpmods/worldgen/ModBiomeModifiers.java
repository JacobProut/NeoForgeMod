package com.jacobpmods.worldgen;

import com.jacobpmods.neomod.FirstNeoMod;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_TREE_GHOSTLY = registerKey("add_tree_ghostly");



    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_TREE_GHOSTLY, new BiomeModifiers.AddFeaturesBiomeModifier(
            biomes.getOrThrow(Tags.Biomes.IS_PLAINS), // I assume change this to a different biome for it to spawn else where.
                // For multiple biomes use: HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.BIRCH_FOREST))
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.GHOSTLY_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }
    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, name));
    }
}
