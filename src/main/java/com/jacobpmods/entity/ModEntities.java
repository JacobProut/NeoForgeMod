package com.jacobpmods.entity;

import com.jacobpmods.entity.custom.SkeletalZombieEntity;
import com.jacobpmods.neomod.FirstNeoMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, FirstNeoMod.MOD_ID);


    public static final Supplier<EntityType<SkeletalZombieEntity>> SKELETAL_ZOMBIE =
            ENTITY_TYPES.register("skeletalzombie", () -> EntityType.Builder.of(SkeletalZombieEntity::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("skeletalzombie"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
