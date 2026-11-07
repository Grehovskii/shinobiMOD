package com.shinobi.entity;

import com.shinobi.registry.ModEntityTypes;
import com.shinobi.registry.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ThrownKunaiEntity extends AbstractArrow {

    public ThrownKunaiEntity(EntityType<? extends ThrownKunaiEntity> type, Level level) {
        super(type, level);
    }

    public ThrownKunaiEntity(Level level, LivingEntity owner) {
        super(ModEntityTypes.THROWN_KUNAI.get(), owner, level);
        this.setBaseDamage(4.5D);
    }

    public void setItem(ItemStack stack) {
        // visual/pickup item kept as kunai regardless of what triggered the throw
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.KUNAI.get());
    }
}
