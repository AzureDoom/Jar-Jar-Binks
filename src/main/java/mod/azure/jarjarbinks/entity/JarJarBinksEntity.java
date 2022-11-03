package mod.azure.jarjarbinks.entity;

import mod.azure.jarjarbinks.registry.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.Vec3;
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

public class JarJarBinksEntity extends PathfinderMob implements IAnimatable, IAnimationTickable {

	public AnimationFactory factory = GeckoLibUtil.createFactory(this);
	protected final GroundPathNavigation landNavigation = new GroundPathNavigation(this, level);
	protected final AmphibiousNavigation swimNavigation = new AmphibiousNavigation(this, level);
	protected final MoveControl landMoveControl = new MoveControl(this);
	protected final LookControl landLookControl = new LookControl(this);
	protected final SmoothSwimmingMoveControl swimMoveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.5f, 1.0f,
			false);
	protected final SmoothSwimmingLookControl swimLookControl = new SmoothSwimmingLookControl(this, 10);

	public JarJarBinksEntity(EntityType<? extends JarJarBinksEntity> entityType, Level worldIn) {
		super(entityType, worldIn);
		navigation = landNavigation;
		moveControl = landMoveControl;
		lookControl = landLookControl;
	}

	@Override
	public void travel(Vec3 movementInput) {
		this.navigation = (this.isInWater() || this.isInWater()) ? swimNavigation : landNavigation;
		this.moveControl = (this.wasEyeInWater || this.isInWater()) ? swimMoveControl : landMoveControl;
		this.lookControl = (this.wasEyeInWater || this.isInWater()) ? swimLookControl : landLookControl;

		if (this.tickCount % 10 == 0) {
			this.refreshDimensions();
		}
		if (isEffectiveAi() && this.isInWater()) {
			moveRelative(getSpeed(), movementInput);
			move(MoverType.SELF, getDeltaMovement());
			setDeltaMovement(getDeltaMovement().scale(0.35));
			if (getTarget() == null) {
				setDeltaMovement(getDeltaMovement().add(0.0, -0.005, 0.0));
			}
		} else {
			super.travel(movementInput);
		}
	}

	@Override
	public PathNavigation createNavigation(Level world) {
		return swimNavigation;
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	protected void jumpInLiquid(TagKey<Fluid> fluid) {
	}

	public static AttributeSupplier.Builder createMobAttributes() {
		return Mob.createLivingAttributes().add(Attributes.FOLLOW_RANGE, 50.0D).add(Attributes.MAX_HEALTH, 15.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.1D).add(Attributes.KNOCKBACK_RESISTANCE, 0.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 1.0D);
	}

	public static boolean canSpawn(EntityType<? extends JarJarBinksEntity> type, LevelAccessor world,
			MobSpawnType reason, BlockPos pos, RandomSource random) {
		return world.getDifficulty() != Difficulty.PEACEFUL;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.3D, false));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0D, 10));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Cod.class, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Pufferfish.class, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Salmon.class, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, TropicalFish.class, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Squid.class, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Turtle.class, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Dolphin.class, true));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	@Override
	public int tickTimer() {
		return this.tickCount;
	}

	public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (this.wasEyeInWater) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("idle_water", EDefaultLoopTypes.LOOP));
			return PlayState.CONTINUE;
		}
		event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", EDefaultLoopTypes.LOOP));
		return PlayState.CONTINUE;
	}

	public <E extends IAnimatable> PlayState attack(AnimationEvent<E> event) {
		if (this.swinging && this.isAggressive()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", EDefaultLoopTypes.LOOP));
			return PlayState.CONTINUE;
		}
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(
				new AnimationController<JarJarBinksEntity>(this, "idle_controller", 0, this::predicate));
		data.addAnimationController(
				new AnimationController<JarJarBinksEntity>(this, "attack_controller", 0, this::attack));
	}

	@Override
	public float getEyeHeight(Pose pose) {
		return 1.74F;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSoundEvents.JARNORMAL;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSoundEvents.JARHURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSoundEvents.JARDEATH;
	}

	protected SoundEvent getStepSound() {
		return SoundEvents.ZOMBIE_STEP;
	}

	protected boolean shouldDrown() {
		return false;
	}

	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public EntityDimensions getDimensions(Pose pose) {
		return this.wasEyeInWater ? EntityDimensions.scalable(1.5f, 0.8f) : super.getDimensions(pose);
	}
	
	@Override
	public void refreshDimensions() {
		super.refreshDimensions();
	}

}