package com.jacobpmods.entity.client.skeletal.enderman;

import com.jacobpmods.entity.custom.SkeletalEndermanEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SkeletalEndermanModel extends HierarchicalModel<SkeletalEndermanEntity> {
        private final ModelPart skeletalEnderman;
        private final ModelPart Head;
        private final ModelPart Body;
        private final ModelPart RightArm;
        private final ModelPart LeftArm;
        private final ModelPart RightLeg;
        private final ModelPart LeftLeg;

        public SkeletalEndermanModel(ModelPart root) {
            this.skeletalEnderman = root.getChild("skeletalEnderman");
            this.Head = this.skeletalEnderman.getChild("Head");
            this.Body = this.skeletalEnderman.getChild("Body");
            this.RightArm = this.skeletalEnderman.getChild("RightArm");
            this.LeftArm = this.skeletalEnderman.getChild("LeftArm");
            this.RightLeg = this.skeletalEnderman.getChild("RightLeg");
            this.LeftLeg = this.skeletalEnderman.getChild("LeftLeg");
        }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition SkeletalEnderman = partdefinition.addOrReplaceChild("skeletalEnderman", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Head = SkeletalEnderman.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -24.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F))
                .texOffs(0, 16).addBox(-4.0F, -22.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, -24.0F, 0.0F));

        PartDefinition Body = SkeletalEnderman.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(32, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -38.0F, 0.0F));

        PartDefinition RightArm = SkeletalEnderman.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(56, 0).addBox(-3.0F, -2.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -36.0F, 0.0F));

        PartDefinition LeftArm = SkeletalEnderman.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(56, 0).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -36.0F, 0.0F));

        PartDefinition RightLeg = SkeletalEnderman.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(56, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -26.0F, 0.0F));

        PartDefinition LeftLeg = SkeletalEnderman.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(56, 0).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, -26.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void setupAnim(SkeletalEndermanEntity skeletalEndermanEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(SkeletalEndermanAnimations.SKELETAL_ENDERMAN_WALK, limbSwing, limbSwingAmount, 1f, 2.f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, - 30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.Head.yRot = headYaw * ((float)Math.PI / 180f);
        this.Head.xRot = headPitch * ((float)Math.PI / 180f);

    }
        @Override
        public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
            skeletalEnderman.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        }

    @Override
    public ModelPart root() {
        return skeletalEnderman;
    }

}
