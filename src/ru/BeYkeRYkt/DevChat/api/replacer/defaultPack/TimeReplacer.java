package ru.BeYkeRYkt.DevChat.api.replacer.defaultPack;

import java.text.SimpleDateFormat;
import java.util.Date;

import ru.BeYkeRYkt.DevChat.api.channels.IUser;
import ru.BeYkeRYkt.DevChat.api.replacer.Replacer;

public class TimeReplacer implements Replacer {

    @Override
    public String getDetectWord() {
        return "%TIME%";
    }

    @Override
    public String getReplaceWord(String message, IUser user) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(date);
        return time;
    }

}