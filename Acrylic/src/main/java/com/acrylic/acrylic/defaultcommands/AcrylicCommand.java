package com.acrylic.acrylic.defaultcommands;

import com.acrylic.universal.command.AbstractCommandBuilder;
import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.shapes.Circle;
import com.acrylic.universal.shapes.Line;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.version_1_8.items.ItemBuilder;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                                    "&b/acrylic test &7Test command.\n" +
                                    "    &f-list &7To see the list of test commands."
                    ));
                })
        .register();
    }

    private CommandBuilder getTestCommandBuilder() {
        return new CommandBuilder("test")
                .setTimerActive(true)
                .handle(commandExecutor -> {
                    Player sender = (Player) commandExecutor.getSender();

                    sender.sendMessage(ChatUtils.get("&bThis command executes the current test. To see other tests, do &f/acrylic test -list&b!"));
                }).arguments(new AbstractCommandBuilder[] {
                        //List
                        new CommandBuilder("-list")
                                .handle(commandExecutor ->  {
                            CommandSender sender = commandExecutor.getSender();
                            sender.sendMessage(ChatUtils.get("&3&lList of Tests:"));
                            commandExecutor.getParentCommandBuilder().iterateArguments(commandBuilderExecutor -> {
                                sender.sendMessage(ChatUtils.get("  &3&l- &r&b" + commandBuilderExecutor.getName()));
                            });
                        }),
                        new CommandBuilder("item")
                                .filter(AbstractCommandExecuted::isPlayer)
                                .handle(commandExecutor -> {
                            Player player = (Player) commandExecutor.getSender();
                            player.getInventory().addItem(
                                    ItemBuilder.of(Material.DIAMOND_SWORD)
                                            .shiny(true)
                                            .build()
                            );
                        }),
                        new CommandBuilder("circle")
                                .setTimerActive(true)
                                .filter(AbstractCommandExecuted::isPlayer)
                                .handle(commandExecutor -> {
                            Player sender = (Player) commandExecutor.getSender();

                            Circle circle = new Circle(3, 25);
                            circle.setOrientation(sender).setShouldReuse(true);
                            circle.invokeAction(25, sender.getLocation().add(sender.getLocation().getDirection().multiply(3)), (i, location) -> {
                                sender.sendBlockChange(location, (i == 1) ? Material.EMERALD_BLOCK : Material.DIAMOND_BLOCK, (byte) 0);
                            });
                        }),
                        new CommandBuilder("line")
                                .setTimerActive(true)
                                .filter(AbstractCommandExecuted::isPlayer)
                                .handle(commandExecutor -> {
                            Player sender = (Player) commandExecutor.getSender();

                            Line line = new Line(sender.getLocation(), 1f);
                            line.setTo(sender.getLocation().add(sender.getLocation().getDirection().multiply(15)));
                            line.invokeAction(25, sender.getLocation(), (i, location) -> {
                                sender.sendBlockChange(location, (i == 1) ? Material.EMERALD_BLOCK : Material.DIAMOND_BLOCK, (byte) 0);
                            });
                        })


                });
    }




}
