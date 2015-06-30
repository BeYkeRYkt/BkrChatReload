package ru.BeYkeRYkt.DevChat.api.events.channel;

import ru.BeYkeRYkt.DevChat.api.channels.IChannel;
import ru.BeYkeRYkt.DevChat.api.channels.IUser;
import ru.BeYkeRYkt.DevChat.api.events.DevChatEvent;

public class DevChatChannelEvent extends DevChatEvent {

    private IChannel channel;

    public DevChatChannelEvent(IUser user, IChannel channel) {
        super(user);
        this.channel = channel;
    }

    public IChannel getChannel() {
        return channel;
    }

}