package com.acrylic.universal.gui.uiformats;

import com.acrylic.universal.gui.components.GUIItemComponent;

public interface UIFormat
        extends GUIItemComponent {

    default void clear() {
        getGUIItems().clear();
    }

}
