package com.jacobpmods.entity.custom;

import com.jacobpmods.entity.attackgoals.SkeletalZombieAttackGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class SkeletalZombieEntity extends Monster {
    public final AnimationState idleAnimationSate = new AnimationState();
    private int idleAnimationTimeout = 0;

    //private final BreakDoorGoal breakDoorGoal;
    private boolean canBreakDoors;
    private static final float BREAK_DOOR_CHANCE = 0.1F;
   // private static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE;
    public SkeletalZombieEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
       // this.breakDoorGoal = breakDoorGoal;
        // this.breakDoorGoal = new BreakDoorGoal(this, DOOR_BREAKING_PREDICATE);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new ZombieAttackTurtleEggGoal(this, 1.0, 3));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 25)
                .add(Attributes.MOVEMENT_SPEED, 0.23)
                .add(Attributes.FOLLOW_RANGE,35)
                .add(Attributes.ATTACK_DAMAGE, 4.5);
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SkeletalZombieAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0, true, 4, this::canBreakDoors));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        //this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[]{ZombifiedPiglin.class}));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));

    }
    public boolean canBreakDoors() {
        return this.canBreakDoors;
    }
    protected boolean supportsBreakDoorGoal() {
        return true;
    }

   /* public void setCanBreakDoors(boolean canBreakDoors) {
        if (this.supportsBreakDoorGoal() && GoalUtils.hasGroundPathNavigation(this)) {
            if (this.canBreakDoors != canBreakDoors) {
                this.canBreakDoors = canBreakDoors;
                ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(canBreakDoors);
                if (canBreakDoors) {
                    this.goalSelector.addGoal(1, this.breakDoorGoal);
                } else {
                    this.goalSelector.removeGoal(this.breakDoorGoal);
                }
            }
        } else if (this.canBreakDoors) {
            this.goalSelector.removeGoal(this.breakDoorGoal);
            this.canBreakDoors = false;
        }

    }*/


    static class ZombieAttackTurtleEggGoal extends RemoveBlockGoal {
        ZombieAttackTurtleEggGoal(PathfinderMob mob, double speedModifier, int verticalSearchRange) {
            super(Blocks.TURTLE_EGG, mob, speedModifier, verticalSearchRange);
        }

        public void playDestroyProgressSound(LevelAccessor level, BlockPos pos) {
           // level.playSound((Player)null, pos, SoundEvents.ZOMBIE_DESTROY_EGG, SoundSource.HOSTILE, 0.5F, 0.9F + Zombie.this.random.nextFloat() * 0.2F);
        }

        public void playBreakSound(Level level, BlockPos pos) {
            level.playSound((Player)null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + level.random.nextFloat() * 0.2F);
        }

        public double acceptedDistance() {
            return 1.14;
        }
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationSate.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide) {
            this.setupAnimationStates();
        }
    }
}
