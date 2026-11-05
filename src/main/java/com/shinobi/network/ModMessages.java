package com.shinobi.network;

import com.shinobi.ShinobiMod;
import com.shinobi.network.packet.CastJutsuPacket;
import com.shinobi.network.packet.SyncChakraPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {

    private static final String PROTOCOL = "1";
    public static SimpleChannel CHANNEL;

    public static void register() {
        CHANNEL = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(ShinobiMod.MOD_ID, "main"),
                () -> PROTOCOL,
                PROTOCOL::equals,
                PROTOCOL::equals
        );

        int id = 0;
        CHANNEL.registerMessage(id++, CastJutsuPacket.class, CastJutsuPacket::encode, CastJutsuPacket::decode, CastJutsuPacket::handle);
        CHANNEL.registerMessage(id++, SyncChakraPacket.class, SyncChakraPacket::encode, SyncChakraPacket::decode, SyncChakraPacket::handle);
    }
}
