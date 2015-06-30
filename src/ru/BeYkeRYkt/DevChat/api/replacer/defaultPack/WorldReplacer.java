package ru.BeYkeRYkt.DevChat.api.replacer.defaultPack;

import ru.BeYkeRYkt.DevChat.api.channels.IUser;
import ru.BeYkeRYkt.DevChat.api.replacer.Replacer;

public class WorldReplacer implements Replacer {

    @Override
    public String getDetectWord() {
        return "%WORLD%";
    }

    @Override
    public String getReplaceWord(String message, IUser user) {
        return user.getPlayer().getWorld().getName();
    }

}