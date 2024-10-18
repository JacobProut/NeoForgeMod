package com.jacobpmods.entity.ai;

import com.jacobpmods.entity.custom.SkeletalZombieEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class SkeletalZombieAttackGoal extends MeleeAttackGoal {
    private final SkeletalZombieEntity entity;
    private int attackDelay = 6; //delay before animation attack
    private int ticksUntilNextAttack = 40;
    private boolean shouldCountTillNextAttack = false;

    public SkeletalZombieAttackGoal(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(mob, speedModifier, followingTargetEvenIfNotSeen);
        entity = ((SkeletalZombieEntity) mob);
    }


    @Override
    public void start() {
        super.start();
        attackDelay = 40;
        ticksUntilNextAttack = 40;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        if (isEnemyWithinAttackDistance(pEnemy, pDistToEnemySqr)) {
            shouldCountTillNextAttack = true;

            if(isTimeToStartAttackAnimation()) {
                entity.setAttacking(true);
            }

            if(isTimeToAttack()) {
                this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
                performAttack(pEnemy);
            }
        } else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            entity.setAttacking(false);
            entity.attackAnimationTimeout = 0;
        }
    }

    // Calculate squared attack reach for melee attacks
    protected double getAttackReachSqr(LivingEntity target) {
        // Get the attack range (usually based on the entity's dimensions or a custom value)
        double attackReach = this.mob.getBbWidth() * 2.0F + target.getBbWidth();

        // Return the squared value (squared distance is compared to avoid using costly sqrt calculations)
        return attackReach * attackReach;
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy, double pDistToEnemySqr) {
        return pDistToEnemySqr <= this.getAttackReachSqr(pEnemy);
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilNextAttack <= attackDelay;
    }

    protected int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }


    protected void performAttack(LivingEntity pEnemy) {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(pEnemy);
    }

    @Override
    public void tick() {
        super.tick();

        // Make sure to count down the ticks until the next attack
        if (shouldCountTillNextAttack) {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }

        // Check if an attack can be performed
        LivingEntity target = this.mob.getTarget();
        if (target != null) {
            double distanceToTargetSqr = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());

            if (isEnemyWithinAttackDistance(target, distanceToTargetSqr)) {
                this.shouldCountTillNextAttack = true;

                if (isTimeToStartAttackAnimation()) {
                    this.entity.setAttacking(true); // Start attack animation
                }

                if (isTimeToAttack()) {
                    this.mob.getLookControl().setLookAt(target.getX(), target.getEyeY(), target.getZ());
                    performAttack(target);
                }
            } else {
                resetAttackCooldown();
                this.shouldCountTillNextAttack = false;
                this.entity.setAttacking(false); // Stop attack animation
            }
        }
    }

    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }














}




/*
public class SkeletalZombieAttackGoal extends MeleeAttackGoal {
    private final SkeletalZombieEntity skeletalZombie;
    private int raiseArmTicks;

    public SkeletalZombieAttackGoal(SkeletalZombieEntity skeletalZombie, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(skeletalZombie, speedModifier, followingTargetEvenIfNotSeen);
        this.skeletalZombie = skeletalZombie;
    }

    public void start() {
        super.start();
        this.raiseArmTicks = 0;
    }

    public void stop() {
        super.stop();
        this.skeletalZombie.setAggressive(false);
    }

    public void tick() {
        super.tick();
        ++this.raiseArmTicks;
        if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
            this.skeletalZombie.setAggressive(true);
        } else {
            this.skeletalZombie.setAggressive(false);
        }

    }
}*/
