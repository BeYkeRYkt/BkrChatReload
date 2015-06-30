package ru.BeYkeRYkt.DevChat.implementation.channels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ru.BeYkeRYkt.DevChat.api.channels.IChannel;
import ru.BeYkeRYkt.DevChat.api.channels.IUser;

public class User implements IUser {

    private MemberStatus status;
    private List<IChannel> chats;
    private IChannel current;
    private Player player;
    private Map<String, Object> settings;

    public User(Player player) {
        this.player = player;
        this.status = MemberStatus.NONE;
        this.chats = new ArrayList<IChannel>();
        this.settings = new HashMap<String, Object>();
    }

    @Override
    public String getName() {
        return player.getName();// TODO
    }

    @Override
    public Player getPlayer() {
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
    public Collection<IChannel> getChannels() {
        return chats;
    }

    @Override
    public IChannel getChannel(String id) {
        for (IChannel chat : getChannels()) {
            if (chat.getId().equals(id)) {
                return chat;
            }
        }
        return null;
    }

    @Override
    public void clearChat() {
        for (int x = 0; x < 120; x++) {
            getPlayer().sendMessage(" ");
        }
        // String text = "Чат очищен.";
        // sendMessage(text);
    }

    @Override
    public void sendMessage(String message) {
        Player player = Bukkit.getPlayer(getUUID());
        player.sendRawMessage(message);
    }

    @Override
    public IChannel getCurrectChannel() {
        return current;
    }

    @Override
    public void setCurrentChannel(IChannel channel) {
        this.current = channel;
        // ??
        String text = null;
        if (current != null) {
            text = "Вы подключились к " + channel.getDisplayName();
        } else {
            text = "Вас отключили от чата.";
        }
        sendMessage(text);
    }

    @Override
    public Map<String, Object> getSettings() {
        return settings;
    }

    @Override
    public Object getSettingValue(String key) {
        return settings.get(key);
    }

    @Override
    public void setSettingValue(String key, Object value) {
        settings.put(key, value);
    }
}