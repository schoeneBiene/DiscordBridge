package me.goodbee.discordbridge.api.managers;

import me.goodbee.discordbridge.DiscordBridge;
import me.goodbee.discordbridge.api.objects.SlashCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.*;
import java.util.HashSet;

/**
 * Class representing the manager of application (/) commands
 */
public class CommandsManager {
    private HashMap<String, SlashCommand> commands = new HashMap<String, SlashCommand>();

    /**
     * Updates the slash commands with the Discord API.
     * @param jda The JDA instance.
     */
    public void update(JDA jda) {
        Collection<CommandData> jdacommands = new HashSet<CommandData>();

        for(String name : commands.keySet()) {
            jdacommands.add(commands.get(name).getCommand());
        }

        jda.updateCommands().addCommands(jdacommands).complete();
    }

    /**
     * Registers a command on the CommandManager
     * @param name The name of the slash command
     * @param command The [SlashCommand] to register
     */
    public void registerCommand(String name, SlashCommand command) {
        commands.put(name, command);
    }

    /**
     * Runs a slash command from an interaction
     * @param name The name of the slash command, as registered with [CommandsManager.registerCommand]
     * @param interaction The interaction
     * @param plugin The [DiscordBridge] plugin
     */
    public void runCommand(String name, SlashCommandInteraction interaction, DiscordBridge plugin) {
        commands.get(name).onCommand(interaction, plugin);
    }
}
