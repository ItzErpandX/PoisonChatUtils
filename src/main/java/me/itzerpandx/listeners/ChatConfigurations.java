package me.itzerpandx.listeners;

import me.itzerpandx.ChatUtils;
import me.itzerpandx.utils.ColorUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class ChatConfigurations implements Listener {

    private ChatUtils plugin;

    public ChatConfigurations(ChatUtils plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onChatRude(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String plainMessage = event.getMessage();
        // Blacklist Message
        List<String> blockedMessages = plugin.getConfig().getStringList("blacklist");
        String blockedMessageTemplate = plugin.getConfig().getString("blacklistMessage");

        for (String blockedWord : blockedMessages) {
            if (plainMessage.toLowerCase().contains(blockedWord.toLowerCase())) {
                event.setCancelled(true);
                String blockedMessage = blockedMessageTemplate.replace("%word%", blockedWord).replace("%player%", player.getName()).replace("%message%", plainMessage);
                player.sendMessage(ColorUtil.color(blockedMessage));
                return;
            }
        }
    }
    @EventHandler
    public void onChatAdvertise(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String plainMessage = event.getMessage();
        // Blacklist Message
        List<String> blockedMessages = plugin.getConfig().getStringList("disabledAd");
        String blockedMessageTemplate = plugin.getConfig().getString("blacklistAdMessage");

        for (String blockedWord : blockedMessages) {
            if (plainMessage.toLowerCase().contains(blockedWord.toLowerCase())) {
                event.setCancelled(true);
                String blockedMessage = blockedMessageTemplate.replace("%word%", blockedWord).replace("%player%", player.getName()).replace("%message%", plainMessage);
                player.sendMessage(ColorUtil.color(blockedMessage));
                return;
            }
        }
    }
}