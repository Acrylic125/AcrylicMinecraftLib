package com.acrylic.universal.ui.components;

import com.acrylic.universal.ui.OpenDetails;
import com.acrylic.universal.ui.uibuttons.GUIButton;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GUIStaticComponent<T extends GUIItem>
        implements GUIComponent {

    private Map<Integer, T> componentMap;

    public GUIStaticComponent() {
        this(new HashMap<>());
    }

    public GUIStaticComponent(@NotNull Map<Integer, T> componentMap) {
        this.componentMap = componentMap;
    }

    @NotNull
    public Map<Integer, T> getComponentMap() {
        return componentMap;
    }

    public void setComponentMap(@NotNull Map<Integer, T> componentMap) {
        this.componentMap = componentMap;
    }

    @Nullable
    public T getComponent(int slot) {
        return this.componentMap.get(slot);
    }

    public void addComponent(int slot, @NotNull T component) {
        this.componentMap.put(slot, component);
    }

    @NotNull
    public Collection<T> getComponents() {
        return componentMap.values();
    }

    @Override
    public void applyComponentToInventory(@NotNull Inventory inventory, @Nullable Player player) {
        OpenDetails openDetails = new OpenDetails(player, inventory);
        componentMap.forEach((slot, item) -> inventory.setItem(slot, item.getItem(openDetails)));
    }

    public static Builder<GUIButton> builder() {
        return new Builder<>();
    }

    public static <T extends GUIButton> Builder<T> builder(@NotNull Class<T> buttonType) {
        return new Builder<>();
    }

    public static class Builder<T extends GUIItem> {

        private final Map<Integer, T> componentMap = new HashMap<>();

        private Builder() {}

        public Builder<T> addComponent(int slot, @NotNull T component) {
            componentMap.put(slot, component);
            return this;
        }

        public Builder<T> removeComponent(int slot) {
            componentMap.remove(slot);
            return this;
        }

        public GUIStaticComponent<T> build() {
            GUIStaticComponent<T> components = new GUIStaticComponent<>();
            components.setComponentMap(this.componentMap);
            return components;
        }

    }

}
