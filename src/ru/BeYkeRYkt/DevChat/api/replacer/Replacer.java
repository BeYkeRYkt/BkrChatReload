package ru.BeYkeRYkt.DevChat.api.replacer;

import ru.BeYkeRYkt.DevChat.api.events.ChannelUserEvent;

public interface Replacer {
    
    public String getReplacerWords();
    
    public void replace(ChannelUserEvent channel);
    
}