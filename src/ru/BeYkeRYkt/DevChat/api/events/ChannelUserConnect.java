package ru.BeYkeRYkt.DevChat.api.events;

import ru.BeYkeRYkt.DevChat.api.group.IGroupChat;
import ru.BeYkeRYkt.DevChat.api.group.IUser;

public class ChannelUserConnect extends ChannelUserEvent {

    public ChannelUserConnect(IUser user, IGroupChat chat, String message) {
        super(user, chat, message);
    }
    
}