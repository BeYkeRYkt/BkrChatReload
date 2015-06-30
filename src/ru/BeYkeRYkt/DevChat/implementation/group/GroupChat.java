package ru.BeYkeRYkt.DevChat.implementation.group;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import ru.BeYkeRYkt.DevChat.api.group.IGroupChat;
import ru.BeYkeRYkt.DevChat.api.group.IUser;

public class GroupChat implements IGroupChat{

    private List<IUser> users;
    private GroupStatus status;
    private UUID owner;
    private String id;
    private String name;
    private String password;
    private Collection<IUser> moderators;
    private int maxMembers;
    
    public GroupChat(UUID owner, String id, String name, String password, int maxMembers, GroupStatus status) {
       this.owner = owner;
       this.id = id;
       this.name = name;
       this.password = password;
       this.status = status;
       this.maxMembers = maxMembers;
       this.users = new ArrayList<IUser>();
    }
    
    @Override
    public Collection<IUser> getMembers() {
        return users;
    }

    @Override
    public GroupStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(GroupStatus status) {
        this.status = status;
    }

    @Override
    public void sendMessage(Player owner, String message) {
        for(IUser user: getMembers()){
            if(user.getCurrectGroupChat().getId().equals(getId())){
                user.sendMessage(message);
            }
        }
    }

    @Override
    public UUID getOwnerUUID() {
        return owner;
    }

    @Override
    public Collection<IUser> getModerators() {
        return moderators;
    }

    @Override
    public IUser getMember(String name) {
        for(IUser user: getMembers()){
            if(user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void broadcastMessage(String message) {
        for(IUser user: getMembers()){
            user.sendMessage(message);
        }
    }

    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void addMember(IUser member) {
        getMembers().add(member);
    }

    @Override
    public void removeMember(IUser member) {
        getMembers().remove(member);
    }

    @Override
    public int getMaxMembers() {
        return maxMembers;
    }

    @Override
    public void setMaxMembers(int max) {
        this.maxMembers = max;
    }

    @Override
    public void connectUser(IUser member) {
        String text = "%PLAYER_NAME% зашел на наш чат!";
        if(!getMembers().contains(member)){
            getMembers().add(member);
        }
        broadcastMessage(text);
    }

    @Override
    public void disconnectUser(IUser member) {
        String text = "%PLAYER_NAME% вышел из нашего чат!";
        if(getMembers().contains(member)){
            getMembers().remove(member);
        }
        broadcastMessage(text);
    }

    @Override
    public void clearChat() {
        //???
        for(IUser user: getMembers()){
            user.clearChat();
        }
    }

    @Override
    public void clearChat(IUser user) {
        clearChat();
        String text = "Чат очищен %PLAYER_NAME%";
        broadcastMessage(text);
    }

    @Override
    public void clearChat(String reason) {
        clearChat();
        String text = "Чат очищен был очищен по причине: %REASON%";
        broadcastMessage(text);
    }

    @Override
    public void clearChat(IUser owner, String reason) {
        clearChat();
        String text = "Чат очищен был очищен %PLAYER_NAME% по причине: %REASON%";
        broadcastMessage(text);
    }
    
}