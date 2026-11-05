package com.shinobi.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/** Server -> client: keeps the client-side chakra HUD in sync. */
public class SyncChakraPacket {
    private final int chakra;
    private final int maxChakra;

    public SyncChakraPacket(int chakra, int maxChakra) {
        this.chakra = chakra;
        this.maxChakra = maxChakra;
    }

    public static void encode(SyncChakraPacket packet, FriendlyByteBuf buf) {
        buf.writeInt(packet.chakra);
        buf.writeInt(packet.maxChakra);
    }

    public static SyncChakraPacket decode(FriendlyByteBuf buf) {
        return new SyncChakraPacket(buf.readInt(), buf.readInt());
    }

    public static void handle(SyncChakraPacket packet, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> com.shinobi.client.ClientChakraData.update(packet.chakra, packet.maxChakra));
        ctx.setPacketHandled(true);
    }
}
