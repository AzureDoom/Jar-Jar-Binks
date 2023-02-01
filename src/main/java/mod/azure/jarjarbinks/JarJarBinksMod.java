package mod.azure.jarjarbinks;

import mod.azure.azurelib.items.AzureSpawnEgg;
import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import mod.azure.jarjarbinks.registry.ModEntities;
import mod.azure.jarjarbinks.registry.ModEntitySpawning;
import mod.azure.jarjarbinks.registry.ModSoundEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;

public class JarJarBinksMod implements ModInitializer {

	public static ModEntities ENTITIES;
	public static ModSoundEvents SOUNDS;
	public static final AzureSpawnEgg JARJAR_SPAWN_EGG = new AzureSpawnEgg(ModEntities.JARJAR, 16753920, 16777215);
	public static final AzureSpawnEgg DARTHJARJAR_SPAWN_EGG = new AzureSpawnEgg(ModEntities.DARTHJARJAR, 16753920, 16777215);
	public static final String MODID = "jarjarbinks";

	@Override
	public void onInitialize() {
		SOUNDS = new ModSoundEvents();
		ENTITIES = new ModEntities();
		ModEntitySpawning.addSpawnEntries();
		Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(JarJarBinksMod.MODID, "jarjar_spawn_egg"),
				JARJAR_SPAWN_EGG);
		Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(JarJarBinksMod.MODID, "darthjarjar_spawn_egg"),
				DARTHJARJAR_SPAWN_EGG);
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS)
				.register(entries -> entries.accept(JARJAR_SPAWN_EGG));
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS)
				.register(entries -> entries.accept(DARTHJARJAR_SPAWN_EGG));
		FabricDefaultAttributeRegistry.register(ModEntities.JARJAR, JarJarBinksEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.DARTHJARJAR, DarthJarJarEntity.createMobAttributes());
	}
}
