package ru.BeYkeRYkt.DevChat.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import ru.BeYkeRYkt.DevChat.api.IDevChatManager;
import ru.BeYkeRYkt.DevChat.api.group.IGroupChat;
import ru.BeYkeRYkt.DevChat.api.group.IUser;
import ru.BeYkeRYkt.DevChat.api.replacer.ReplacerManager;

public class DevChatManager implements IDevChatManager{

    private List<IGroupChat> chats;
    private ReplacerManager replacerManager;

    public DevChatManager(Plugin plugin) {
        this.chats = new ArrayList<IGroupChat>();
        this.replacerManager = new ReplacerManager(plugin);
    }
    
    @Override
    public Collection<IGroupChat> getGroupChats() {
        return chats;
    }

    @Override
    public void registerGroup(IGroupChat chat) {
        if(getGroupChat(chat.getId()) == null){
            chats.add(chat);
        }
    }

    @Override
    public void unregisterGroup(String id) {
        if(getGroupChat(id) == null){
            chats.remove(id);
        }
    }

    @Override
    public IGroupChat getGroupChat(String id) {
        for(IGroupChat chat: getGroupChats()){
            if(chat.getId().equals(id)){
                return chat;
            }
        }
        return null;
    }

    @Override
    public IUser loadUser(Player player) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void saveUser(IUser user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Collection<IUser> getUsers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReplacerManager getReplacerManager() {
        return replacerManager;
    }    
}