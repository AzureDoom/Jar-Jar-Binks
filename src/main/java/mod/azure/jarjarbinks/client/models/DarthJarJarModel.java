package mod.azure.jarjarbinks.client.models;

import mod.azure.jarjarbinks.JarJarBinksMod;
import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class DarthJarJarModel extends GeoModel<DarthJarJarEntity> {

	@Override
	public ResourceLocation getModelResource(DarthJarJarEntity object) {
		return new ResourceLocation(JarJarBinksMod.MODID, "geo/jarjarbinks.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(DarthJarJarEntity object) {
		return new ResourceLocation(JarJarBinksMod.MODID, "textures/entity/darthjarjar.png");
	}

	@Override
	public ResourceLocation getAnimationResource(DarthJarJarEntity object) {
		return new ResourceLocation(JarJarBinksMod.MODID, "animations/jarjarbinks.animation.json");
	}

	@Override
	public void setCustomAnimations(DarthJarJarEntity animatable, long instanceId,
			AnimationState<DarthJarJarEntity> animationState) {
		super.setCustomAnimations(animatable, instanceId, animationState);
		CoreGeoBone neck = getAnimationProcessor().getBone("head");
		CoreGeoBone Left_arm = getAnimationProcessor().getBone("leftarm");
		CoreGeoBone Right_arm = getAnimationProcessor().getBone("rightarm");
		CoreGeoBone Left_leg = getAnimationProcessor().getBone("leftleg");
		CoreGeoBone Right_leg = getAnimationProcessor().getBone("rightleg");
		EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
		if (neck != null) {
			neck.setRotX((entityData.headPitch() * (((float) Math.PI) / 180F)));
			neck.setRotY((entityData.netHeadYaw() * (((float) Math.PI) / 180F)));
		}
		if (Left_arm != null && !animatable.isAggressive()) {
			Left_arm.setRotX(Mth.cos(animatable.animationPosition * 0.6662F) * 2.0F * animatable.animationSpeed * 0.5F);
		}
		if (Right_arm != null && !animatable.isAggressive()) {
			Right_arm.setRotX(Mth.cos(animatable.animationPosition * 0.6662F + 3.1415927F) * 2.0F
					* animatable.animationSpeed * 0.5F);
		}
		if (Left_leg != null) {
			Left_leg.setRotX(Mth.cos(animatable.animationPosition * 0.6662F + 3.1415927F) * 1.4F
					* animatable.animationSpeed * 0.5F);
		}
		if (Right_leg != null) {
			Right_leg
					.setRotX(Mth.cos(animatable.animationPosition * 0.6662F) * 1.4F * animatable.animationSpeed * 0.5F);
		}
	}

	@Override
	public RenderType getRenderType(DarthJarJarEntity animatable, ResourceLocation texture) {
		return RenderType.entityTranslucent(getTextureResource(animatable));
	}
}