package com.acrylic.universal.text;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

/**
 * This class is intended to be extended upon if you have
 * other constants.
 *
 * The methods are intentionally synchronized to aid in async use.
 */
@Setter @Getter
public abstract class AbstractMessageBuilder {

    private static final StringBuilder builder = new StringBuilder();

    private String ERROR_COLOR = "&e";
    private String ERROR_HIGHLIGHT = ERROR_COLOR + "&n";
    private String ERROR_PREFIX = "&c&l[!] &r" + ERROR_COLOR;
    private String PRIMARY_COLOUR = "&3";
    private String SECONDARY_COLOUR = "&b";
    private String TERTIARY_COLOUR = "&f";
    private String HIGHLIGHT = TERTIARY_COLOUR + "&n";

    public synchronized AbstractMessageBuilder create() {
        builder.setLength(0);
        return this;
    }

    public synchronized AbstractMessageBuilder append(String str) {
        builder.append(str);
        return this;
    }

    public synchronized AbstractMessageBuilder append(String prefix, String str) {
        return append(prefix + str);
    }

    public synchronized AbstractMessageBuilder nextLine() {
        return append("\n");
    }

    public synchronized AbstractMessageBuilder space() {
        return append(" ");
    }

    public synchronized AbstractMessageBuilder clearCodes() {
        return append("&r");
    }

    public synchronized void sendErrorMessage(String errorMessage, Player... players) {
        create()
                .append(ERROR_PREFIX)
                .append(ERROR_COLOR, errorMessage)
                .send(players);
    }

    public synchronized String toStringUncolored() {
        return builder.toString();
    }

    public synchronized void send(Player... players) {
        String str = toString();
        for (Player player : players)
            player.sendMessage(str);
    }

    @Override
    public synchronized String toString() {
        return ChatUtils.get(toStringUncolored());
    }

}
