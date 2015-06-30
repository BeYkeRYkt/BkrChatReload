package ru.BeYkeRYkt.DevChat.implementation.channels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ru.BeYkeRYkt.DevChat.api.channels.IChannel;
import ru.BeYkeRYkt.DevChat.api.channels.IUser;
import ru.BeYkeRYkt.DevChat.api.channels.IUser.MemberStatus;
import ru.BeYkeRYkt.DevChat.api.events.channel.DevChatChannelConnectEvent;
import ru.BeYkeRYkt.DevChat.api.events.channel.DevChatChannelDisconnectEvent;
import ru.BeYkeRYkt.DevChat.api.replacer.ReplacerManager;

public class Channel implements IChannel {

    private List<IUser> users;
    private ChannelStatus status;
    private UUID owner;
    private String id;
    private String name;
    private String password;
    private Collection<UUID> moderators;
    private int maxMembers;

    public Channel(UUID owner, String id, String name, String password, int maxMembers, ChannelStatus status) {
        this.owner = owner;
        this.id = id;
        this.name = name;
        this.password = password;
        this.status = status;
        this.maxMembers = maxMembers;
        this.users = new ArrayList<IUser>();
        this.moderators = new ArrayList<UUID>();
    }

    @Override
    public Collection<IUser> getMembers() {
        return users;
    }

    @Override
    public ChannelStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(ChannelStatus status) {
        this.status = status;
    }

    @Override
    public void sendMessage(Player owner, String message) {
        for (IUser user : getMembers()) {
            // if(user.getCurrectChannel().getId().equals(getId())){
            user.sendMessage(message);
            // }
        }
    }

    @Override
    public UUID getOwnerUUID() {
        return owner;
    }

    @Override
    public Collection<UUID> getModerators() {
        return moderators;
    }

    @Override
    public IUser getMember(String name) {
        for (IUser user : getMembers()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void broadcastMessage(String message) {
        for (IUser user : getMembers()) {
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

    private boolean isOwner(Player player) {
        if (player.getUniqueId().equals(getOwnerUUID())) {
            return true;
        }
        return false;
    }

    private boolean isModerator(Player player) {
        for (UUID uuid : getModerators()) {
            if (uuid.equals(player.getUniqueId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void connectUser(IUser member) {
        String text = "%PLAYER_NAME% зашел на наш чат!";
        text = ReplacerManager.replace(text, member);

        // Checker...
        if (isOwner(member.getPlayer())) {
            member.setStatus(MemberStatus.ADMIN);
        } else if (isModerator(member.getPlayer())) {
            member.setStatus(MemberStatus.MODERATOR);
        } else {
            member.setStatus(MemberStatus.MEMBER);
        }

        DevChatChannelConnectEvent event = new DevChatChannelConnectEvent(member, this, text);
        Bukkit.getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            if (!getMembers().contains(member)) {
                addMember(member);
            }
            member.getChannels().add(this);
            member.setCurrentChannel(this);

            broadcastMessage(event.getJoinMessage());
        } else {
            member.setStatus(MemberStatus.NONE);
        }
    }

    @Override
    public void disconnectUser(IUser member) {
        String text = "%PLAYER_NAME% вышел из нашего чат!";
        text = ReplacerManager.replace(text, member);

        DevChatChannelDisconnectEvent event = new DevChatChannelDisconnectEvent(member, this, text);
        Bukkit.getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            if (getMembers().contains(member)) {
                removeMember(member);
                member.setStatus(MemberStatus.NONE);
            }
            member.setCurrentChannel(null);
            broadcastMessage(event.getLeaveMessage());
        }
    }

    @Override
    public void clearChat() {
        // ???
        for (IUser user : getMembers()) {
            user.clearChat();
        }
    }

    @Override
    public void clearChat(IUser user) {
        clearChat();
        String text = "Чат очищен %PLAYER_NAME%";
        text = ReplacerManager.replace(text, user);
        broadcastMessage(text);
    }

    @Override
    public void clearChat(String reason) {
        clearChat();
        String text = "Чат был очищен по причине: %REASON%";
        text = text.replace("%REASON%", reason);
        text = ReplacerManager.replace(text, null);
        broadcastMessage(text);
    }

    @Override
    public void clearChat(IUser owner, String reason) {
        clearChat();
        String text = "Чат был очищен %PLAYER_NAME% по причине: %REASON%";
        text = text.replace("%REASON%", reason);
        text = ReplacerManager.replace(text, owner);
        broadcastMessage(text);
    }

}