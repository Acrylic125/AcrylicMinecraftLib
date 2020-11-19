package com.acrylic.universal.gui.guiitems;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * This is a simple implementation for conditional item presets.
 */
public class ConditionalGUIItem extends GUIItem implements PlayerGUIItem {

    /**
     * This is the conditionMap or the list of conditoons.
     */
    private final HashMap<Predicate<Player>, ItemStack> conditionalMap = new HashMap<>();

    /**
     *
     * @param slot The main slot.
     * @param defaultItem The default item if none of the conditionalMap conditions
     *                    are met.
     */
    public ConditionalGUIItem(int slot, @Nullable ItemStack defaultItem) {
        super(slot, defaultItem);
    }

    public void addConditionalItem(@NotNull final Predicate<Player> condition, @Nullable final ItemStack item) {
        conditionalMap.put(condition, item);
    }

    public void removeConditionalItem(@NotNull final Predicate<Player> condition) {
        conditionalMap.remove(condition);
    }

    public void clear() {
        conditionalMap.clear();
    }

    @Override
    public void applyTo(@NotNull final Inventory inventory, @NotNull final Player viewer) {
        for (Map.Entry<Predicate<Player>, ItemStack> entry : conditionalMap.entrySet())
            if (entry.getKey().test(viewer)) {
                inventory.setItem(getSlot(), entry.getValue());
                return;
            }
        applyTo(inventory);
    }


}
