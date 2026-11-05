package com.shinobi.chakra;

import com.shinobi.ShinobiMod;
import com.shinobi.network.ModMessages;
import com.shinobi.network.packet.SyncChakraPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;

/**
 * Attaches the chakra capability to every player, regenerates it slowly
 * over time, and keeps it across death/respawn and dimension change.
 */
public class ChakraEvents {

    public static final ResourceLocation CHAKRA_ID = new ResourceLocation(ShinobiMod.MOD_ID, "chakra");

    private int tickCounter = 0;

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<net.minecraft.world.entity.Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(CHAKRA_ID, new ChakraProvider());
        }
    }

    @SubscribeEvent
    public void onClone(PlayerEvent.Clone event) {
        event.getOriginal().getCapability(ChakraProvider.CHAKRA_CAP).ifPresent(oldStore ->
                event.getEntity().getCapability(ChakraProvider.CHAKRA_CAP).ifPresent(newStore ->
                        newStore.copyFrom(oldStore)));
    }

    @SubscribeEvent
    public void onPlayerTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof net.minecraft.server.level.ServerPlayer player)) return;
        tickCounter++;
        if (tickCounter % 20 != 0) return; // once a second

        player.getCapability(ChakraProvider.CHAKRA_CAP).ifPresent(chakra -> {
            if (chakra.getChakra() < chakra.getMaxChakra()) {
                chakra.add(1);
                ModMessages.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player),
                        new SyncChakraPacket(chakra.getChakra(), chakra.getMaxChakra()));
            }
        });
    }
}
