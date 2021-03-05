package com.acrylic.universal.ui.components;

import com.acrylic.universal.ui.GUI;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface GUIItemComponent extends GUIComponent {

    @NotNull
    Collection<GUIItem> getGUIItems();

}
