package mod.azure.jarjarbinks.client.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import mod.azure.jarjarbinks.client.models.JarJarModel;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class JarJarRender extends GeoEntityRenderer<JarJarBinksEntity> {

	public JarJarRender(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new JarJarModel());
	}

	@Override
	public RenderType getRenderType(JarJarBinksEntity animatable, float partialTicks, PoseStack stack,
			MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureResource(animatable));
	}

}