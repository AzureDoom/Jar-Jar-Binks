package mod.azure.jarjarbinks.client.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import mod.azure.jarjarbinks.client.models.DarthJarJarModel;
import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DarthJarJarRender extends GeoEntityRenderer<DarthJarJarEntity> {

	public DarthJarJarRender(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new DarthJarJarModel());
	}

	@Override
	public RenderType getRenderType(DarthJarJarEntity animatable, float partialTicks, PoseStack stack,
			MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureResource(animatable));
	}

}