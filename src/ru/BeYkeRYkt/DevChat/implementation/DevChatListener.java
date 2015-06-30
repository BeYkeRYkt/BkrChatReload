package ru.BeYkeRYkt.DevChat.implementation;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ru.BeYkeRYkt.DevChat.DevChat;
import ru.BeYkeRYkt.DevChat.api.group.IGroupChat;
import ru.BeYkeRYkt.DevChat.api.group.IUser;

public class DevChatListener implements Listener {
    
    private DevChat plugin;
    
    public DevChatListener(DevChat chat) {
        this.plugin = chat;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        IUser user = plugin.getChatManager().loadUser(event.getPlayer());
        IGroupChat chat = plugin.getChatManager().getGroupChat("lobby");
        chat.connectUser(user);
    }
    
}