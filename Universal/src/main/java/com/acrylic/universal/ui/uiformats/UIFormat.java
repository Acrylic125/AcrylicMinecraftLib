package com.acrylic.universal.ui.uiformats;

import com.acrylic.universal.ui.components.GUIComponent;
import com.acrylic.universal.ui.components.GUIItem;
import com.acrylic.universal.ui.components.GUIItemComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface UIFormat
        extends GUIItemComponent {

    void setGUIItems(@NotNull Collection<GUIItem> items);

    default void addGUIItem(@NotNull GUIItem item) {
        getGUIItems().add(item);
    }

    default void removeGUIItem(@NotNull GUIItem item) {
        getGUIItems().remove(item);
    }

    default void clear() {
        getGUIItems().clear();
    }

    void format(@NotNull Inventory inventory, @NotNull Collection<GUIItem> collection, @Nullable Player viewer);

}
