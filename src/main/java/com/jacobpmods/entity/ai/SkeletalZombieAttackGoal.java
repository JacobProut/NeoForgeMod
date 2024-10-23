package com.jacobpmods.entity.ai;

import com.jacobpmods.entity.custom.SkeletalZombieEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class SkeletalZombieAttackGoal extends MeleeAttackGoal {
    private final SkeletalZombieEntity entity;
    //private int raiseArmTicks;
    private int attackDelay = 10;
    private int ticksUntilNextAttack = 10;
    private int ticksUntilNextPathRecalculation;

    private boolean shouldCountTillNextAttack = false;


    public SkeletalZombieAttackGoal(PathfinderMob skeletalZombie, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(skeletalZombie, speedModifier, followingTargetEvenIfNotSeen);
        entity = ((SkeletalZombieEntity) skeletalZombie);
    }

    public void start() {
        super.start();
        //this.raiseArmTicks = 0;
        ticksUntilNextAttack = 10;
        entity.attackAnimationTimeout = 0;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity target) {
        if (this.canPerformAttack(target)) {
            // Set the attacking flag and initialize animation timeout
            this.entity.setAttacking(true);
            this.entity.attackAnimationTimeout = attackDelay; // Set animation timeout to the delay

            // Perform the attack
            this.resetAttackCooldown();
            this.mob.swing(InteractionHand.MAIN_HAND);
            this.mob.doHurtTarget(target);
        } else {
            // Reset attack status if out of range or can't attack
            this.entity.setAttacking(false);
            this.entity.attackAnimationTimeout = 0; // Reset the animation timeout
        }
    }

    @Override
    public void stop() {
        super.stop();
        this.entity.setAttacking(false); // Ensure to stop attacking animation
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity target = this.mob.getTarget();

        // Handle attack animation timeout countdown
        if (this.entity.attackAnimationTimeout > 0) {
            this.entity.attackAnimationTimeout--;
        }

        if (target != null) {
            this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);

            // Reduce path recalculation delay
            this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);

            // Count down to the next attack
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);

            // Check and perform attack
            this.checkAndPerformAttack(target);
        }
    }

    @Override
    protected boolean canPerformAttack(LivingEntity entity) {
        // Ensure entity is within melee attack range and visible
        return this.isTimeToAttack() && this.mob.isWithinMeleeAttackRange(entity) && this.mob.getSensing().hasLineOfSight(entity);
    }

    @Override
    protected void resetAttackCooldown() {
        // Reset attack cooldown to default 20 ticks or your custom delay
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay);
    }
       /* ++this.raiseArmTicks;
        this.entity.setAggressive(this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2);
*/
}
