package com.acrylic.acrylic.defaultcommands;

import com.acrylic.time.Time;
import com.acrylic.universal.Universal;
import com.acrylic.universal.animations.dangle.Dangle;
import com.acrylic.universal.animations.holograms.Holograms;
import com.acrylic.universal.animations.rotational.HandRotationAnimation;
import com.acrylic.universal.command.AbstractCommandBuilder;
import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.entityanimations.entities.AbstractArmorStandAnimator;
import com.acrylic.universal.entityanimations.entities.AbstractGiantAnimator;
import com.acrylic.universal.entityanimations.entities.ArmorStandAnimator;
import com.acrylic.universal.entityanimations.entities.GiantAnimator;
import com.acrylic.universal.events.EventBuilder;
import com.acrylic.universal.files.bukkit.Configuration;
import com.acrylic.universal.geometry.circular.Circle;
import com.acrylic.universal.geometry.circular.Spiral;
import com.acrylic.universal.geometry.line.QuadraticYLine;
import com.acrylic.universal.regions.SimpleRegion;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.universal.threads.Scheduler;
import com.acrylic.universal.ui.InventoryUIBuilder;
import com.acrylic.universal.ui.PrivateGUI;
import com.acrylic.universal.ui.items.GUIItem;
import com.acrylic.universal.ui.components.GUIStaticComponent;
import com.acrylic.universal.ui.items.CustomClickableItem;
import com.acrylic.universal.ui.uiformats.MiddleUIFormat;
import com.acrylic.universal.utils.StringUtils;
import com.acrylic.universal.utils.keys.BlockKey;
import com.acrylic.version_1_8.entity.EntityEquipmentBuilder;
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

import java.util.HashMap;

public class AcrylicCommand {

    private static final HashMap<BlockKey, String> map = new HashMap<>();

    public static void registerMainCommand() {
        CommandBuilder.create("acrylic")
                .setAliases("acryliccmd")
                .permissions("acrylic.acrylic")
                .arguments(new AbstractCommandBuilder[] {
                        getTestCommandBuilder(),
                        getUtilsCommandBuilder()
                })
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
            Universal.getAcrylicPlugin().getMessageBuilder().sendErrorMessage("The user is not online.", sender);
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
                }).arguments(new AbstractCommandBuilder[] {
                        CommandBuilder.create("loop")
                                .filter(AbstractCommandExecuted::isPlayer)
                                .setTimerActive(true)
                                .handle(commandExecutor -> {

                            Player sender = (Player) commandExecutor.getSender();
                            String arg1 = commandExecutor.getArg(0);
                            String arg2 = commandExecutor.getArg(1);
                            String str = commandExecutor.getArgs(2);
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
                                .filter(AbstractCommandExecuted::isPlayer)
                                .setTimerActive(true)
                                .handle(commandExecutor -> {
                            Player sender = (Player) commandExecutor.getSender();
                            String arg1 = commandExecutor.getArg(0);
                            String str = commandExecutor.getArgs(1);
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
                });
    }

    private static CommandBuilder getTestCommandBuilder() {
        return CommandBuilder.create("test")
                .setTimerActive(true)
                .handle(commandExecutor -> {
                    Player sender = (Player) commandExecutor.getSender();
                    Bukkit.broadcastMessage(
                            StringUtils.COMMA_SEPARATED_NUMBER_FORMATTER.format(StringUtils.NUMBER_STRING_FORMATTER.formatDouble(commandExecutor.getArg(0)))
                    );
                    ChatUtils.send(sender, "&bThis command executes the current test. To see other tests, do &f/acrylic test -list&b!");
                }).arguments(new AbstractCommandBuilder[] {
                        //List
                        CommandBuilder.create("scheduler")
                                .filter(AbstractCommandExecuted::isPlayer)
                                .setTimerActive(true)
                                .handle(commandExecutor -> {
                                    Scheduler.sync().runDelayedTask(5, Time.SECONDS).handleThenBuild(task -> {
                                        Bukkit.broadcastMessage("hello!");
                                    });

                        }),
                        CommandBuilder.create("item")
                                .filter(AbstractCommandExecuted::isPlayer)
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
                                .filter(AbstractCommandExecuted::isPlayer)
                                .setTimerActive(true)
                                .handle(commandExecutor -> {
                            Player player = (Player) commandExecutor.getSender();
                            SimpleRegion simpleRegion = new SimpleRegion(player.getLocation(), player.getLocation().add(10, 10 , 10));
                            simpleRegion.showMarkers();
                        }),
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
                                            .enchant(Enchantment.DAMAGE_ALL, 10)
                                            .shiny(true)
                                            .build()
                            );
                        }),
                        CommandBuilder.create("circle")
                                .setTimerActive(true)
                                .filter(AbstractCommandExecuted::isPlayer)
                                .handle(commandExecutor -> {
                            Player sender = (Player) commandExecutor.getSender();

                            Circle circle = new Circle(3, 36);
                            circle.modifyOrientationRelativeTo(sender.getLocation());
                            circle.setLocation(sender.getLocation());
                            circle.iterateToIndex(location -> sender.sendBlockChange(location, Material.DIAMOND_BLOCK, (byte) 0), 36);
                        }),
                        CommandBuilder.create("line")
                                .setTimerActive(true)
                                .filter(AbstractCommandExecuted::isPlayer)
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
                                .setTimerActive(true)
                                .filter(AbstractCommandExecuted::isPlayer)
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
                                .filter(AbstractCommandExecuted::isPlayer)
                                .handle(commandExecutor -> {
                                    Player sender = (Player) commandExecutor.getSender();
                            InventoryUIBuilder inventoryUIBuilder = new InventoryUIBuilder();
                            inventoryUIBuilder
                                    .rows(6)
                                    .addItem(0, ItemBuilder.of(Material.DIAMOND_SWORD))
                                    .addItem(1, ItemBuilder.of(Material.EMERALD_BLOCK))
                                    .removeItem(0);
                            GUIItem guiItem = new CustomClickableItem(ItemBuilder.of(Material.IRON_SWORD).shiny(true).build(),
                                    (event, button) -> Bukkit.broadcastMessage("Clicked something!")
                            );
                            PrivateGUI.builder()
                                    .inventory(inventoryUIBuilder)
                                    .cancelInventoryClick(true)
                                    .clickListener(clickEvent -> {
                                        Bukkit.broadcastMessage("CLICKED!");
                                    })
                                    .closeListener(closeEvent -> {
                                        Bukkit.broadcastMessage("Closed!");
                                    })
                                    .uiFormat(MiddleUIFormat.builder()
                                            .initialRow(2)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .addGUIItem(guiItem)
                                            .build())
                                    .staticComponent(GUIStaticComponent.builder()
                                            .addComponent(3, guiItem)
                                            .build())
                                    .build()
                                    .openGUIFor(sender);
                        }),
                        CommandBuilder.create("handrotation")
                                .filter(AbstractCommandExecuted::isPlayer)
                                .handle(commandExecutor -> {
                            Player sender = (Player) commandExecutor.getSender();
                            AbstractGiantAnimator armorStandAnimator =
                                    new GiantAnimator(sender.getLocation()).asAnimator()
                                            .asAnimator()
                                    ;
                            Location location = sender.getLocation();
                            armorStandAnimator.setEquipment(new EntityEquipmentBuilder().setItemInHand(sender.getItemInHand()));
                            HandRotationAnimation handRotationAnimation = new HandRotationAnimation(armorStandAnimator);
                            Scheduler.sync()
                                    .runRepeatingTask(1, 1)
                                    .handle(task -> {
                                        handRotationAnimation.teleportWithHolograms(location);
                                    }).build();
                        }),
                        CommandBuilder.create("dangle")
                                .filter(AbstractCommandExecuted::isPlayer)
                                .handle(commandExecutor -> {
                            Player sender = (Player) commandExecutor.getSender();
                            Dangle dangle = new Dangle(sender);
                            for (int i = 0; i < 10; i++) {
                                AbstractArmorStandAnimator armorStandAnimator = new ArmorStandAnimator(sender.getLocation())
                                        .asAnimator();
                                HandRotationAnimation handRot = new HandRotationAnimation(armorStandAnimator);
                                armorStandAnimator.setEquipment(new EntityEquipmentBuilder().setItemInHand(new ItemStack(Material.DIAMOND_PICKAXE)));
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
                            configuration.loadFromResources(Universal.getPlugin());
                            Bukkit.broadcastMessage(configuration.getFileEditor() + "");
                            configuration.saveFile();
                        })
                });
    }


}
