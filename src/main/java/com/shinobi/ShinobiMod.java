package com.shinobi;

import com.shinobi.chakra.ChakraEvents;
import com.shinobi.client.ClientSetup;
import com.shinobi.client.ModKeyBindings;
import com.shinobi.network.ModMessages;
import com.shinobi.registry.*;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.DistExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Shinobi World — a Naruto-universe mod for Forge 1.20.1.
 *
 * Adds: a chakra resource bound to the player, five elemental jutsu cast with
 * hotkeys (1-5), ninja weapons (kunai, shuriken, ninjato), five village armor
 * sets, a chakra HUD, and ninja villager NPCs with jutsu-themed trades.
 */
@Mod(ShinobiMod.MOD_ID)
public class ShinobiMod {

    public static final String MOD_ID = "shinobimod";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public ShinobiMod() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(modBus);
        ModBlocks.BLOCKS.register(modBus);
        ModBlocks.BLOCK_ITEMS.register(modBus);
        ModEntityTypes.ENTITY_TYPES.register(modBus);
        ModCreativeTabs.TABS.register(modBus);

        modBus.addListener(this::commonSetup);
        modBus.addListener(ModVillagers::registerTrades);

        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(new ChakraEvents());

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            modBus.addListener(ClientSetup::init);
            modBus.addListener(ClientSetup::registerRenderers);
            modBus.addListener(ClientSetup::registerAttributes);
            modBus.addListener(ClientSetup::registerOverlays);
            modBus.addListener(ModKeyBindings::registerKeys);
            net.minecraftforge.common.MinecraftForge.EVENT_BUS.addListener(ModKeyBindings::onClientTick);
        });

        LOGGER.info("Shinobi World mod loading...");
    }

    private void commonSetup(net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent event) {
        event.enqueueWork(ModMessages::register);
    }
}
