package com.jacobpmods.neomod.event;

import com.jacobpmods.neomod.entity.ModEntities;
import com.jacobpmods.neomod.entity.client.ModModelLayers;
import com.jacobpmods.neomod.entity.client.skeletal.enderman.SkeletalEndermanModel;
import com.jacobpmods.neomod.entity.client.skeletal.zombie.SkeletalZombieModel;
import com.jacobpmods.neomod.entity.custom.SkeletalEndermanEntity;
import com.jacobpmods.neomod.entity.custom.SkeletalZombieEntity;
import com.jacobpmods.neomod.FirstNeoMod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = FirstNeoMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.SKELETAL_ZOMBIE, SkeletalZombieModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SKELETAL_ENDERMAN, SkeletalEndermanModel::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SKELETAL_ZOMBIE.get(), SkeletalZombieEntity.createAttributes().build());
        event.put(ModEntities.SKELETAL_ENDERMAN.get(), SkeletalEndermanEntity.createAttributes().build());
    }

}

