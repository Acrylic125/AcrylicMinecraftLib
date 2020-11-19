package com.acrylic.universal;

import com.acrylic.universal.text.AbstractMessageBuilder;
import com.acrylic.universal.text.MessageBuilder;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Universal {

    private static JavaPlugin plugin;
    private static AbstractMessageBuilder messageBuilder = new MessageBuilder();

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

}
