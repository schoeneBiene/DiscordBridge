package me.goodbee.discordbridge.util;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigMan {
    private static String bridgeChannel;

    public static void reload(FileConfiguration config) {
        bridgeChannel = config.getString("bridge-channel-id");
    }

    public static String getBridgeChannel() {
        return bridgeChannel;
    }
}
