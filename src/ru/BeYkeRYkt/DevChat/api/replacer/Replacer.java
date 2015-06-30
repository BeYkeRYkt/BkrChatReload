package ru.BeYkeRYkt.DevChat.api.replacer;

import ru.BeYkeRYkt.DevChat.api.channels.IUser;

public interface Replacer {

    public String getDetectWord();

    public String getReplaceWord(String message, IUser user);

}