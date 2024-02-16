package me.goodbee.discordbridge.api.objects;

import me.goodbee.discordbridge.DiscordBridge;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;

/**
 * Interface representing slash commands.
 */
public interface SlashCommand {
    /**
     * Ran when the slash command is executed.
     * @param interaction The SlashCommandInteraction that ran.
     * @param plugin The [DiscordBridge] plugin
     */
    void onCommand(@NotNull SlashCommandInteraction interaction, DiscordBridge plugin);

    /**
     * Returns the [SlashCommandData]
     * @return
     */
    SlashCommandData getCommand();
}