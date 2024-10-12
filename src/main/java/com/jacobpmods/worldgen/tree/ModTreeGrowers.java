package com.jacobpmods.worldgen.tree;

import com.jacobpmods.neomod.FirstNeoMod;
import com.jacobpmods.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower GHOSTLY = new TreeGrower(FirstNeoMod.MOD_ID + ":ghostly",
            Optional.empty(), Optional.of(ModConfiguredFeatures.GHOSTLY_KEY), Optional.empty());
}
