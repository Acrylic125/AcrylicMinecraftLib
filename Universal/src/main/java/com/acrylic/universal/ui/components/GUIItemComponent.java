package com.acrylic.universal.ui.components;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface GUIItemComponent<T extends GUIItem> extends GUIComponent {

    @NotNull
    Collection<T> getGUIItems();

}
