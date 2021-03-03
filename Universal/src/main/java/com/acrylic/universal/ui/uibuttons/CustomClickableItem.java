package com.acrylic.universal.ui.uibuttons;

import com.acrylic.universal.ui.OpenDetails;
import com.acrylic.universal.ui.UIComparableItemInfo;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CustomClickableItem implements GUIClickableItem {

    private final String id = UUID.randomUUID().toString();
    private ItemStack item;
    private ItemClickAction<CustomClickableItem> clickedAction;
    private boolean active = true;

    public CustomClickableItem(@Nullable ItemStack item, @Nullable ItemClickAction<CustomClickableItem> buttonItemClickAction) {
        setItem(item);
        this.clickedAction = buttonItemClickAction;
    }

    public CustomClickableItem setItem(@Nullable ItemStack item) {
        this.item = wrapGUIItem(item);
        return this;
    }

    @Nullable
    public ItemClickAction<CustomClickableItem> getClickedAction() {
        return clickedAction;
    }

    public CustomClickableItem setClickedAction(@Nullable ItemClickAction<CustomClickableItem> clickedAction) {
        this.clickedAction = clickedAction;
        return this;
    }

    @Override
    public void onClicked(InventoryClickEvent event) {
        if (clickedAction != null)
            this.clickedAction.run(event, this);
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public CustomClickableItem setActive(boolean active) {
        this.active = active;
        return this;
    }

    @NotNull
    @Override
    public String getID() {
        return id;
    }

    @Nullable
    @Override
    public ItemStack getItem(OpenDetails openDetails) {
        return item;
    }

    @Override
    public boolean doesItemMatchWithThis(@NotNull UIComparableItemInfo.Comparison uiComparableComparisonInfo) {
        return id.equals(uiComparableComparisonInfo.getID());
    }

    @Override
    public int hashCode() {
        return item.hashCode();
    }
}
