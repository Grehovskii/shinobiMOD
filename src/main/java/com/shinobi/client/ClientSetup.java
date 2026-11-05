package com.shinobi.client;

import com.shinobi.client.render.KunaiRenderer;
import com.shinobi.client.render.ShurikenRenderer;
import com.shinobi.registry.ModEntityTypes;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static void init(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // no-op here; renderer registration happens via its own event below
        });
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.NINJA_VILLAGER.get(), VillagerRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.THROWN_KUNAI.get(), KunaiRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.THROWN_SHURIKEN.get(), ShurikenRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.FIREBALL_JUTSU.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.WATER_JUTSU.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.WIND_JUTSU.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.EARTH_SPIKE.get(), ThrownItemRenderer::new);
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.NINJA_VILLAGER.get(),
                com.shinobi.entity.villager.NinjaVillagerEntity.createAttributes().build());
    }

    public static void registerOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("chakra_bar", new ChakraHudOverlay());
    }
}
