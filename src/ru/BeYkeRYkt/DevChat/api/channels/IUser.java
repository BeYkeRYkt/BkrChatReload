package ru.BeYkeRYkt.DevChat.api.channels;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

public interface IUser {

    public enum MemberStatus {
        MEMBER, MODERATOR, ADMIN, NONE;
    }

    public Player getPlayer();

    public String getName();

    public UUID getUUID();

    public MemberStatus getStatus();

    public void setStatus(MemberStatus status);

    public Collection<IChannel> getChannels();

    public IChannel getChannel(String id);

    public void clearChat();

    public void sendMessage(String message);

    public IChannel getCurrectChannel();

    public void setCurrentChannel(IChannel channel);

    // public void playNotificationSound();

    // public boolean isNotificationSound();

    // public void setNotificationSound(boolean flag);

    // public Sound getNotificationSound();

    // public void setNotificationSound(Sound sound);

    public Map<String, Object> getSettings();

    public Object getSettingValue(String key);

    public void setSettingValue(String key, Object value);
}