package com.shinobi.item;

import com.shinobi.entity.ThrownShurikenEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

/** A spinning throwing star: fast, flat trajectory, light damage. */
public class ShurikenItem extends Item {
    public ShurikenItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<net.minecraft.world.item.ItemStack> use(Level level, Player player, InteractionHand hand) {
        net.minecraft.world.item.ItemStack stack = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TRIDENT_THROW,
                SoundSource.PLAYERS, 0.5F, 1.6F);
        if (!level.isClientSide) {
            ThrownShurikenEntity shuriken = new ThrownShurikenEntity(level, player);
            shuriken.setItem(stack);
            shuriken.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.8F, 0.2F);
            level.addFreshEntity(shuriken);
        }
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        player.awardStat(net.minecraft.stats.Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
