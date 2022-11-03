package mod.azure.jarjarbinks.entity;

import mod.azure.jarjarbinks.registry.ModSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class DarthJarJarEntity extends JarJarBinksEntity implements IAnimatable, IAnimationTickable {

	public AnimationFactory factory = GeckoLibUtil.createFactory(this);

	public DarthJarJarEntity(EntityType<? extends JarJarBinksEntity> entityType, Level worldIn) {
		super(entityType, worldIn);
	}

	public static AttributeSupplier.Builder createMobAttributes() {
		return Mob.createLivingAttributes().add(Attributes.FOLLOW_RANGE, 50.0D).add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.5D).add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
				.add(Attributes.MOVEMENT_SPEED, 0.35D).add(Attributes.ATTACK_DAMAGE, 1.5D);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", EDefaultLoopTypes.LOOP));
		return PlayState.CONTINUE;
	}

	public <E extends IAnimatable> PlayState attack(AnimationEvent<E> event) {
		if (this.swinging) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", EDefaultLoopTypes.LOOP));
			return PlayState.CONTINUE;
		}
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(
				new AnimationController<DarthJarJarEntity>(this, "idle_controller", 0, this::predicate));
		data.addAnimationController(
				new AnimationController<DarthJarJarEntity>(this, "idle_controller", 0, this::attack));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	@Override
	public int tickTimer() {
		return this.tickCount;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSoundEvents.DARTHNORMAL;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSoundEvents.DARTHHURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSoundEvents.DARTHDEATH;
	}

	@Override
	public boolean canStandOnFluid(FluidState state) {
		return state.is(FluidTags.LAVA);
	}

}
