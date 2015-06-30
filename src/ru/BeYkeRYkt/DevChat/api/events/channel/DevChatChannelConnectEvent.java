package ru.BeYkeRYkt.DevChat.api.events.channel;

import ru.BeYkeRYkt.DevChat.api.channels.IChannel;
import ru.BeYkeRYkt.DevChat.api.channels.IUser;

public class DevChatChannelConnectEvent extends DevChatChannelEvent {

    private String joinMessage;

    public DevChatChannelConnectEvent(IUser user, IChannel chat, String message) {
        super(user, chat);
        this.joinMessage = message;
    }

    public String getJoinMessage() {
        return joinMessage;
    }

    public void setJoinMessage(String joinMessage) {
        this.joinMessage = joinMessage;
    }

}