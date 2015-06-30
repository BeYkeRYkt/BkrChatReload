package ru.BeYkeRYkt.DevChat.api.replacer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.Plugin;

import ru.BeYkeRYkt.DevChat.api.events.ChannelUserEvent;

public class ReplacerManager {
    
    private Map<String, Replacer> replacers;
    private Plugin plugin;
    
    public ReplacerManager(Plugin plugin) {
        this.plugin = plugin;
        this.replacers = new HashMap<String, Replacer>();
    }
    
    public Plugin getPlugin(){
        return plugin;
    }

    public Collection<Replacer> getReplacers(){
        return replacers.values();
    }
    
    public boolean registerReplacer(Replacer replacer){
        if(replacers.containsKey(replacer.getReplacerWords())){
            return false;
        }
        replacers.put(replacer.getReplacerWords(), replacer);
        return true;
    }
    
    public boolean unregisterReplacer(String replacerWords){
        if(replacers.containsKey(replacerWords)){
            return false;
        }
        replacers.remove(replacerWords);
        return true;
    }
    
    public Replacer getReplacer(String replacerWords){
        return replacers.get(replacerWords);
    }
    
    public String replace(ChannelUserEvent event){
        String[] list = event.getMessage().split("\\.");
        for(String word: list){
            if(hasReplacer(word)){
                Replacer replacer = getReplacer(word);
                replacer.replace(event);
            }
        }
        return event.getMessage();
    }

    public boolean hasReplacer(String word) {
        return getReplacer(word) != null;
    }
}