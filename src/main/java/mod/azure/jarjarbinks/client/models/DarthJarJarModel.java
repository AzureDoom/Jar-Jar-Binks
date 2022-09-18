package mod.azure.jarjarbinks.client.models;

import com.mojang.math.Vector3f;

import mod.azure.jarjarbinks.JarJarBinksMod;
import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class DarthJarJarModel extends AnimatedTickingGeoModel<DarthJarJarEntity> {

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
	public void setLivingAnimations(DarthJarJarEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("head");
		IBone Left_arm = this.getAnimationProcessor().getBone("leftarm");
		IBone Right_arm = this.getAnimationProcessor().getBone("rightarm");
		IBone Left_leg = this.getAnimationProcessor().getBone("leftleg");
		IBone Right_leg = this.getAnimationProcessor().getBone("rightleg");

		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if (head != null) {
			head.setRotationX(Vector3f.XP.rotation(extraData.headPitch * ((float) Math.PI / 180F)).i());
			head.setRotationY(Vector3f.YP.rotation(extraData.netHeadYaw * ((float) Math.PI / 180F)).j());
		}
		if (Left_arm != null) {
			Left_arm.setRotationX(Vector3f.XP
					.rotation(Mth.cos(entity.animationPosition * 0.6662F) * 2.0F * entity.animationSpeed * 0.5F).i());
		}
		if (Right_arm != null) {
			Right_arm.setRotationX(Vector3f.XP.rotation(
					Mth.cos(entity.animationPosition * 0.6662F + 3.1415927F) * 2.0F * entity.animationSpeed * 0.5F)
					.i());
		}
		if (Left_leg != null) {
			Left_leg.setRotationX(Vector3f.XP.rotation(
					Mth.cos(entity.animationPosition * 0.6662F + 3.1415927F) * 1.4F * entity.animationSpeed * 0.5F)
					.i());
		}
		if (Right_leg != null) {
			Right_leg.setRotationX(Vector3f.XP
					.rotation(Mth.cos(entity.animationPosition * 0.6662F) * 1.4F * entity.animationSpeed * 0.5F).i());
		}
	}
}