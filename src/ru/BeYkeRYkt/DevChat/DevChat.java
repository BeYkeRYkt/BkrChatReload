package ru.BeYkeRYkt.DevChat;

import org.bukkit.plugin.java.JavaPlugin;

import ru.BeYkeRYkt.DevChat.api.IDevChatManager;
import ru.BeYkeRYkt.DevChat.implementation.DevChatManager;

public class DevChat extends JavaPlugin {
    
    private static IDevChatManager manager;
    
    @Override
    public void onEnable(){
        manager = new DevChatManager(this);
    }
    
    public static IDevChatManager getChatManager() {
        return manager;
    }
    
}