package com.acrylic.acrylic.defaultcommands;

import com.acrylic.universal.Universal;
import com.acrylic.universal.command.AbstractCommandBuilder;
import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.events.EventBuilder;
import com.acrylic.universal.shapes.Circle;
import com.acrylic.universal.shapes.Spiral;
import com.acrylic.universal.shapes.lines.Line;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.version_1_8.items.ItemBuilder;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.CustomTimingsHandler;

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
                                .setDescription("This list.")
                                .handle(commandExecutor ->  {
                            CommandSender sender = commandExecutor.getSender();
                            sender.sendMessage(ChatUtils.get("&3&lList of Tests:"));
                            commandExecutor.getParentCommandBuilder().iterateArguments(commandBuilderExecutor -> {
                                sender.sendMessage(ChatUtils.get("  &3&l- &r&b" + commandBuilderExecutor.getName() + " &r&7"));
                            });
                        }),
                        new CommandBuilder("item")
                                .setDescription("ItemBuilder test.")
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
                                .setDescription("Circles.")
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
                                .setDescription("Line in the direction you face.")
                                .filter(AbstractCommandExecuted::isPlayer)
                                .handle(commandExecutor -> {
                            Player sender = (Player) commandExecutor.getSender();

                            Line line = new Line(sender.getLocation(), 1f);
                            line.setTo(sender.getLocation().add(sender.getLocation().getDirection().multiply(15)));
                            line.invokeAction(sender.getLocation(), (i, location) -> {
                                sender.sendBlockChange(location, (i == 1) ? Material.EMERALD_BLOCK : Material.DIAMOND_BLOCK, (byte) 0);
                            });
                        }),
                        new CommandBuilder("spiral")
                                .setTimerActive(true)
                                .setDescription("Spiral in the direction you face.")
                                .filter(AbstractCommandExecuted::isPlayer)
                                .handle(commandExecutor -> {
                            Player sender = (Player) commandExecutor.getSender();

                            Spiral spiral = new Spiral(0f, 10);
                            spiral.setOrientation(sender);
                            spiral.setRadiusIncrement(0.1f);
                            spiral.setFrequencyIncrement(0.08f);
                            spiral.setShouldUseTimeLine(true);
                            spiral.set(sender.getLocation(), sender.getLocation().add(sender.getLocation().getDirection().multiply(10)));
                            spiral.invokeAction(sender.getLocation(), (i, location) -> {
                                sender.sendBlockChange(location, (i == 1) ? Material.EMERALD_BLOCK : Material.DIAMOND_BLOCK, (byte) 0);
                            });
                        }),
                        new CommandBuilder("event")
                                .setDescription("Event test.")
                                .handle(commandExecutor -> {
                            EventBuilder
                                    .listen(EntityDamageByEntityEvent.class)
                                    .handle(event -> {
                                        Bukkit.broadcastMessage("Hit " + event.getDamager().getName());
                                    })
                                    .register();
                        })



                });
    }




}
