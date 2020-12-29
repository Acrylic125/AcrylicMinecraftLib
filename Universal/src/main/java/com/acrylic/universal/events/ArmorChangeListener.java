package com.acrylic.universal.events;

import com.acrylic.universal.Universal;
import com.acrylic.universal.files.bukkit.Configuration;
import com.acrylic.universal.files.fileeditor.FileEditor;
import com.acrylic.universal.items.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseArmorEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ArmorChangeListener {

    private boolean HOT_SWAP_ENABLED = true;
    private boolean HANDLE_ON_QUIT = true;
    private boolean HANDLE_ON_JOIN = true;

    public ArmorChangeListener(@NotNull Configuration configuration) {
        configuration.getFileEditor().getFileEditor("armor-equip-event").safeFileAccess(fileEditor -> {
            HOT_SWAP_ENABLED = fileEditor.getBoolean("hot-swap");
            HANDLE_ON_JOIN = fileEditor.getBoolean("trigger-on-join");
            HANDLE_ON_QUIT = fileEditor.getBoolean("trigger-on-leave");
        });
        listenInventoryClickEvent();
        listenRightClickEvent();
        listenDragEvent();
        listenDeath();
        listenPlayerLoginAndOut();
        listenDispenser();
        listenItemBreak();
    }

    private ArmorChangeEvent interact(Player player, ItemStack item){
        if (ItemUtils.isAir(item))
            return null;
        EntityEquipment equipment = player.getEquipment();
        ArmorType newArmorType = ArmorType.getArmorType(item);
        if (newArmorType == null || equipment == null)
            return null;
        ItemStack previousItem, newItem = item.clone();
        previousItem = newArmorType.getItemFromEquipment(equipment);
        ArmorChangeEvent.ChangeType changeType = getChangeType(ArmorType.getArmorType(previousItem), newArmorType);
        boolean isItemHeldAir = ItemUtils.isAir(previousItem);
        if (changeType == null || !(isItemHeldAir || HOT_SWAP_ENABLED))
            return null;
        ArmorChangeEvent armorChangeEvent = new ArmorChangeEvent(player)
                .setPreviousItem(previousItem)
                .setNewItem(newItem)
                .setEquipType(ArmorChangeEvent.EquipType.RIGHT_CLICK)
                .setArmorType(newArmorType)
                .setHotSwap(HOT_SWAP_ENABLED && !isItemHeldAir)
                .setChangeType(changeType);
        Bukkit.getPluginManager().callEvent(armorChangeEvent);
        if (!armorChangeEvent.isCancelled()) {
            newArmorType.setItemInEquipment(equipment, item);
            equipment.setItemInHand((previousItem == null) ? null : previousItem.clone());
        }
        return armorChangeEvent;
    }

    private void iterateArmor(@NotNull LivingEntity entity, @NotNull IteratedArmorPiece armorChangeEventManipulation) {
        EntityEquipment equipment = entity.getEquipment();
        if (equipment != null) {
            for (ItemStack item : equipment.getArmorContents()) {
                ArmorType armorType = ArmorType.getArmorType(item);
                if (armorType != null) {
                    ArmorChangeEvent event = new ArmorChangeEvent(entity)
                            .setArmorType(armorType);
                    armorChangeEventManipulation.accept(event, armorType, item);
                    Bukkit.getPluginManager().callEvent(event);
                }
            }
        }
    }

    private void listenItemBreak() {
        EventBuilder.listen(PlayerItemBreakEvent.class)
                .priority(EventPriority.HIGHEST)
                .handleThenRegister(event -> {
                    ItemStack oldItem = event.getBrokenItem();
                    ArmorType type = ArmorType.getArmorType(oldItem);
                    if (type != null) {
                        Bukkit.getPluginManager().callEvent(new ArmorChangeEvent(event.getPlayer())
                                .setArmorType(type)
                                .setEquipType(ArmorChangeEvent.EquipType.ITEM_BREAK)
                                .setChangeType(ArmorChangeEvent.ChangeType.UN_EQUIP)
                                .setPreviousItem(oldItem)
                                .setNewItem(null)
                        );
                    }
                });
    }

    private void listenPlayerLoginAndOut() {
        if (HANDLE_ON_JOIN) {
            EventBuilder.listen(PlayerJoinEvent.class)
                    .priority(EventPriority.HIGHEST)
                    .handleThenRegister(event -> {
                        LivingEntity entity = event.getPlayer();
                        iterateArmor(entity, getIAPUnEquip(ArmorChangeEvent.EquipType.JOIN));
                        iterateArmor(entity, getIAPEquip(ArmorChangeEvent.EquipType.JOIN));
                    });
        }
        if (HANDLE_ON_QUIT) {
            EventBuilder.listen(PlayerQuitEvent.class)
                    .priority(EventPriority.HIGHEST)
                    .handleThenRegister(event -> {
                        LivingEntity entity = event.getPlayer();
                        iterateArmor(entity, getIAPUnEquip(ArmorChangeEvent.EquipType.QUIT));
                    });
        }
    }

    private void listenDeath() {
        EventBuilder.listen(PlayerDeathEvent.class)
                .priority(EventPriority.HIGHEST)
                .handleThenRegister(event -> {
                    LivingEntity entity = event.getEntity();
                    iterateArmor(entity, getIAPUnEquip(ArmorChangeEvent.EquipType.DEATH));
                });
        EventBuilder.listen(PlayerRespawnEvent.class)
                .priority(EventPriority.HIGHEST)
                .handleThenRegister(event -> {
                    LivingEntity entity = event.getPlayer();
                    iterateArmor(entity, getIAPEquip(ArmorChangeEvent.EquipType.DEATH));
                });
    }

    private void listenDragEvent() {
        EventBuilder.listen(InventoryDragEvent.class)
                .priority(EventPriority.HIGHEST)
                .handleThenRegister(event -> {
                    ItemStack newItem = event.getOldCursor();
                    ArmorType type = ArmorType.getArmorType(newItem);
                    if (type == null)
                        return;
                    int slot = type.getSlot();
                    if (slot != -1 && slot == event.getRawSlots().stream().findFirst().orElse(0)) {
                        ArmorChangeEvent changeEvent = new ArmorChangeEvent(event.getWhoClicked())
                                .setChangeType(ArmorChangeEvent.ChangeType.EQUIP)
                                .setArmorType(type)
                                .setEquipType(ArmorChangeEvent.EquipType.INVENTORY_DRAG)
                                .setPreviousItem(null)
                                .setNewItem(newItem); //ArmorChangeEvent.call(event.getWhoClicked(),newItem,null,ArmorEquipType.INVENTORY,false);
                        Bukkit.getPluginManager().callEvent(changeEvent);
                        if (changeEvent.isCancelled()){
                            event.setResult(Event.Result.DENY);
                            event.setCancelled(true);
                        }
                    }
                });
    }

    private void listenRightClickEvent() {
        EventBuilder.listen(PlayerInteractEvent.class)
                .priority(EventPriority.HIGHEST)
                .handleThenRegister(event -> {
                    Action action = event.getAction();
                    if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
                        ArmorChangeEvent armorChangeEvent = interact(event.getPlayer(), event.getItem());
                        if (armorChangeEvent != null)
                            event.setCancelled(armorChangeEvent.isCancelled());
                    }
                });
        EventBuilder.listen(PlayerInteractEntityEvent.class)
                .priority(EventPriority.HIGHEST)
                .handleThenRegister(event -> {
                    ArmorChangeEvent armorChangeEvent = interact(event.getPlayer(), event.getPlayer().getItemInHand());
                    if (armorChangeEvent != null)
                        event.setCancelled(armorChangeEvent.isCancelled());
                });
    }

    private void listenInventoryClickEvent() {
        EventBuilder.listen(InventoryClickEvent.class)
                .priority(EventPriority.HIGHEST)
                .handleThenRegister(event -> {
                    InventoryAction action = event.getAction();
                    if (action.equals(InventoryAction.NOTHING))
                        return;
                    ClickType clickType = event.getClick();
                    ItemStack newItem, oldItem;
                    Inventory inventory = event.getClickedInventory();
                    if (inventory == null || !(inventory.getType() == InventoryType.PLAYER || inventory.getType() == InventoryType.CRAFTING))
                        return;
                    Player player = (Player) event.getView().getPlayer();
                    InventoryType.SlotType slotType = event.getSlotType();
                    ArmorType newArmorType, oldArmorType;
                    ArmorChangeEvent.ChangeType changeType = null;
                    switch (clickType) {
                        case LEFT:
                        case RIGHT:
                        case CONTROL_DROP:
                        case CREATIVE:
                        case DROP:
                            if (!slotType.equals(InventoryType.SlotType.ARMOR))
                                return;
                            newItem = event.getCursor();
                            oldItem = event.getCurrentItem();
                            newArmorType = ArmorType.getArmorType(newItem);
                            oldArmorType = ArmorType.getArmorType(oldItem);
                            break;
                        case SHIFT_LEFT:
                        case SHIFT_RIGHT:
                            newItem = event.getCurrentItem();
                            newArmorType = ArmorType.getArmorType(newItem);
                            if (newArmorType == null)
                                return;
                            oldItem = newArmorType.getItemFromEquipment(player.getEquipment());
                            if (!slotType.equals(InventoryType.SlotType.ARMOR)) { //Equipping.
                                if (!event.getView().getType().equals(InventoryType.CRAFTING) || !(ItemUtils.isAir(oldItem)))
                                    return;
                                oldArmorType = ArmorType.getArmorType(oldItem);
                            } else { //Un-equipping
                                if (inventory.firstEmpty() == -1)
                                    return;
                                newItem = null;
                                oldArmorType = ArmorType.getArmorType(oldItem);
                                if (oldArmorType == null || oldArmorType.getSlot() != event.getRawSlot())
                                    return;
                                newArmorType = null;
                            }
                            break;
                        case NUMBER_KEY:
                            int rawSlot = event.getRawSlot();
                            if (!event.getClickedInventory().getType().equals(InventoryType.PLAYER) || !(rawSlot >= 5 && rawSlot <= 8))
                                return;
                            int slot = event.getSlot();
                            oldItem = inventory.getItem(slot);
                            newItem = inventory.getItem(event.getHotbarButton());
                            ArmorType armorType = ArmorType.getArmorType((ItemUtils.isAir(newItem)) ? oldItem : newItem);
                            if (armorType != null && armorType.getSlot() != rawSlot)
                                newItem = null;
                            newArmorType = ArmorType.getArmorType(newItem);
                            oldArmorType = ArmorType.getArmorType(oldItem);
                            break;
                        default:
                            return;
                    }
                    changeType = getChangeType(oldArmorType, newArmorType);
                    if (changeType != null) {
                        ArmorChangeEvent armorChangeEvent = new ArmorChangeEvent(player)
                                .setChangeType(changeType)
                                .setEquipType(ArmorChangeEvent.EquipType.INVENTORY_CLICK)
                                .setPreviousItem((changeType == ArmorChangeEvent.ChangeType.EQUIP) ? null : oldItem)
                                .setNewItem((changeType == ArmorChangeEvent.ChangeType.UN_EQUIP) ? null : newItem);
                        Bukkit.getPluginManager().callEvent(armorChangeEvent);
                        if (armorChangeEvent.isCancelled())
                            event.setCancelled(true);
                    }
                });
    }

    private ArmorChangeEvent.ChangeType getChangeType(ArmorType oldArmorType, ArmorType newArmorType) {
        boolean newArmorNE = newArmorType != null, oldArmorNE = oldArmorType != null;
        if (newArmorNE && oldArmorNE)
            return ArmorChangeEvent.ChangeType.CHANGE;
        else if (!oldArmorNE && newArmorNE)
            return ArmorChangeEvent.ChangeType.EQUIP;
        else if (oldArmorNE)
            return ArmorChangeEvent.ChangeType.UN_EQUIP;
        return null;
    }

    private IteratedArmorPiece getIAPUnEquip(@NotNull ArmorChangeEvent.EquipType equipType) {
        return (changeEvent, armorType, currentItem) -> {
            changeEvent
                    .setEquipType(equipType)
                    .setChangeType(ArmorChangeEvent.ChangeType.UN_EQUIP)
                    .setPreviousItem(currentItem)
                    .setNewItem(null);
        };
    }

    private IteratedArmorPiece getIAPEquip(@NotNull ArmorChangeEvent.EquipType equipType) {
        return (changeEvent, armorType, currentItem) -> {
            changeEvent
                    .setEquipType(equipType)
                    .setChangeType(ArmorChangeEvent.ChangeType.EQUIP)
                    .setPreviousItem(null)
                    .setNewItem(currentItem);
        };
    }

    private void listenDispenser() {
        if (!Universal.getVersionStore().isLegacyVersion()) {
            EventBuilder.listen(BlockDispenseArmorEvent.class)
                    .priority(EventPriority.HIGHEST)
                    .handleThenRegister(event -> {
                        ItemStack newItem = event.getItem();
                        ArmorType type = ArmorType.getArmorType(newItem);
                        if (type != null) {
                            ArmorChangeEvent changeEvent = new ArmorChangeEvent(event.getTargetEntity())
                                    .setPreviousItem(null)
                                    .setNewItem(newItem)
                                    .setArmorType(type)
                                    .setEquipType(ArmorChangeEvent.EquipType.DISPENSER)
                                    .setChangeType(ArmorChangeEvent.ChangeType.EQUIP);
                            Bukkit.getPluginManager().callEvent(changeEvent);
                            event.setCancelled(changeEvent.isCancelled());
                        }
                    });
        }
    }

    @FunctionalInterface
    private interface IteratedArmorPiece {

        void accept(ArmorChangeEvent changeEvent, ArmorType armorType, ItemStack currentItem);

    }

}
