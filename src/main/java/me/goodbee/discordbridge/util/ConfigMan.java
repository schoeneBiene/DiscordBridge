package me.goodbee.discordbridge.util;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Simple class used to access the config
 */
public class ConfigMan {
    private static String bridgeChannel;

    /**
     * Reload the config
     * @param config The FileConfiguration used for this
     */
    public static void reload(FileConfiguration config) {
        bridgeChannel = config.getString("bridge-channel-id");
    }

    public static String getBridgeChannel() {
        return bridgeChannel;
    }
}
