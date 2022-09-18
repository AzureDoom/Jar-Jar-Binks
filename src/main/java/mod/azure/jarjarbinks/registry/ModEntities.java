package mod.azure.jarjarbinks.registry;

import mod.azure.jarjarbinks.JarJarBinksMod;
import mod.azure.jarjarbinks.entity.DarthJarJarEntity;
import mod.azure.jarjarbinks.entity.JarJarBinksEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {

	public static final EntityType<JarJarBinksEntity> JARJAR = Registry.register(Registry.ENTITY_TYPE,
			new ResourceLocation(JarJarBinksMod.MODID, "jarjar"),
			FabricEntityTypeBuilder.create(MobCategory.MONSTER, JarJarBinksEntity::new)
					.dimensions(EntityDimensions.fixed(0.6f, 1.95F)).trackRangeBlocks(90).trackedUpdateRate(1).build());

	public static final EntityType<DarthJarJarEntity> DARTHJARJAR = Registry.register(Registry.ENTITY_TYPE,
			new ResourceLocation(JarJarBinksMod.MODID, "darthbinks"),
			FabricEntityTypeBuilder.create(MobCategory.MONSTER, DarthJarJarEntity::new)
					.dimensions(EntityDimensions.fixed(0.6f, 1.95F)).trackRangeBlocks(90).trackedUpdateRate(1).build());
}
