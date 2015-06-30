package ru.BeYkeRYkt.DevChat.api.replacer.defaultPack;

import ru.BeYkeRYkt.DevChat.api.channels.IUser;
import ru.BeYkeRYkt.DevChat.api.replacer.Replacer;

public class PlayerNameReplacer implements Replacer {

    @Override
    public String getDetectWord() {
        return "%PLAYER_NAME%";
    }

    @Override
    public String getReplaceWord(String message, IUser user) {
        return user.getName();
    }

}