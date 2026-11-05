package com.shinobi.entity;

import com.shinobi.registry.ModEntityTypes;
import com.shinobi.registry.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
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

    public void setItem(net.minecraft.world.item.ItemStack stack) {
        // kept simple: always returns as a shuriken
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.SHURIKEN.get();
    }

    @Override
    protected void tickDespawn() {
        // shuriken fly flat and far; let AbstractArrow's default despawn timer handle it
        super.tickDespawn();
    }
}
