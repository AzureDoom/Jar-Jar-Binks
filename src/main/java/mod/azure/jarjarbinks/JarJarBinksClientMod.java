package mod.azure.jarjarbinks;

import mod.azure.jarjarbinks.client.renders.DarthJarJarRender;
import mod.azure.jarjarbinks.client.renders.JarJarRender;
import mod.azure.jarjarbinks.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class JarJarBinksClientMod implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(ModEntities.JARJAR, (ctx) -> new JarJarRender(ctx));
		EntityRendererRegistry.register(ModEntities.DARTHJARJAR, (ctx) -> new DarthJarJarRender(ctx));
	}

}
