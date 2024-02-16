package me.goodbee.discordbridge.events;

import me.goodbee.discordbridge.DiscordBridge;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * JDA Listener handling slash commands
 */
public class OnSlashCommand extends ListenerAdapter {
    private DiscordBridge plugin;
    public OnSlashCommand(DiscordBridge plugin) {
        this.plugin = plugin;
    }
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        plugin.getCommandsManager().runCommand(event.getName(), event, plugin);
    }
}
