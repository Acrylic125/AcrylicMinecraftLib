package com.acrylic.universal.text;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public final class ChatUtils {

    public static String get(@NotNull String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String[] get(String... text) {
        int i = 0;
        for (String s : text) {
            text[i] = get(s);
            i++;
        }
        return text;
    }

    public static void send(@NotNull Collection<? extends Player> players, @NotNull String... text) {
        for (Player player : players)
            send(player, text);
    }

    public static void send(@NotNull Player[] players, @NotNull String... text) {
        for (Player player : players)
            send(player, text);
    }

    public static void send(@NotNull Player player, @NotNull String... text) {
        send((CommandSender) player, text);
    }

    public static void send(@NotNull CommandSender sender, @NotNull String... text) {
        for (String s : text)
            sender.sendMessage(ChatUtils.get(s));
    }

}
