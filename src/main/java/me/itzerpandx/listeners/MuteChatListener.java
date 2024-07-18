package me.itzerpandx.listeners;

import me.itzerpandx.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class MuteChatListener implements Listener {

    private ChatUtils plugin;

    public MuteChatListener(ChatUtils plugin) {
        this.plugin = plugin;
    }

    public void muteChat() {
        boolean isMuted = plugin.getConfig().getBoolean("muteChat.isChatNotMuted", false);
        if (isMuted) {
            for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                onlinePlayers.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                            ");
                onlinePlayers.sendMessage(ChatColor.GREEN + "The chat is now unmuted");
                onlinePlayers.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "                                            ");
            }
            plugin.getConfig().set("muteChat.isChatNotMuted", false);
            plugin.saveConfig();
        } else {
            for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                onlinePlayers.sendMessage(ChatColor.RED + "" + ChatColor.STRIKETHROUGH + "                                            ");
                onlinePlayers.sendMessage(ChatColor.RED + "The chat is now muted");
                onlinePlayers.sendMessage(ChatColor.RED + "" + ChatColor.STRIKETHROUGH + "                                            ");
            }
            plugin.getConfig().set("muteChat.isChatNotMuted", true);
            plugin.saveConfig();
        }
    }

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        boolean isMuted = plugin.getConfig().getBoolean("muteChat.isChatNotMuted", false);
        if (isMuted && !player.hasPermission("chatutils.bypass")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "The chat is currently muted");
        }
    }
}