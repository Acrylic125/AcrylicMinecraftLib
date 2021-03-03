package com.acrylic.universal.ui.uibuttons;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GUIButtons<T extends GUIButton> {

    private Map<Integer, T> buttonMap;

    public GUIButtons() {
        this(new HashMap<>());
    }

    public GUIButtons(@NotNull Map<Integer, T> buttonMap) {
        this.buttonMap = buttonMap;
    }

    @NotNull
    public Map<Integer, T> getButtonMap() {
        return buttonMap;
    }

    public void setButtonMap(@NotNull Map<Integer, T> buttonMap) {
        this.buttonMap = buttonMap;
    }

    @Nullable
    public T getButtonFromSlot(int slot) {
        return this.buttonMap.get(slot);
    }

    public void addButton(int slot, @NotNull T button) {
        this.buttonMap.put(slot, button);
    }

    @NotNull
    public Collection<T> getButtons() {
        return buttonMap.values();
    }

}
