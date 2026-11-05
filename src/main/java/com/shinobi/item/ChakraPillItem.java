package com.shinobi.item;

import com.shinobi.chakra.Chakra;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/** Restores a chunk of the player's chakra when eaten, like the soldier pills in the series. */
public class ChakraPillItem extends Item {
    public ChakraPillItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);
        if (!level.isClientSide && entity instanceof Player player) {
            player.getCapability(com.shinobi.chakra.ChakraProvider.CHAKRA_CAP).ifPresent(c -> c.add(40));
        }
        return result;
    }
}
