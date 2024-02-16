package me.goodbee.discordbridge.api.objects;

import me.goodbee.discordbridge.DiscordBridge;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;

public interface SlashCommand {
    void onCommand(@NotNull SlashCommandInteraction interaction, DiscordBridge plugin);
    SlashCommandData getCommand();
}