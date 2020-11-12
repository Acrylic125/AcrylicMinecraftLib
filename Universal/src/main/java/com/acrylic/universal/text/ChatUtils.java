package com.acrylic.universal.text;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

public final class ChatUtils {

    public static String get(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
