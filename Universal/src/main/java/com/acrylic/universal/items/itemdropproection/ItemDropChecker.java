package com.acrylic.universal.items.itemdropproection;

import com.acrylic.universal.Universal;
import com.acrylic.universal.events.AbstractEventBuilder;
import com.acrylic.universal.events.EventBuilder;
import com.acrylic.universal.threads.Scheduler;
import com.acrylic.universal.threads.SyncScheduler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.jetbrains.annotations.NotNull;

public class ItemDropChecker {

    private ItemDropProtectionMap itemDropProtectionMap = new SimpleItemDropProtectionMap();
    private Scheduler<?> syncScheduleBuilderScheduler;
    private AbstractEventBuilder<?> entityPickupItemEventBuilder;
    private AbstractEventBuilder<ItemMergeEvent> mergeEventBuilder;

    public ItemDropChecker() {
        initialize();
        listenPickUp();
        listenItemMerge();
    }

    public ItemDropProtectionMap getItemDropProtectionMap() {
        return itemDropProtectionMap;
    }

    public void setItemDropProtectionMap(@NotNull ItemDropProtectionMap itemDropProtectionMap) {
        this.itemDropProtectionMap = itemDropProtectionMap;
    }

    private void initialize() {
        syncScheduleBuilderScheduler = Scheduler.sync()
                .runRepeatingTask(1, 1)
                .setName("ItemDropChecker : Running Scheduler")
                .handle(task -> itemDropProtectionMap.check());
        syncScheduleBuilderScheduler.build();
    }

    private void listenPickUp() {
        entityPickupItemEventBuilder = (Universal.getVersionStore().isLegacyVersion()) ?
                EventBuilder.listen(PlayerPickupItemEvent.class)
                        .priority(EventPriority.HIGHEST)
                        .setEventName("ItemDropChecker : PickUp Listener")
                        .handle(event -> {
                            ItemDropProtected itemDropProtected = itemDropProtectionMap.getItem(event.getItem());
                            if (itemDropProtected != null && !itemDropProtected.isAllowedToPickup(event.getPlayer()))
                                event.setCancelled(true);
                        })
                :
                EventBuilder.listen(EntityPickupItemEvent.class)
                        .priority(EventPriority.HIGHEST)
                        .setEventName("ItemDropChecker : PickUp Listener")
                        .handle(event -> {
                            ItemDropProtected itemDropProtected = itemDropProtectionMap.getItem(event.getItem());
                            if (itemDropProtected != null && !itemDropProtected.isAllowedToPickup(event.getEntity()))
                                event.setCancelled(true);
                        });
        entityPickupItemEventBuilder.register();
    }

    private void listenItemMerge() {
        mergeEventBuilder = EventBuilder.listen(ItemMergeEvent.class)
                .priority(EventPriority.HIGHEST)
                .setEventName("ItemDropChecker : Item Merge Listener")
                .handle(event -> {
                    ItemDropProtected itemDropProtected = itemDropProtectionMap.getItem(event.getEntity()),
                            targetItemDropProtected = itemDropProtectionMap.getItem(event.getTarget());
                    if (itemDropProtected != null || targetItemDropProtected != null)
                        event.setCancelled(true);
                });
        mergeEventBuilder.register();
    }

    public void unregister() {
        syncScheduleBuilderScheduler.cancel();
        entityPickupItemEventBuilder.unregister();
        mergeEventBuilder.unregister();
    }

}
