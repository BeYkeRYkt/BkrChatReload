package ru.BeYkeRYkt.DevChat.api.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import ru.BeYkeRYkt.DevChat.api.channels.IUser;

public class DevChatReplaceWordEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private IUser user;
    private String message;

    public DevChatReplaceWordEvent(IUser user, String message) {
        this.user = user;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}