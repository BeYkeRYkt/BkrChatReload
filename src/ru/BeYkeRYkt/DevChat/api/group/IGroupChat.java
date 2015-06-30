package ru.BeYkeRYkt.DevChat.api.group;

import java.util.Collection;
import java.util.UUID;

import org.bukkit.entity.Player;

public interface IGroupChat {
    
    public enum GroupStatus{
        OPEN, CLOSE;
    }
    
    public Collection<IUser> getMembers();
    
    public GroupStatus getStatus();
    
    public void setStatus(GroupStatus status);
    
    public void sendMessage(Player owner, String message);
    
    public UUID getOwnerUUID();
    
    public Collection<IUser> getModerators();
    
    public IUser getMember(String name);
    
    public void broadcastMessage(String message);
    
    public String getDisplayName();
    
    public String getId();
    
    public String getPassword();
    
    public void setPassword(String password);
    
    public void addMember(IUser member);
    
    public void removeMember(IUser member);
    
    public void connectUser(IUser member);
    
    public void disconnectUser(IUser member);
        
    public void clearChat();
    
    public void clearChat(String reason);
    
    public void clearChat(IUser owner);
    
    public void clearChat(IUser owner, String reason);
    
    public int getMaxMembers();
    
    public void setMaxMembers(int max);
}