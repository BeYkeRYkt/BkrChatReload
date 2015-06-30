package ru.BeYkeRYkt.DevChat;

import java.util.UUID;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import ru.BeYkeRYkt.DevChat.api.IDevChatManager;
import ru.BeYkeRYkt.DevChat.api.channels.IChannel.ChannelStatus;
import ru.BeYkeRYkt.DevChat.api.replacer.ReplacerManager;
import ru.BeYkeRYkt.DevChat.api.replacer.defaultPack.GroupNameReplacer;
import ru.BeYkeRYkt.DevChat.api.replacer.defaultPack.PlayerDisplayNameReplacer;
import ru.BeYkeRYkt.DevChat.api.replacer.defaultPack.PlayerNameReplacer;
import ru.BeYkeRYkt.DevChat.api.replacer.defaultPack.PlayerPrefixReplacer;
import ru.BeYkeRYkt.DevChat.api.replacer.defaultPack.TimeReplacer;
import ru.BeYkeRYkt.DevChat.api.replacer.defaultPack.WorldReplacer;
import ru.BeYkeRYkt.DevChat.implementation.DevChatListener;
import ru.BeYkeRYkt.DevChat.implementation.DevChatManager;
import ru.BeYkeRYkt.DevChat.implementation.channels.Channel;

public class DevChat extends JavaPlugin {

    private static IDevChatManager manager;
    private static DevChat plugin;
    public static Chat chat = null;
    public static Permission perms = null;

    @Override
    public void onEnable() {
        plugin = this;
        manager = new DevChatManager(this);
        getChatManager().registerChannel(new Channel(UUID.randomUUID(), "lobby", "Lobby", null, getServer().getMaxPlayers(), ChannelStatus.OPEN));

        setupChat();
        setupPermissions();

        // register defalult replacers
        ReplacerManager.registerReplacer(new PlayerDisplayNameReplacer());
        ReplacerManager.registerReplacer(new PlayerNameReplacer());
        ReplacerManager.registerReplacer(new PlayerPrefixReplacer());
        ReplacerManager.registerReplacer(new GroupNameReplacer());
        ReplacerManager.registerReplacer(new TimeReplacer());
        ReplacerManager.registerReplacer(new WorldReplacer());

        getServer().getPluginManager().registerEvents(new DevChatListener(), this);
    }

    public static IDevChatManager getChatManager() {
        return manager;
    }

    public static DevChat getInstance() {
        return plugin;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            perms = permissionProvider.getProvider();
        }
        return (perms != null);
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }
        return (chat != null);
    }

    public Chat getVaultChat() {
        return chat;
    }

    public Permission getVaultPermissions() {
        return perms;
    }
}