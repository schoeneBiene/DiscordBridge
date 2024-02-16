package me.goodbee.discordbridge.events;

import me.goodbee.discordbridge.util.ConfigMan;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public class MessageCreate extends ListenerAdapter {


    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {


        if(event.getChannel().getId().equals(ConfigMan.getBridgeChannel()) && !event.isWebhookMessage() && !event.getAuthor().isBot()) {
            Bukkit.broadcastMessage(String.format(ChatColor.BLUE + "[Discord] %s: %s", event.getAuthor().getName(), event.getMessage()));
        }
    }
}
