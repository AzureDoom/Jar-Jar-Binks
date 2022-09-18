package mod.azure.jarjarbinks.registry;

import mod.azure.jarjarbinks.JarJarBinksMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSoundEvents {

	public static SoundEvent JARDEATH = of("jarjar.howwude");
	public static SoundEvent JARNORMAL = of("jarjar.jarjarluvsyou");
	public static SoundEvent JARHURT = of("jarjar.meesadoanuthin");

	public static SoundEvent DARTHDEATH = of("jarjar.yousamaystrike");
	public static SoundEvent DARTHNORMAL = of("jarjar.yousagonnadie");
	public static SoundEvent DARTHHURT = of("jarjar.uhohyousadidafuckywucky");

	static SoundEvent of(String id) {
		SoundEvent sound = new SoundEvent(new ResourceLocation(JarJarBinksMod.MODID, id));
		Registry.register(Registry.SOUND_EVENT, new ResourceLocation(JarJarBinksMod.MODID, id), sound);
		return sound;
	}

}
