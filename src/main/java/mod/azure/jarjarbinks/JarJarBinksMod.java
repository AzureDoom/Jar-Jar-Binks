package mod.azure.jarjarbinks;

import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import mod.azure.jarjarbinks.entity.JarJarSpawnEggItem;
import mod.azure.jarjarbinks.registry.ModEntities;
import mod.azure.jarjarbinks.registry.ModEntitySpawning;
import mod.azure.jarjarbinks.registry.ModSoundEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

public class JarJarBinksMod implements ModInitializer {

	public static ModEntities ENTITIES;
	public static ModSoundEvents SOUNDS;
	public static JarJarSpawnEggItem JARJAR_SPAWN_EGG;
	public static JarJarSpawnEggItem DARTHJARJAR_SPAWN_EGG;
	public static final String MODID = "jarjarbinks";

	@Override
	public void onInitialize() {
		SOUNDS = new ModSoundEvents();
		ENTITIES = new ModEntities();
		ModEntitySpawning.addSpawnEntries();
		Registry.register(Registry.ITEM, new ResourceLocation(JarJarBinksMod.MODID, "jarjar_spawn_egg"),
				new JarJarSpawnEggItem(ModEntities.JARJAR));
		Registry.register(Registry.ITEM, new ResourceLocation(JarJarBinksMod.MODID, "darthjarjar_spawn_egg"),
				new JarJarSpawnEggItem(ModEntities.DARTHJARJAR));
		FabricDefaultAttributeRegistry.register(ModEntities.JARJAR, JarJarBinksEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.DARTHJARJAR, DarthJarJarEntity.createMobAttributes());
	}
}
