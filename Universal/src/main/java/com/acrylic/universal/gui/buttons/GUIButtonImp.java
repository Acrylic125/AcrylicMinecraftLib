package com.acrylic.universal.gui.buttons;

import com.acrylic.universal.gui.AbstractGUIBuilder;
import org.bukkit.inventory.Inventory;

public interface GUIButtonImp {

    AbstractButtons getButtons();

    default void applyButtons(Inventory inventory, AbstractGUIBuilder builder) {
        getButtons().forEach(button -> inventory.setItem(button.getSlot(), button.getItem()));
    }

}
