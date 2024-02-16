package me.goodbee.discordbridge.api.managers;

import me.goodbee.discordbridge.DiscordBridge;
import me.goodbee.discordbridge.api.objects.SlashCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.*;
import java.util.HashSet;

public class CommandsManager {
    private HashMap<String, SlashCommand> commands = new HashMap<String, SlashCommand>();

    public void update(JDA jda) {
        Collection<CommandData> jdacommands = new HashSet<CommandData>();

        for(String name : commands.keySet()) {
            jdacommands.add(commands.get(name).getCommand());
        }

        jda.updateCommands().addCommands(jdacommands).complete();
    }
    public void registerCommand(String name, SlashCommand command) {
        commands.put(name, command);
    }

    public void runCommand(String name, SlashCommandInteraction interaction, DiscordBridge plugin) {
        commands.get(name).onCommand(interaction, plugin);
    }
}
