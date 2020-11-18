package com.acrylic.acrylic.defaultcommands;

import com.acrylic.universal.command.AbstractCommandBuilder;
import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.events.EventBuilder;
import com.acrylic.universal.gui.GlobalGUIBuilder;
import com.acrylic.universal.gui.InventoryBuilder;
import com.acrylic.universal.gui.paginated.PaginatedGUI;
import com.acrylic.universal.gui.templates.AlternateGUITemplate;
import com.acrylic.universal.gui.templates.GUITemplate;
import com.acrylic.universal.shapes.Circle;
import com.acrylic.universal.shapes.spiral.MultiSpiral;
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
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class AcrylicCommand {

    private final static PaginatedGUI ui;

    static {
        AlternateGUITemplate ct = new AlternateGUITemplate(2);
        for (int i = 0; i < 10000; i++) {
            ct.add(new ItemStack(Material.DIAMOND, i + 1));
        }
        ct.setOffsetLeft(1);
        ct.setOffsetRight(0);
        ct.setTotalItemsInMenu(2,5);
        ui = new PaginatedGUI(InventoryBuilder.create().rows(6).
                title("Test"),ct);
    }

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

                    ui.open(Integer.parseInt(commandExecutor.getArg(0)), sender);
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

                            MultiSpiral spiral = new MultiSpiral(0f, 10);
                            spiral.setOrientation(sender);
                            spiral.setRadiusIncrement(2f);
                            spiral.setAngleOffset(Float.parseFloat(commandExecutor.getArg(0)));
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
