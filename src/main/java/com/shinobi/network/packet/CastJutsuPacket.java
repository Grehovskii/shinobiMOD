package com.shinobi.network.packet;

import com.shinobi.jutsu.Element;
import com.shinobi.jutsu.JutsuManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/** Client -> server: "I pressed the hotkey for this element, cast it." */
public class CastJutsuPacket {
    private final Element element;

    public CastJutsuPacket(Element element) {
        this.element = element;
    }

    public static void encode(CastJutsuPacket packet, FriendlyByteBuf buf) {
        buf.writeEnum(packet.element);
    }

    public static CastJutsuPacket decode(FriendlyByteBuf buf) {
        return new CastJutsuPacket(buf.readEnum(Element.class));
    }

    public static void handle(CastJutsuPacket packet, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            net.minecraft.server.level.ServerPlayer player = ctx.getSender();
            if (player != null) {
                JutsuManager.cast(player, packet.element);
            }
        });
        ctx.setPacketHandled(true);
    }
}
