package com.jacobpmods.neomod.entity.client.skeletal.enderman;

import com.jacobpmods.neomod.entity.client.ModModelLayers;
import com.jacobpmods.neomod.entity.custom.SkeletalEndermanEntity;
import com.jacobpmods.neomod.FirstNeoMod;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SkeletalEndermanRender extends MobRenderer<SkeletalEndermanEntity, SkeletalEndermanModel> {

        public SkeletalEndermanRender(EntityRendererProvider.Context context) {
            super(context, new SkeletalEndermanModel(context.bakeLayer(ModModelLayers.SKELETAL_ENDERMAN)), 0.4f);
        }

        @Override
        public ResourceLocation getTextureLocation(SkeletalEndermanEntity skeletalEndermanEntity) {
            return ResourceLocation.fromNamespaceAndPath(FirstNeoMod.MOD_ID, "textures/entity/skeletalenderman/skeletal_enderman.png");
        }

        @Override
        public void render(SkeletalEndermanEntity skeletalEndermanEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                           MultiBufferSource pBuffer, int pPackedLight) {
            if(skeletalEndermanEntity.isBaby()) {
                pPoseStack.scale(0.45f,0.45f,0.45f);
            }
            super.render(skeletalEndermanEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);


        }
}
