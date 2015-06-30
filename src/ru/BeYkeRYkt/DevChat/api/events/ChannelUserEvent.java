package ru.BeYkeRYkt.DevChat.api.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import ru.BeYkeRYkt.DevChat.api.group.IGroupChat;
import ru.BeYkeRYkt.DevChat.api.group.IUser;

public class ChannelUserEvent extends Event implements Cancellable{

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private IUser user;
    private IGroupChat chat;
    private String message;
    
    
    public ChannelUserEvent(IUser user, IGroupChat chat, String message) {
        this.user = user;
        this.chat = chat;
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

    public IGroupChat getChat() {
        return chat;
    }

    public IUser getUser() {
        return user;
    }
    
    public String getMessage(){
        return message;
    }
    
    public void setMessage(String message){
        this.message = message;
    }
}