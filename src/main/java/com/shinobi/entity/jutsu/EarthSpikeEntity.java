package com.shinobi.entity.jutsu;

import com.shinobi.registry.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

/**
 * Earth Style: a short-lived rock spike that erupts from the ground at a
 * fixed position and damages anything standing on top of it, then vanishes.
 * Does not place or break real blocks, so it's safe in any world.
 */
public class EarthSpikeEntity extends Entity implements ItemSupplier {

    private int life = 0;

    public EarthSpikeEntity(EntityType<? extends EarthSpikeEntity> type, Level level) {
        super(type, level);
    }

    public EarthSpikeEntity(Level level, BlockPos pos) {
        this(ModEntityTypes.EARTH_SPIKE.get(), level);
        this.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
    }

    @Override
    public void tick() {
        super.tick();
        life++;
        if (!this.level().isClientSide) {
            if (life == 5) {
                List<LivingEntity> hit = this.level().getEntitiesOfClass(LivingEntity.class,
                        new AABB(getX() - 0.6, getY(), getZ() - 0.6, getX() + 0.6, getY() + 2.0, getZ() + 0.6));
                for (LivingEntity target : hit) {
                    target.hurt(this.damageSources().generic(), 5.0F);
                    target.push(0, 0.4, 0);
                }
            }
            if (life > 30) {
                this.discard();
            }
        }
    }

    @Override
    protected void defineSynchedData() {
        // no synced data needed
    }

    @Override
    protected void readAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
        this.life = tag.getInt("life");
    }

    @Override
    protected void addAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
        tag.putInt("life", this.life);
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.COBBLESTONE);
    }
}
