package com.shinobi.entity.jutsu;

import com.shinobi.registry.ModEntityTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

/** Water Style: a pressurised blast that knocks back and briefly slows. */
public class WaterJutsuEntity extends ThrowableProjectile implements ItemSupplier {

    public WaterJutsuEntity(EntityType<? extends WaterJutsuEntity> type, Level level) {
        super(type, level);
    }

    public WaterJutsuEntity(Level level, LivingEntity owner) {
        super(ModEntityTypes.WATER_JUTSU.get(), owner, level);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            if (result instanceof EntityHitResult entityHit && entityHit.getEntity() instanceof LivingEntity target) {
                target.hurt(this.damageSources().magic(), 4.0F);
                target.push(this.getDeltaMovement().x * 1.5, 0.3, this.getDeltaMovement().z * 1.5);
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1));
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
        return 0.01F;
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.WATER_BUCKET);
    }
}
