package com.jacobpmods.neomod.entity.client;

import com.jacobpmods.neomod.FirstNeoMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation SKELETAL_ZOMBIE = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "skeletal_zombie"), "main");

    public static final ModelLayerLocation SKELETAL_ENDERMAN = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "skeletal_enderman"), "main");
}
