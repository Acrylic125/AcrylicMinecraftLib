package com.acrylic.universal.ui.uiformats;

import com.acrylic.universal.ui.InventoryDetails;
import com.acrylic.universal.ui.components.GUIItem;
import com.acrylic.universal.ui.components.GUIItemComponent;
import org.jetbrains.annotations.NotNull;

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

}
