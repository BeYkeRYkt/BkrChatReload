package ru.BeYkeRYkt.DevChat.api.group;

import java.util.Collection;
import java.util.UUID;

import org.bukkit.entity.Player;

public interface IUser {
    
    public enum MemberStatus{
        MEMBER, MODERATOR, ADMIN, NONE;
    }
    
    public Player getPlayer();
    
    public String getName();
    
    public UUID getUUID();
    
    public MemberStatus getStatus();
    
    public void setStatus(MemberStatus status);
    
    public Collection<IGroupChat> getGroups();
    
    public IGroupChat getGroupChat(String id);    

    public void clearChat();
    
    public boolean isNotificationSound();
    
    public void setNotificationSound(boolean flag);
    
    public void sendMessage(String message);
    
    public IGroupChat getCurrectGroupChat();
}