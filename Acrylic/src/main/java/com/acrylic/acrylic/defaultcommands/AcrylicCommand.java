package com.acrylic.acrylic.defaultcommands;

import com.acrylic.universal.command.AbstractCommandBuilder;
import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.events.EventBuilder;
import com.acrylic.universal.gui.GlobalGUIBuilder;
import com.acrylic.universal.gui.InventoryBuilder;
import com.acrylic.universal.gui.paginated.PaginatedGUI;
import com.acrylic.universal.gui.templates.GUISubCollectionTemplate;
import com.acrylic.universal.gui.templates.GUITemplate;
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
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

@UtilityClass
public class AcrylicCommand {

    public void registerMainCommand() {
        
        CommandBuilder.create("acrylic")
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
        return CommandBuilder.create("test")
                .setTimerActive(true)
                .handle(commandExecutor -> {
                    Player sender = (Player) commandExecutor.getSender();

                    new PaginatedGUI(InventoryBuilder.create().title("Test"));

                    sender.sendMessage(ChatUtils.get("&bThis command executes the current test. To see other tests, do &f/acrylic test -list&b!"));
                }).arguments(new AbstractCommandBuilder[] {
                        //List
                        CommandBuilder.create("-list")
                                .handle(commandExecutor ->  {
                            CommandSender sender = commandExecutor.getSender();
                            sender.sendMessage(ChatUtils.get("&3&lList of Tests:"));
                            commandExecutor.getParentCommandBuilder().iterateArguments(commandBuilderExecutor -> {
                                sender.sendMessage(ChatUtils.get("  &3&l- &r&b" + commandBuilderExecutor.getName()));
                            });
                        }),
                        CommandBuilder.create("item")
                                .filter(AbstractCommandExecuted::isPlayer)
                                .handle(commandExecutor -> {
                            Player player = (Player) commandExecutor.getSender();
                            player.getInventory().addItem(
                                    ItemBuilder.of(Material.DIAMOND_SWORD)
                                            .shiny(true)
                                            .build()
                            );
                        }),
                        CommandBuilder.create("circle")
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
                        CommandBuilder.create("line")
                                .setTimerActive(true)
                                .filter(AbstractCommandExecuted::isPlayer)
                                .handle(commandExecutor -> {
                            Player sender = (Player) commandExecutor.getSender();

                            Line line = new Line(sender.getLocation(), 1f);
                            line.setTo(sender.getLocation().add(sender.getLocation().getDirection().multiply(15)));
                            line.invokeAction(sender.getLocation(), (i, location) -> {
                                sender.sendBlockChange(location, (i == 1) ? Material.EMERALD_BLOCK : Material.DIAMOND_BLOCK, (byte) 0);
                            });
                        }),
                        CommandBuilder.create("spiral")
                                .setTimerActive(true)
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
                        CommandBuilder.create("event")
                                .handle(commandExecutor -> {
                            EventBuilder
                                    .listen(EntityDamageByEntityEvent.class)
                                    .handle(event -> {
                                        Bukkit.broadcastMessage("Hit " + event.getDamager().getName());
                                    })
                                    .register();

                        }),
                        CommandBuilder.create("gui")
                                .filter(AbstractCommandExecuted::isPlayer)
                                .handle(commandExecutor -> {

                                    Player sender = (Player) commandExecutor.getSender();
                            GlobalGUIBuilder.create(
                                    InventoryBuilder
                                            .create()
                                            .rows(3)
                                            .title("Hello")
                                            .build()
                            ).clickListener(EventBuilder
                                    .listen(InventoryClickEvent.class)
                                    .filter(event -> event.getView().getPlayer().isOp())
                                    .handle(event -> {
                                        event.setCancelled(true);
                                    })
                            ).template(new GUITemplate()
                                    .addGUIItem(1, ItemBuilder.of(Material.DIAMOND_BLOCK).build())
                                    .addGUIItem(2, ItemBuilder.of(Material.DIAMOND_BLOCK).build())
                                    .addGUIItem(5, ItemBuilder.of(Material.DIAMOND_BLOCK).build())
                                    .addGUIItem(2, ItemBuilder.of(Material.GOLD_BLOCK).build())
                            )
                                    .removeListenersOnClose(true)
                                    .update()
                                    .open(sender);

                        })



                });
    }




}
