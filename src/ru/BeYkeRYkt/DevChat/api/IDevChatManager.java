package ru.BeYkeRYkt.DevChat.api;

import java.util.Collection;

import org.bukkit.entity.Player;

import ru.BeYkeRYkt.DevChat.api.group.IGroupChat;
import ru.BeYkeRYkt.DevChat.api.group.IUser;
import ru.BeYkeRYkt.DevChat.api.replacer.ReplacerManager;

public interface IDevChatManager {
    
    public Collection<IGroupChat> getGroupChats();
    
    public void registerGroup(IGroupChat chat);
    
    public void unregisterGroup(String id);
    
    public IGroupChat getGroupChat(String id);
    
    public IUser loadUser(Player player);
    
    public void saveUser(IUser user);
    
    public Collection<IUser> getUsers();
    
    public ReplacerManager getReplacerManager();
}