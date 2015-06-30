package ru.BeYkeRYkt.DevChat.api;

import java.util.Collection;
import java.util.UUID;

import org.bukkit.entity.Player;

import ru.BeYkeRYkt.DevChat.api.channels.IChannel;
import ru.BeYkeRYkt.DevChat.api.channels.IUser;

public interface IDevChatManager {

    public Collection<IChannel> getChannels();

    public void registerChannel(IChannel chat);

    public void unregisterChannel(String id);

    public IChannel getChannel(String id);

    public IUser loadUser(Player player);

    public void saveUser(IUser user);

    public Collection<IUser> getUsers();

    public IUser getUser(UUID uuid);

    public IUser getUser(Player player);
}