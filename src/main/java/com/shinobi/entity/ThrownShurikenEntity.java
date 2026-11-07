package com.shinobi.entity;

import com.shinobi.registry.ModEntityTypes;
import com.shinobi.registry.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ThrownShurikenEntity extends AbstractArrow {

    public ThrownShurikenEntity(EntityType<? extends ThrownShurikenEntity> type, Level level) {
        super(type, level);
    }

    public ThrownShurikenEntity(Level level, LivingEntity owner) {
        super(ModEntityTypes.THROWN_SHURIKEN.get(), owner, level);
        this.setBaseDamage(3.0D);
        this.setNoGravity(true);
    }

    public void setItem(ItemStack stack) {
        // kept simple: always returns as a shuriken
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.SHURIKEN.get());
    }
}
