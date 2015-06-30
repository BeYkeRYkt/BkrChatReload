package ru.BeYkeRYkt.DevChat.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import ru.BeYkeRYkt.DevChat.api.IDevChatManager;
import ru.BeYkeRYkt.DevChat.api.channels.IChannel;
import ru.BeYkeRYkt.DevChat.api.channels.IUser;
import ru.BeYkeRYkt.DevChat.implementation.channels.User;

public class DevChatManager implements IDevChatManager {

    private List<IChannel> chats;
    private List<IUser> users;

    public DevChatManager(Plugin plugin) {
        this.chats = new ArrayList<IChannel>();
        this.users = new ArrayList<IUser>();
    }

    @Override
    public Collection<IChannel> getChannels() {
        return chats;
    }

    @Override
    public void registerChannel(IChannel chat) {
        if (getChannel(chat.getId()) == null) {
            chats.add(chat);
        }
    }

    @Override
    public void unregisterChannel(String id) {
        if (getChannel(id) == null) {
            chats.remove(id);
        }
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
    public IUser loadUser(Player player) {
        IUser user = getUser(player);
        if (user == null) {
            user = new User(player);
            users.add(user);
        }
        return user;
    }

    @Override
    public void saveUser(IUser user) {
    }

    @Override
    public Collection<IUser> getUsers() {
        return users;
    }

    @Override
    public IUser getUser(UUID uuid) {
        for (IUser user : users) {
            if (user.getUUID().equals(uuid)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public IUser getUser(Player player) {
        return getUser(player.getUniqueId());
    }
}