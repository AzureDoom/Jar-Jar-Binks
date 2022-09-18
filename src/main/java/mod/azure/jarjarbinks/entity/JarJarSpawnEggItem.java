package mod.azure.jarjarbinks.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class JarJarSpawnEggItem extends SpawnEggItem {

	public JarJarSpawnEggItem(EntityType<? extends Mob> type) {
		super(type, 16753920, 16777215, new Item.Properties().stacksTo(64).tab(CreativeModeTab.TAB_MISC));
	}
}