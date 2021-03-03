package com.acrylic.universal.ui;

import com.acrylic.universal.events.AbstractEventBuilder;
import com.acrylic.universal.interfaces.Terminable;
import com.acrylic.universal.ui.uibuttons.GUIButton;
import com.acrylic.universal.ui.components.GUIStaticComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GUI<T extends GUIButton> extends Terminable {

    @Nullable
    GUIStaticComponent<T> getStaticComponent();

    @Nullable
    AbstractEventBuilder<InventoryClickEvent> getClickListener();

    @Nullable
    AbstractEventBuilder<InventoryCloseEvent> getCloseListener();

    void openGUIFor(@NotNull Player player);

    default void openGUIFor(@NotNull Player... players) {
        for (Player player : players)
            openGUIFor(player);
    }

    void createOrUpdate();

}
