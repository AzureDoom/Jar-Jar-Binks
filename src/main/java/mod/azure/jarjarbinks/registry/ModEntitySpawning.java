package mod.azure.jarjarbinks.registry;

import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class ModEntitySpawning {

	public static void addSpawnEntries() {
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.FROG), MobCategory.MONSTER,
				ModEntities.JARJAR, 50, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.PIGLIN), MobCategory.MONSTER,
				ModEntities.DARTHJARJAR, 50, 1, 1);
		SpawnPlacements.register(ModEntities.JARJAR, SpawnPlacements.Type.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JarJarBinksEntity::canSpawn);
		SpawnPlacements.register(ModEntities.DARTHJARJAR, SpawnPlacements.Type.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JarJarBinksEntity::canSpawn);
	}
}
