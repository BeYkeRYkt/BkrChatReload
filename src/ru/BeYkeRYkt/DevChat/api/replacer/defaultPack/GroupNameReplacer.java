package ru.BeYkeRYkt.DevChat.api.replacer.defaultPack;

import ru.BeYkeRYkt.DevChat.DevChat;
import ru.BeYkeRYkt.DevChat.api.channels.IUser;
import ru.BeYkeRYkt.DevChat.api.replacer.Replacer;

public class GroupNameReplacer implements Replacer {

    @Override
    public String getDetectWord() {
        return "%GROUP_NAME%";
    }

    @Override
    public String getReplaceWord(String message, IUser user) {
        return DevChat.getInstance().getVaultChat().getPrimaryGroup(user.getPlayer());
    }

}