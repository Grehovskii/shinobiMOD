package com.shinobi.entity.jutsu;

import com.shinobi.registry.ModEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

/** Wind Style: a fast, flat, armor-piercing-ish cutting blade. Low gravity, high speed. */
public class WindBladeEntity extends ThrowableProjectile implements ItemSupplier {

    public WindBladeEntity(EntityType<? extends WindBladeEntity> type, Level level) {
        super(type, level);
    }

    public WindBladeEntity(Level level, LivingEntity owner) {
        super(ModEntityTypes.WIND_JUTSU.get(), owner, level);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            if (result instanceof EntityHitResult entityHit) {
                entityHit.getEntity().hurt(this.damageSources().generic(), 7.0F);
            }
            this.discard();
        }
    }

    @Override
    protected void defineSynchedData() {
        // no synced data needed
    }

    @Override
    protected float getGravity() {
        return 0.0F;
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.FEATHER);
    }
}
