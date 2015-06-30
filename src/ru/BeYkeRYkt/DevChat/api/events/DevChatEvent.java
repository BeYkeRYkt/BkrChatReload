package ru.BeYkeRYkt.DevChat.api.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import ru.BeYkeRYkt.DevChat.api.channels.IUser;

public class DevChatEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private IUser user;

    public DevChatEvent(IUser user) {
        this.user = user;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean paramBoolean) {
        this.cancelled = paramBoolean;
    }

    public IUser getUser() {
        return user;
    }
}