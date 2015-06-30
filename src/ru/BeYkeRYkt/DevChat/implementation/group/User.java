package ru.BeYkeRYkt.DevChat.implementation.group;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ru.BeYkeRYkt.DevChat.api.group.IGroupChat;
import ru.BeYkeRYkt.DevChat.api.group.IUser;

public class User implements IUser {

    private MemberStatus status;
    private List<IGroupChat> chats;
    private IGroupChat current;
    private boolean notification;
    private Player player;
    
    
    public User(Player player) {
        this.status = MemberStatus.MEMBER;
        this.chats = new ArrayList<IGroupChat>();
    }
    
    @Override
    public String getName() {
        return player.getName();//TODO
    }

    @Override
    public Player getPlayer(){
        return player;
    }
    
    @Override
    public UUID getUUID() {
        return player.getUniqueId();
    }

    @Override
    public MemberStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(MemberStatus status) {
        this.status = status;
    }

    @Override
    public Collection<IGroupChat> getGroups() {
        return chats;
    }

    @Override
    public IGroupChat getGroupChat(String id) {
        for(IGroupChat chat: getGroups()){
            if(chat.getId().equals(id)){
                return chat;
            }
        }
        return null;
    }

    @Override
    public void clearChat() {
        for(int x = 0; x < 120; x++){
            getPlayer().sendMessage(" ");
        }
        String text = "Чат очищен.";
        sendMessage(text);
    }

    @Override
    public boolean isNotificationSound() {
        return notification;
    }

    @Override
    public void setNotificationSound(boolean flag) {
        this.notification = flag;
    }

    @Override
    public void sendMessage(String message) {
        Player player = Bukkit.getPlayer(getUUID());
        player.sendRawMessage(message);
    }

    @Override
    public IGroupChat getCurrectGroupChat() {
        return current;
    }
    
}