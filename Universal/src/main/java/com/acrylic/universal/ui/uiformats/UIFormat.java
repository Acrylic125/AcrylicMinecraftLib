package com.acrylic.universal.ui.uiformats;

import com.acrylic.universal.ui.components.GUIItemComponent;

public interface UIFormat
        extends GUIItemComponent {

    default void clear() {
        getGUIItems().clear();
    }

}
