package me.goodbee.discordbridge;

import me.goodbee.discordbridge.api.objects.SlashCommand;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements SlashCommand {
    @Override
    public void onCommand(@NotNull User user, @NotNull SlashCommandInteraction interaction) {

    }

    @Override
    public SlashCommandData getCommand() {
        return Commands.slash("ping", "Ping the bot!");
    }
}
