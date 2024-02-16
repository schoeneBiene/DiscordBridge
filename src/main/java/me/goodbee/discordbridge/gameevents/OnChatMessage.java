package me.goodbee.discordbridge.gameevents;

import me.goodbee.discordbridge.DiscordBridge;
import net.dv8tion.jda.api.entities.Webhook;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import me.goodbee.discordbridge.util.DiscordWebhook;

import java.io.IOException;

public class OnChatMessage implements Listener {
    private final DiscordBridge plugin;

    public OnChatMessage(DiscordBridge plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent e) throws IOException {
        Bukkit.getLogger().info("asd");

        String message = e.getMessage();
        String playerName = e.getPlayer().getName();

        DiscordWebhook webhook = new DiscordWebhook(plugin.getBridgeWebhook().getUrl());

        webhook.setUsername(playerName);
        webhook.setAvatarUrl(String.format("https://crafthead.net/helm/%s", playerName));

        webhook.setContent(message);

        webhook.execute();
    }
}
