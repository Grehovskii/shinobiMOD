package com.shinobi.client;

/** Client-side cache of the local player's chakra, kept in sync via SyncChakraPacket. */
public class ClientChakraData {
    private static int chakra = 0;
    private static int maxChakra = 100;

    public static void update(int chakra, int maxChakra) {
        ClientChakraData.chakra = chakra;
        ClientChakraData.maxChakra = maxChakra;
    }

    public static int getChakra() {
        return chakra;
    }

    public static int getMaxChakra() {
        return maxChakra;
    }
}
