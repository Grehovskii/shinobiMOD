package com.shinobi.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.shinobi.jutsu.Element;
import com.shinobi.network.ModMessages;
import com.shinobi.network.packet.CastJutsuPacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.event.TickEvent;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Hotkeys 1-5 cast the five elemental jutsu (Fire, Water, Earth, Wind,
 * Lightning), the way jutsu-casting mods like Naruto to Ninja World bind
 * each element to a dedicated key rather than a crafted item or GUI.
 * Rebindable in Options > Controls > Shinobi World.
 *
 * {@link #registerKeys} is called from the mod event bus and
 * {@link #onClientTick} from the Forge event bus; both are wired up
 * explicitly in {@code ShinobiMod}'s client-only setup instead of via
 * {@code @Mod.EventBusSubscriber}, since the two events live on different
 * buses.
 */
public class ModKeyBindings {

    public static final KeyMapping FIRE_JUTSU = new KeyMapping("key.shinobimod.fire_jutsu",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, InputConstants.KEY_1, "key.categories.shinobimod");
    public static final KeyMapping WATER_JUTSU = new KeyMapping("key.shinobimod.water_jutsu",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, InputConstants.KEY_2, "key.categories.shinobimod");
    public static final KeyMapping EARTH_JUTSU = new KeyMapping("key.shinobimod.earth_jutsu",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, InputConstants.KEY_3, "key.categories.shinobimod");
    public static final KeyMapping WIND_JUTSU = new KeyMapping("key.shinobimod.wind_jutsu",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, InputConstants.KEY_4, "key.categories.shinobimod");
    public static final KeyMapping LIGHTNING_JUTSU = new KeyMapping("key.shinobimod.lightning_jutsu",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, InputConstants.KEY_5, "key.categories.shinobimod");

    private static final Map<KeyMapping, Element> BINDINGS = new LinkedHashMap<>();
    static {
        BINDINGS.put(FIRE_JUTSU, Element.FIRE);
        BINDINGS.put(WATER_JUTSU, Element.WATER);
        BINDINGS.put(EARTH_JUTSU, Element.EARTH);
        BINDINGS.put(WIND_JUTSU, Element.WIND);
        BINDINGS.put(LIGHTNING_JUTSU, Element.LIGHTNING);
    }

    public static void registerKeys(RegisterKeyMappingsEvent event) {
        BINDINGS.keySet().forEach(event::register);
    }

    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (Minecraft.getInstance().player == null) return;

        for (Map.Entry<KeyMapping, Element> entry : BINDINGS.entrySet()) {
            if (entry.getKey().consumeClick()) {
                ModMessages.CHANNEL.sendToServer(new CastJutsuPacket(entry.getValue()));
            }
        }
    }
}
