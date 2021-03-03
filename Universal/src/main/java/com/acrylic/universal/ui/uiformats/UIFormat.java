package com.acrylic.universal.ui.uiformats;

import com.acrylic.universal.ui.components.GUIComponent;
import com.acrylic.universal.ui.components.GUIItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface UIFormat<T extends GUIItem>
        extends GUIComponent {

    @NotNull
    Collection<T> getGUIItems();

    void setGUIItems(@NotNull Collection<T> items);

    default void addGUIItem(@NotNull T item) {
        getGUIItems().add(item);
    }

    default void removeGUIItem(@NotNull T item) {
        getGUIItems().remove(item);
    }

    default void clear() {
        getGUIItems().clear();
    }

    void format(@NotNull Inventory inventory, @NotNull Collection<T> collection, @Nullable Player viewer);

}
