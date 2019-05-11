package com.example.examplemod.registries;

import com.example.examplemod.network.MessageExample;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * The SimpleNetworkWrapper container class for the mod. Register all network messages here.
 */
public class ModNetworkMessages {
    public static void registerMessages() {
        // Register messages here with the specified receiving side
        INSTANCE.registerMessage(MessageExample.Handler.class, MessageExample.class, nextID(), Side.SERVER);
    }

    private static int packetId = 0;

    public static SimpleNetworkWrapper INSTANCE = null;

    public ModNetworkMessages() {
    }

    public static int nextID() {
        return packetId++;
    }

    public static void init(String channelName) {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
        registerMessages();
    }
}
