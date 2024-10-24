package com.jacobpmods.entity.client.skeletal.zombie;

import com.jacobpmods.entity.client.ModModelLayers;
import com.jacobpmods.entity.custom.SkeletalZombieEntity;
import com.jacobpmods.neomod.FirstNeoMod;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SkeletalZombieRender extends MobRenderer<SkeletalZombieEntity, SkeletalZombieModel> {

    public SkeletalZombieRender(EntityRendererProvider.Context context) {
        super(context, new SkeletalZombieModel(context.bakeLayer(ModModelLayers.SKELETAL_ZOMBIE)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(SkeletalZombieEntity skeletalZombieEntity) {
        return ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "textures/entity/skeletalzombie/skeletal_zombie.png");
    }

    @Override
    public void render(SkeletalZombieEntity skeletalZombieEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(skeletalZombieEntity.isBaby()) {
            pPoseStack.scale(0.45f,0.45f,0.45f);
        }
        super.render(skeletalZombieEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);


    }
}
