package ru.BeYkeRYkt.DevChat.api.replacer.defaultPack;

import ru.BeYkeRYkt.DevChat.DevChat;
import ru.BeYkeRYkt.DevChat.api.channels.IUser;
import ru.BeYkeRYkt.DevChat.api.replacer.Replacer;

public class PlayerPrefixReplacer implements Replacer {

    @Override
    public String getDetectWord() {
        return "%PLAYER_PREFIX%";
    }

    @Override
    public String getReplaceWord(String message, IUser user) {
        return DevChat.getInstance().getVaultChat().getPrimaryGroup(user.getPlayer());
    }

}