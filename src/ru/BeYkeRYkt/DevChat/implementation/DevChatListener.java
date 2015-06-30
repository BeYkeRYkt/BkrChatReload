package ru.BeYkeRYkt.DevChat.implementation;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import ru.BeYkeRYkt.DevChat.DevChat;
import ru.BeYkeRYkt.DevChat.api.channels.IChannel;
import ru.BeYkeRYkt.DevChat.api.channels.IUser;
import ru.BeYkeRYkt.DevChat.api.replacer.ReplacerManager;

public class DevChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!event.isCancelled()) {
            event.setCancelled(true);
            IUser user = DevChat.getChatManager().getUser(event.getPlayer());

            if (event.getMessage().contains("clear")) {
                user.getCurrectChannel().clearChat(user, "Testing");
            }

            String text = "[%TIME%] %PLAYER_NAME%: %MESSAGE%";
            // Automatic
            text = text.replace("%MESSAGE%", event.getMessage());

            text = ReplacerManager.replace(text, user);
            user.getCurrectChannel().sendMessage(event.getPlayer(), text);
        }
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLater(DevChat.getInstance(), new Runnable() {

            @Override
            public void run() {
                IUser user = DevChat.getChatManager().loadUser(event.getPlayer());
                IChannel chat = DevChat.getChatManager().getChannel("lobby");
                chat.connectUser(user);
            }

        }, 2);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        IUser user = DevChat.getChatManager().getUser(player);
        user.getCurrectChannel().disconnectUser(user);
        DevChat.getChatManager().saveUser(user);
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        // Player player = event.getPlayer();
        // IUser user = DevChat.getChatManager().getUser(player);
        // user.getCurrectChannel().disconnectUser(user);
        // DevChat.getChatManager().saveUser(user);
    }
}