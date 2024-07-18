package me.itzerpandx;

import me.itzerpandx.commands.*;
import me.itzerpandx.listeners.*;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.security.Permission;

public final class ChatUtils extends JavaPlugin {
    private Permission permission;

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Configs
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            getLogger().info("Config file not found, creating...");
            saveResource("config.yml", false);
        }

        ClearChatCommand clearChatCommand = new ClearChatCommand(this);
        PluginCommand clearChatCmd = getCommand("clearchat");
        if (clearChatCmd != null) clearChatCmd.setExecutor(clearChatCommand);

        MuteChatCommand muteChatCommand = new MuteChatCommand(this);
        PluginCommand muteChatCmd = getCommand("mutechat");
        if (muteChatCmd != null) muteChatCmd.setExecutor(muteChatCommand);

        MainCommands mainCommand = new MainCommands(this);
        PluginCommand mainCmd = getCommand("chatutils");
        if (mainCmd != null) mainCmd.setExecutor(mainCommand);

        getServer().getPluginManager().registerEvents(new ClearChatListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatConfigurations(this), this);
        getServer().getPluginManager().registerEvents(new MuteChatListener(this), this);
        getServer().getPluginManager().registerEvents(new EmojiListener(this), this);
        getServer().getPluginManager().registerEvents(new DeveloperVerification(this), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
