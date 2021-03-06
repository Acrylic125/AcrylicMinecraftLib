package com.acrylic.acrylic.defaultcommands;

import com.acrylic.universal.MCLib;
import com.acrylic.universal.animations.dangle.Dangle;
import com.acrylic.universal.animations.holograms.Holograms;
import com.acrylic.universal.animations.rotational.HandRotationAnimation;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.command.CommandExecuted;
import com.acrylic.universal.command.CommandUtils;
import com.acrylic.universal.entity.ArmorStandInstance;
import com.acrylic.universal.entity.GiantEntityInstance;
import com.acrylic.universal.entity.impl.BukkitArmorStandInstance;
import com.acrylic.universal.entity.impl.BukkitGiantEntityInstance;
import com.acrylic.universal.events.EventBuilder;
import com.acrylic.universal.files.bukkit.Configuration;
import com.acrylic.universal.geometry.circular.Circle;
import com.acrylic.universal.geometry.circular.Spiral;
import com.acrylic.universal.geometry.line.QuadraticYLine;
import com.acrylic.universal.regions.SimpleRegion;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.universal.threads.ScheduleExecutor;
import com.acrylic.universal.threads.Scheduler;
import com.acrylic.universal.gui.GlobalGUI;
import com.acrylic.universal.gui.InventoryUIBuilder;
import com.acrylic.universal.gui.components.GUIStaticComponent;
import com.acrylic.universal.gui.items.BasicGUIItem;
import com.acrylic.universal.utils.TimeConverter;
import com.acrylic.version_1_8.equipment.EntityEquipmentBuilderImpl;
import com.acrylic.version_1_8.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import weights.Weigher;

public class AcrylicCommand {

    public static void registerMainCommand() {
        CommandBuilder.create("acrylic")
                .aliases("acryliccmd")
                .permissions("acrylic.acrylic")
                .arguments(
                        getTestCommandBuilder(),
                        getUtilsCommandBuilder()
                )
                .handle(commandExecutor -> {
                    sendHelp(commandExecutor.getSender());
                })
        .register();
    }

    private static void sendHelp(CommandSender sender) {
        sender.sendMessage(ChatUtils.get(
                "&3&l[ Acrylic ]\n" +
                        "&b/acrylic test &7Test command.\n" +
                        "    &f-list &7To see the list of test commands.\n" +
                        "&b/acrylic utils &7Utilities.\n" +
                        "    &floop <player> <amt> <str> &7Makes the user execute 'str'&f amt&7 of times.\n" +
                        "    &fsudo <player> <str> &7Makes the user execute 'str'"
        ));
    }

    private static void sudo(@NotNull Player sender, String player, String str, int amt) {
        Player p = Bukkit.getPlayer(player);
        if (p == null || !p.isOnline()) {
            sender.sendMessage(ChatUtils.get("&c&l[!]&r&c The user is not online."));
            return;
        }
        String name = p.getName();
        if (str.startsWith("/")) {
            str = str.replaceFirst("/","");
            for (int i = 0; i < amt; i++)
                p.performCommand(str);
            sender.sendMessage(ChatUtils.get("&eMade &f" + name + "&r &eexecute command &f" + str + "&r&7 [" + amt + "x]"));
        } else {
            for (int i = 0; i < amt; i++)
                p.chat(str);
            sender.sendMessage(ChatUtils.get("&eMade &f" + name + "&r &esay &f" + str + "&r&7 [" + amt + "x]"));
        }
    }

    private static CommandBuilder getUtilsCommandBuilder() {
        return CommandBuilder.create("utils")
                .aliases("util", "utilities", "utility")
                .handle(commandExecutor -> {
                    sendHelp(commandExecutor.getSender());
                }).arguments(
                        CommandBuilder.create("loop")
                                .filter(CommandExecuted::isExecutedByPlayer)
                                .timer(true)
                                .handle(commandExecutor -> {

                            Player sender = (Player) commandExecutor.getSender();
                            String arg1 = commandExecutor.getArg(0);
                            String arg2 = commandExecutor.getArg(1);
                            String str = commandExecutor.getArgsAsString(2);
                            if (arg1 == null || arg2 == null || str == null)
                                sendHelp(sender);
                            else {
                                try {
                                    int amt = Integer.parseInt(arg2);
                                    sudo(sender, arg1, str, amt);
                                } catch (NumberFormatException ex) {
                                    sendHelp(sender);
                                }
                            }
                        }),
                        CommandBuilder.create("sudo")
                                .filter(CommandExecuted::isExecutedByPlayer)
                                .timer(true)
                                .handle(commandExecutor -> {
                            Player sender = (Player) commandExecutor.getSender();
                            String arg1 = commandExecutor.getArg(0);
                            String str = commandExecutor.getArgsAsString(1);
                            if (arg1 == null || str == null)
                                sendHelp(sender);
                            else {
                                try {
                                    sudo(sender, arg1, str, 1);
                                } catch (NumberFormatException ex) {
                                    sendHelp(sender);
                                }
                            }
                        })
                );
    }

    private static CommandBuilder getTestCommandBuilder() {
        return CommandBuilder.create("test")
                .timer(true)
                .handle(commandExecutor -> {
                    Player sender = (Player) commandExecutor.getSender();

                    ChatUtils.send(sender, "&bThis command executes the current test. To see other tests, do &f/acrylic test -list&b!");
                }).arguments(
                        //List
                        CommandBuilder.create("scheduler")
                                .filter(CommandExecuted::isExecutedByPlayer)
                                .timer(true)
                                .handle(commandExecutor -> {
                                    Bukkit.broadcastMessage(ScheduleExecutor.ASYNC_EXECUTOR.getTasks().size() + "");

                        }),
                        CommandBuilder.create("item")
                                .filter(CommandExecuted::isExecutedByPlayer)
                                .handle(commandExecutor -> {
                            Player player = (Player) commandExecutor.getSender();
                            player.getInventory().addItem(
                                    ItemBuilder.of(Material.DIAMOND_SWORD)
                                            .enchant(Enchantment.DAMAGE_ALL, 10)
                                            .shiny(true)
                                            .build()
                            );
                        }),
                        CommandBuilder.create("region")
                                .filter(CommandExecuted::isExecutedByPlayer)
                                .timer(true)
                                .handle(commandExecutor -> {
                            Player player = (Player) commandExecutor.getSender();
                            SimpleRegion simpleRegion = new SimpleRegion(player.getLocation(), player.getLocation().add(10, 10 , 10));
                            simpleRegion.showMarkers();
                        }),
                        CommandBuilder.create("-list")
                                .handle(commandExecutor ->  {
                            CommandSender sender = commandExecutor.getSender();
                            sender.sendMessage(ChatUtils.get("&3&lList of Tests:"));
                            commandExecutor.getParent().iterateArguments(commandBuilderExecutor -> {
                                sender.sendMessage(ChatUtils.get("  &3&l- &r&b" + commandBuilderExecutor.getName()));
                            });
                        }),
                        CommandBuilder.create("item")
                                .filter(CommandExecuted::isExecutedByPlayer)
                                .handle(commandExecutor -> {
                            Player player = (Player) commandExecutor.getSender();
                            player.getInventory().addItem(
                                    ItemBuilder.of(Material.DIAMOND_SWORD)
                                            .enchant(Enchantment.DAMAGE_ALL, 10)
                                            .shiny(true)
                                            .build()
                            );
                        }),
                        CommandBuilder.create("circle")
                                .timer(true)
                                .filter(CommandExecuted::isExecutedByPlayer)
                                .handle(commandExecutor -> {
                            Player sender = (Player) commandExecutor.getSender();

                            Circle circle = new Circle(3, 36);
                            circle.modifyOrientationRelativeTo(sender.getLocation());
                            circle.setLocation(sender.getLocation());
                            circle.iterateToIndex(location -> sender.sendBlockChange(location, Material.DIAMOND_BLOCK, (byte) 0), 36);
                        }),
                        CommandBuilder.create("timer")
                                .timer(true)
                                .filter(CommandExecuted::isExecutedByPlayer)
                                .handle(commandExecutor -> {
                                    TimeConverter timeConverter = new TimeConverter();
                                    timeConverter.setValueFormat("&6");
                                    timeConverter.setSuffixFormat("&6");
                                    Bukkit.broadcastMessage(timeConverter.convert(CommandUtils.getLong(commandExecutor, 0, 0)).toString());
                                }),
                        CommandBuilder.create("line")
                                .timer(true)
                                .filter(CommandExecuted::isExecutedByPlayer)
                                .handle(commandExecutor -> {
                            Player sender = (Player) commandExecutor.getSender();

                            QuadraticYLine linearLine = new QuadraticYLine();
                            linearLine.setPoints(20);
                            linearLine.setYMultiplier(0.05f);
                            linearLine.setYOffset(1f);
                            linearLine.setSourceAndToLocation(sender.getLocation(), sender.getLocation().add(sender.getLocation().getDirection().multiply(10)));
                            linearLine.iterateToIndex(location -> {
                                sender.sendBlockChange(location, Material.DIAMOND_BLOCK, (byte) 0);
                            }, 20);
                        }),
                        CommandBuilder.create("spiral")
                                .timer(true)
                                .filter(CommandExecuted::isExecutedByPlayer)
                                .handle(commandExecutor -> {
                            Player sender = (Player) commandExecutor.getSender();
                            Spiral spiral = new Spiral();
                            spiral.setLocation(sender.getLocation());
                            spiral.modifyOrientationRelativeTo(sender);
                            spiral.setRadiusIncrement(1);
                            spiral.setAmountOfSpirals(3);
                            spiral.setAngleIncrement(Float.parseFloat(commandExecutor.getArg(0)));
                            spiral.iterateToIndex(location -> sender.sendBlockChange(location, Material.DIAMOND_BLOCK, (byte) 0), 100);
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
                                .filter(CommandExecuted::isExecutedByPlayer)
                                .handle(commandExecutor -> {
                                    Player sender = (Player) commandExecutor.getSender();
                            GlobalGUI.builder()
                                    .cancelInventoryClick(true)
                                    .inventory(new InventoryUIBuilder().rows(6).build())
                                    .staticComponent(GUIStaticComponent.builder()
                                            .addItem(0, new BasicGUIItem(new ItemStack(Material.STONE)))
                                            .build())
                                    .build().openGUIFor(sender);
                        }),
                        CommandBuilder.create("handrotation")
                                .filter(CommandExecuted::isExecutedByPlayer)
                                .handle(commandExecutor -> {
                            Player sender = (Player) commandExecutor.getSender();
                            GiantEntityInstance armorStandAnimator = BukkitGiantEntityInstance.builder(sender.getLocation())
                                    .asAnimator()
                                    .upsideDown()
                                    .buildEntityInstance();
                            Location location = sender.getLocation();
                            armorStandAnimator.setEquipment(new EntityEquipmentBuilderImpl().setItemInHand(sender.getItemInHand()));
                            HandRotationAnimation handRotationAnimation = new HandRotationAnimation(armorStandAnimator);
                            Scheduler.sync()
                                    .runRepeatingTask(1, 1)
                                    .handle(task -> {
                                        handRotationAnimation.teleportWithHolograms(location);
                                    }).build();
                        }),
                        CommandBuilder.create("dangle")
                                .filter(CommandExecuted::isExecutedByPlayer)
                                .handle(commandExecutor -> {
                            Player sender = (Player) commandExecutor.getSender();
                            Dangle dangle = new Dangle(sender);
                            for (int i = 0; i < 10; i++) {
                                ArmorStandInstance armorStandInstance = BukkitArmorStandInstance.builder(sender.getLocation())
                                        .asAnimator().buildEntityInstance();
                                HandRotationAnimation handRot = new HandRotationAnimation(armorStandInstance);
                                armorStandInstance.setEquipment(new EntityEquipmentBuilderImpl().setItemInHand(new ItemStack(Material.DIAMOND_PICKAXE)));
                                dangle.addAnimation(handRot);
                                Holograms holograms = new Holograms();
                                holograms.addHologram(sender.getLocation(), 2f, "&eClick Me!", "&b&lDiamond pickaxe");
                                handRot.setHologram(holograms);
                            }
                            dangle.startScheduler();
                        }),
                        CommandBuilder.create("files")
                                .handle(commandExecutor -> {
                            Configuration configuration = new Configuration("acrylic.yml", null);
                            configuration.loadFromResources(MCLib.getPlugin());
                            Bukkit.broadcastMessage(configuration.getFileEditor() + "");
                            configuration.saveFile();
                        })
                );
    }


}
