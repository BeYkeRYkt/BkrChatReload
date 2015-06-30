package ru.BeYkeRYkt.DevChat.api.events.channel;

import ru.BeYkeRYkt.DevChat.api.channels.IChannel;
import ru.BeYkeRYkt.DevChat.api.channels.IUser;

public class DevChatChannelDisconnectEvent extends DevChatChannelEvent {

    private String leaveMessage;

    public DevChatChannelDisconnectEvent(IUser user, IChannel chat, String message) {
        super(user, chat);
        this.setLeaveMessage(message);
    }

    public String getLeaveMessage() {
        return leaveMessage;
    }

    public void setLeaveMessage(String leaveMessage) {
        this.leaveMessage = leaveMessage;
    }
}