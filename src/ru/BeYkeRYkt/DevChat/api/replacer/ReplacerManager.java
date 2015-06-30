package ru.BeYkeRYkt.DevChat.api.replacer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import ru.BeYkeRYkt.DevChat.api.channels.IUser;
import ru.BeYkeRYkt.DevChat.api.events.DevChatReplaceWordEvent;

public class ReplacerManager {

    private static Map<String, Replacer> replacers = new HashMap<String, Replacer>();

    public static Collection<Replacer> getReplacers() {
        return replacers.values();
    }

    public static boolean registerReplacer(Replacer replacer) {
        if (replacers.containsKey(replacer.getDetectWord())) {
            return false;
        }
        replacers.put(replacer.getDetectWord(), replacer);
        return true;
    }

    public static boolean unregisterReplacer(String replacerWords) {
        if (replacers.containsKey(replacerWords)) {
            return false;
        }
        replacers.remove(replacerWords);
        return true;
    }

    public static Replacer getReplacer(String replacerWords) {
        return replacers.get(replacerWords);
    }

    public static String replace(String message, IUser user) {
        // String[] list = message.split(" ");
        // for(String word: list){
        // if(hasReplacer(word)){
        // Replacer replacer = getReplacer(word);
        // message = message.replace(replacer.getDetectWord(),
        // replacer.getReplaceWord(message, user));
        // }
        // }
        DevChatReplaceWordEvent event = new DevChatReplaceWordEvent(user, message);
        Bukkit.getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            for (String word : replacers.keySet()) {
                Replacer replacer = getReplacer(word);
                // message =
                // event.getMessage().replace(replacer.getDetectWord(),
                // replacer.getReplaceWord(event.getMessage(), user));
                event.setMessage(event.getMessage().replace(replacer.getDetectWord(), replacer.getReplaceWord(event.getMessage(), user)));
            }
            // message = ChatColor.translateAlternateColorCodes('&',
            // event.getMessage());
            event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
        }
        return event.getMessage();
    }

    public static boolean hasReplacer(String word) {
        return getReplacer(word) != null;
    }
}