package com.acrylic.universal.ui.components;

import com.acrylic.universal.ui.InventoryDetails;
import com.acrylic.universal.ui.items.GUIItem;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GUIStaticComponent
        implements GUIItemComponent {

    private Map<Integer, GUIItem> componentMap;

    public GUIStaticComponent() {
        this(new HashMap<>());
    }

    public GUIStaticComponent(@NotNull Map<Integer, GUIItem> componentMap) {
        this.componentMap = componentMap;
    }

    @NotNull
    public Map<Integer, GUIItem> getComponentMap() {
        return componentMap;
    }

    public void setComponentMap(@NotNull Map<Integer, GUIItem> componentMap) {
        this.componentMap = componentMap;
    }

    @Nullable
    public GUIItem getComponent(int slot) {
        return this.componentMap.get(slot);
    }

    public void addComponent(int slot, @NotNull GUIItem component) {
        this.componentMap.put(slot, component);
    }

    @Override
    public void applyComponentToInventory(@NotNull InventoryDetails inventoryDetails) {
        Inventory inventory = inventoryDetails.getInventory();
        componentMap.forEach((slot, item) -> inventory.setItem(slot, item.getItem(inventoryDetails)));
    }

    @NotNull
    @Override
    public Collection<GUIItem> getGUIItems() {
        return componentMap.values();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final Map<Integer, GUIItem> componentMap = new HashMap<>();

        private Builder() {}

        public Builder addItem(int slot, @NotNull GUIItem component) {
            componentMap.put(slot, component);
            return this;
        }

        public Builder removeItem(int slot) {
            componentMap.remove(slot);
            return this;
        }

        public GUIStaticComponent build() {
            GUIStaticComponent components = new GUIStaticComponent();
            components.setComponentMap(this.componentMap);
            return components;
        }

    }

}
