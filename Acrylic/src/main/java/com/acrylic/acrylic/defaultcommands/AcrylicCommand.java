package com.acrylic.acrylic.defaultcommands;

import com.acrylic.acrylic.command.AbstractCommandBuilder;
import com.acrylic.acrylic.command.CommandBuilder;
import com.acrylic.acrylic.text.ChatUtils;
import lombok.experimental.UtilityClass;
import org.bukkit.command.CommandSender;

@UtilityClass
public class AcrylicCommand {

    public void registerMainCommand() {
        
        new CommandBuilder("acrylic")
                .setAliases("acryliccmd")
                .arguments(new AbstractCommandBuilder[] {
                        getTestCommandBuilder()
                })
                .handle(commandExecutor -> {
                    CommandSender sender = commandExecutor.getSender();
                    sender.sendMessage(ChatUtils.get(
                            "&3&l[ Acrylic ]\n" +
                                    "&b/acrylic test &7Test command."
                    ));
                })
        .register();
    }

    private static CommandBuilder getTestCommandBuilder() {
        return new CommandBuilder("test")
                .setAliases("")
                .handle(commandExecutor -> {

                });
    }



}
