package com.acrylic.universal;

import com.acrylic.universal.text.AbstractMessageBuilder;
import com.acrylic.universal.text.MessageBuilder;
import com.acrylic.universal.versionstore.AbstractVersionStore;
import com.acrylic.universal.versionstore.VersionStore;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Universal {

    private static JavaPlugin plugin;
    private static AbstractMessageBuilder messageBuilder = new MessageBuilder();
    private static AbstractVersionStore versionStore = new VersionStore();

    public static void setPlugin(@NotNull JavaPlugin plugin) {
        Universal.plugin = plugin;
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static void setMessageBuilder(@NotNull AbstractMessageBuilder messageBuilder) {
        Universal.messageBuilder = messageBuilder;
    }

    public static AbstractMessageBuilder getMessageBuilder() {
        return messageBuilder;
    }

    @SuppressWarnings("unchecked")
    public static <T extends AbstractMessageBuilder> T getMessageBuilder(Class<T> type) {
        return (T) messageBuilder;
    }

    public static void setVersionStore(@NotNull AbstractVersionStore versionStore) {
        Universal.versionStore = versionStore;
    }

    public static AbstractVersionStore getVersionStore() {
        return versionStore;
    }

    @SuppressWarnings("unchecked")
    public static <T extends AbstractVersionStore> T getVersionStore(Class<T> type) {
        return (T) versionStore;
    }



}
