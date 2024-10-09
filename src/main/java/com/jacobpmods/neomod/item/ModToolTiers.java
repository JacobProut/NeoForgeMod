package com.jacobpmods.neomod.item;

import com.jacobpmods.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
        public static final Tier NEXON = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_NEXON_TOOL,3000, 11, 5f, 15,
                () -> Ingredient.of(ModItems.heatednexon));

    }



