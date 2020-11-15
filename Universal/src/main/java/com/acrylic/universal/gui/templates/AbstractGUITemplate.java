package com.acrylic.universal.gui.templates;

import com.acrylic.universal.gui.GUIBuilder;
import com.acrylic.universal.gui.GlobalGUIBuilder;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public abstract class AbstractGUITemplate implements Iterable<GUIItem> {

    private final Collection<GUIItem> guiItems;

    public AbstractGUITemplate() {
        guiItems = new ArrayList<>();
    }

    public AbstractGUITemplate(Collection<GUIItem> collection) {
        guiItems = collection;
    }

    public GUIItem getGUIItem(int slot) {
        for (GUIItem guiItem : guiItems)
            if (guiItem.getSlot() == slot)
                return guiItem;
        return null;
    }

    public AbstractGUITemplate addGUIItem(@NotNull GUIItem guiItem) {
        GUIItem check = getGUIItem(guiItem.getSlot());
        if (check != null)
            guiItems.remove(check);
        guiItems.add(guiItem);
        return this;
    }

    public AbstractGUITemplate addGUIItem(int slot, ItemStack item) {
        return addGUIItem(new GUIItem(slot, item));
    }

    public AbstractGUITemplate addGUIItem(@NotNull GUIItem... guiItems) {
        for (GUIItem guiItem : guiItems)
            addGUIItem(guiItem);
        return this;
    }

    @Override
    public Spliterator<GUIItem> spliterator() {
        return guiItems.spliterator();
    }

    @Override
    public void forEach(Consumer<? super GUIItem> action) {
        guiItems.forEach(action);
    }

    @NotNull
    @Override
    public Iterator<GUIItem> iterator() {
        return guiItems.iterator();
    }

    public void apply(@NotNull Inventory inventory) {
        final int size = inventory.getSize();
        guiItems.forEach(guiItem -> {
            int s = guiItem.getSlot();
            if (s < size)
                inventory.setItem(s, guiItem.getItem());
        });
    }


}
