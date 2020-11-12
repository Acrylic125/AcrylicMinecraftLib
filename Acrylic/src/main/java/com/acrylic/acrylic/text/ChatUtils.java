package com.acrylic.acrylic.text;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public final class ChatUtils {

    public String get(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
