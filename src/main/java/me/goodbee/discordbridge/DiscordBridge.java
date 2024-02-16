package me.goodbee.discordbridge;

import me.goodbee.discordbridge.api.managers.CommandsManager;
import me.goodbee.discordbridge.events.MessageCreate;
import me.goodbee.discordbridge.events.OnSlashCommand;
import me.goodbee.discordbridge.gameevents.OnChatMessage;
import me.goodbee.discordbridge.slashcommands.PingCommand;
import me.goodbee.discordbridge.util.ConfigMan;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.nio.Buffer;

/**
 * Main plugin class
 */
public final class DiscordBridge extends JavaPlugin {
    private String TOKEN;
    private JDA jda;
    private CommandsManager commandsManager;
    private String bridgeChannelId;
    public int tps;
    private Webhook bridgeWebhook;
    @Override
    public void onEnable() {
        saveDefaultConfig();

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
        {
            long sec;
            long currentSec;
            int  ticks;
            int  delay;

            @Override
            public void run()
            {
                sec = (System.currentTimeMillis() / 1000);

                if(currentSec == sec)
                {
                    ticks++;
                }
                else
                {
                    currentSec = sec;
                    tps = (tps == 0 ? ticks : ((tps + ticks) / 2));
                    ticks = 0;
                }
            }
        }, 0, 1);

        this.commandsManager = new CommandsManager();

        this.bridgeChannelId = getConfig().getString("bridge-channel-id");

        getLogger().info("Logging into Discord.");

        TOKEN = getConfig().getString("token");

        if(TOKEN.equals("TOKEN HERE")) {
            getLogger().severe("No token provided. Please configure the plugin!");
            getServer().getPluginManager().disablePlugin(this);
        }

        ConfigMan.reload(getConfig());

        this.jda = JDABuilder
                .createDefault(TOKEN)
                .setActivity(Activity.playing("Minecraft"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                .build();

        jda.addEventListener(new MessageCreate());
        jda.addEventListener(new OnSlashCommand(this));

        commandsManager.registerCommand("ping", new PingCommand());
        Bukkit.getLogger().info(bridgeChannelId);

        DiscordBridge thisplugin = this;

        new BukkitRunnable() {
            @Override
            public void run() {
                bridgeWebhook = jda.getTextChannelById(bridgeChannelId.trim()).createWebhook("Minecraft chat relay").complete();
                getServer().getPluginManager().registerEvents(new OnChatMessage(thisplugin), thisplugin);
            }
        }.runTaskLater(this, 60);

        commandsManager.update(jda);


    }

    @Override
    public void onDisable() {
        bridgeWebhook.delete().complete();
    }

    /**
     * Get the JDA instance
     * @return The JDA instance
     */
    public JDA getJDA() {
        return jda;
    }

    /**
     * Get the used command manager
     * @return The [CommandsManager]
     */
    public CommandsManager getCommandsManager() {
        return this.commandsManager;
    }

    /**
     * Get the Bridge Webhook
     * @return The Bridge Webhook
     */
    public Webhook getBridgeWebhook() {
        return this.bridgeWebhook;
    }
}
