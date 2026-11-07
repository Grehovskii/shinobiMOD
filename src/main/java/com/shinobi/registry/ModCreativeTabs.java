package com.shinobi.registry;

import com.shinobi.ShinobiMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ShinobiMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SHINOBI_TAB = TABS.register("shinobi_world",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.NINJATO.get()))
                    .title(Component.translatable("itemGroup.shinobimod.shinobi_world"))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.KUNAI.get());
                        output.accept(ModItems.SHURIKEN.get());
                        output.accept(ModItems.NINJATO.get());
                        output.accept(ModItems.CHAKRA_PILL.get());
                        output.accept(ModItems.HEADBAND.get());

                        output.accept(ModItems.KONOHA_HELMET.get());
                        output.accept(ModItems.KONOHA_CHEST.get());
                        output.accept(ModItems.KONOHA_LEGS.get());
                        output.accept(ModItems.KONOHA_BOOTS.get());

                        output.accept(ModItems.SUNA_HELMET.get());
                        output.accept(ModItems.SUNA_CHEST.get());
                        output.accept(ModItems.SUNA_LEGS.get());
                        output.accept(ModItems.SUNA_BOOTS.get());

                        output.accept(ModItems.KIRI_HELMET.get());
                        output.accept(ModItems.KIRI_CHEST.get());
                        output.accept(ModItems.KIRI_LEGS.get());
                        output.accept(ModItems.KIRI_BOOTS.get());

                        output.accept(ModItems.IWA_HELMET.get());
                        output.accept(ModItems.IWA_CHEST.get());
                        output.accept(ModItems.IWA_LEGS.get());
                        output.accept(ModItems.IWA_BOOTS.get());

                        output.accept(ModItems.KUMO_HELMET.get());
                        output.accept(ModItems.KUMO_CHEST.get());
                        output.accept(ModItems.KUMO_LEGS.get());
                        output.accept(ModItems.KUMO_BOOTS.get());

                        output.accept(ModBlocks.KONOHA_BRICKS.get());
                        output.accept(ModBlocks.SUNA_SANDSTONE.get());
                        output.accept(ModBlocks.KIRI_STONE.get());
                        output.accept(ModBlocks.IWA_ROCK.get());
                        output.accept(ModBlocks.KUMO_CLOUDSTONE.get());

                        output.accept(ModItems.NINJA_VILLAGER_SPAWN_EGG.get());
                    })
                    .build());
}
