package com.acrylic.acrylic.defaultcommands;

import com.acrylic.universal.Universal;
import com.acrylic.universal.animations.dangle.Dangle;
import com.acrylic.universal.animations.holograms.Holograms;
import com.acrylic.universal.animations.rotational.HandRotationAnimation;
import com.acrylic.universal.command.AbstractCommandBuilder;
import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.entityanimations.entities.AbstractArmorStandAnimator;
import com.acrylic.universal.entityanimations.entities.ArmorStandAnimator;
import com.acrylic.universal.events.EventBuilder;
import com.acrylic.universal.gui.GlobalGUIBuilder;
import com.acrylic.universal.gui.InventoryBuilder;
import com.acrylic.universal.gui.templates.GUITemplate;
import com.acrylic.universal.shapes.Circle;
import com.acrylic.universal.shapes.lines.Line;
import com.acrylic.universal.shapes.spiral.MultiSpiral;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.version_1_8.entity.EntityEquipmentBuilder;
import com.acrylic.version_1_8.items.ItemBuilder;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class AcrylicCommand {

    public void registerMainCommand() {
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

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(ChatUtils.get(
                "&3&l[ Acrylic ]\n" +
                        "&b/acrylic test &7Test command.\n" +
                        "    &f-list &7To see the list of test commands.\n" +
                        "&b/acrylic utils &7Utilities.\n" +
                        "    &floop <player> <amt> <str> &7Makes the user execute 'str'&f amt&7 of times.\n" +
                        "    &fsudo <player> <str> &7Makes the user execute 'str'"
        ));
    }

    private void sudo(@NotNull Player sender, String player, String str, int amt) {
        Player p = Bukkit.getPlayer(player);
        if (p == null || !p.isOnline()) {
            Universal.getMessageBuilder().sendErrorMessage("The user is not online.", sender);
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

    private CommandBuilder getUtilsCommandBuilder() {
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

    private CommandBuilder getTestCommandBuilder() {
        return CommandBuilder.create("test")
                .setTimerActive(false)
                .handle(commandExecutor -> {
                    Player sender = (Player) commandExecutor.getSender();

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

                            MultiSpiral spiral = new MultiSpiral(0f, 7);
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
                                    GUITemplate guiTemplate = new GUITemplate();
                                    guiTemplate.addGUIItem(1, ItemBuilder.of(Material.DIAMOND_BLOCK).build());
                                    guiTemplate.addGUIItem(2, ItemBuilder.of(Material.DIAMOND_BLOCK).build());
                                    guiTemplate.addGUIItem(5, ItemBuilder.of(Material.DIAMOND_BLOCK).build());
                                    guiTemplate.addGUIItem(2, ItemBuilder.of(Material.GOLD_BLOCK).build());
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
                            ).template(guiTemplate)
                                    .removeListenersOnClose(true)
                                    .update()
                                    .open(sender);
                        }),
                        CommandBuilder.create("handrotation")
                                .filter(AbstractCommandExecuted::isPlayer)
                                .handle(commandExecutor -> {
                            Player sender = (Player) commandExecutor.getSender();
                            AbstractArmorStandAnimator armorStandAnimator =
                                    new ArmorStandAnimator(sender.getLocation()).asAnimator()
                                            .asAnimator()
                                    ;
                            Location location = sender.getLocation();
                            armorStandAnimator.setEquipment(new EntityEquipmentBuilder().setItemInHand(ItemBuilder.of(Material.DIAMOND_SWORD).build()));
                            HandRotationAnimation handRotationAnimation = new HandRotationAnimation(armorStandAnimator);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    handRotationAnimation.teleport(location);
                                }
                            }.runTaskTimer(Universal.getPlugin(), 1, 1);
                        }),
                        CommandBuilder.create("dangle")
                                .filter(AbstractCommandExecuted::isPlayer)
                                .handle(commandExecutor -> {
                            Player sender = (Player) commandExecutor.getSender();
                            Dangle dangle = new Dangle();
                            for (int i = 0; i < 100; i++) {
                                AbstractArmorStandAnimator armorStandAnimator = new ArmorStandAnimator(sender.getLocation())
                                        .asAnimator();
                                HandRotationAnimation handRot = new HandRotationAnimation(armorStandAnimator);
                                armorStandAnimator.setEquipment(new EntityEquipmentBuilder().setItemInHand(new ItemStack(Material.DIAMOND_PICKAXE)));
                                dangle.addAnimation(handRot);
                                Holograms holograms = new Holograms();
                                holograms.addHologram(sender.getLocation(), 2f, "&eClick Me!", "&b&lDiamond pickaxe");
                                handRot.setHologram(holograms);
                            }
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if (dangle.hasEnded()) {
                                        cancel();
                                    }
                                    dangle.update(sender);
                                }
                            }.runTaskTimer(Universal.getPlugin(), 1, 1);

                        })
                });
    }




}
