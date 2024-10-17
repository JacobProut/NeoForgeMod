package com.jacobpmods.entity.attackgoals;

import com.jacobpmods.entity.custom.SkeletalZombieEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Zombie;

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
}