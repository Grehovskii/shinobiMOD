package com.shinobi.entity.jutsu;

import com.shinobi.registry.ModEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

/** The classic Fire Style fireball: explodes without destroying terrain on hit. */
public class FireballJutsuEntity extends ThrowableProjectile implements ItemSupplier {

    public FireballJutsuEntity(EntityType<? extends FireballJutsuEntity> type, Level level) {
        super(type, level);
    }

    public FireballJutsuEntity(Level level, LivingEntity owner) {
        super(ModEntityTypes.FIREBALL_JUTSU.get(), owner, level);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            if (result instanceof EntityHitResult entityHit) {
                entityHit.getEntity().hurt(this.damageSources().fireball(this, this.getOwner() instanceof LivingEntity le ? le : null), 6.0F);
                entityHit.getEntity().setSecondsOnFire(5);
            }
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 1.0F, false, Level.ExplosionInteraction.NONE);
            this.discard();
        }
    }

    @Override
    protected float getGravity() {
        return 0.03F;
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.FIRE_CHARGE);
    }
}
