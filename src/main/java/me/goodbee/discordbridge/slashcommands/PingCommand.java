package me.goodbee.discordbridge.slashcommands;

import me.goodbee.discordbridge.DiscordBridge;
import me.goodbee.discordbridge.api.objects.SlashCommand;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;

/**
 * Simple command which responds with the TPS.
 */
public class PingCommand implements SlashCommand {
    @Override
    public void onCommand(@NotNull SlashCommandInteraction interaction, DiscordBridge plugin) {
        interaction.reply(String.format("Pong! TPS: %d", plugin.tps)).complete();
    }

    @Override
    public SlashCommandData getCommand() {
        return Commands.slash("ping", "Pong! Returns TPS of the server.");
    }
}
