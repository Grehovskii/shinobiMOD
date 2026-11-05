package com.shinobi.registry;

import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;

/**
 * Adds ninja-gear trades to the vanilla Weaponsmith profession, so any
 * {@code NinjaVillagerEntity} (which spawns already set to that profession)
 * sells kunai, shuriken, the ninjato and village armor for emeralds. Piggy-
 * backing on an existing vanilla profession avoids registering a brand new
 * profession and point-of-interest type just for this.
 */
public class ModVillagers {

    public static void registerTrades(VillagerTradesEvent event) {
        if (event.getType() != VillagerProfession.WEAPONSMITH) return;
        var trades = event.getTrades();

        trades.get(1).add((trader, random) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 2), new ItemStack(ModItems.KUNAI.get(), 4), 12, 2, 0.05F));
        trades.get(1).add((trader, random) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 2), new ItemStack(ModItems.SHURIKEN.get(), 4), 12, 2, 0.05F));

        trades.get(2).add((trader, random) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 10), new ItemStack(ModItems.NINJATO.get()), 6, 5, 0.05F));
        trades.get(2).add((trader, random) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 6), new ItemStack(ModItems.CHAKRA_PILL.get(), 3), 8, 5, 0.05F));

        trades.get(3).add((trader, random) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 18), new ItemStack(ModItems.KONOHA_CHEST.get()), 4, 10, 0.1F));
        trades.get(3).add((trader, random) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 16), new ItemStack(ModItems.SUNA_CHEST.get()), 4, 10, 0.1F));

        trades.get(4).add((trader, random) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 24), new ItemStack(ModItems.IWA_HELMET.get()), 3, 15, 0.1F));
        trades.get(4).add((trader, random) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 22), new ItemStack(ModItems.KUMO_HELMET.get()), 3, 15, 0.1F));
    }
}
