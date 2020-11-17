package com.acrylic.universal.gui.paginated;

import com.acrylic.universal.gui.AbstractInventoryBuilder;
import com.acrylic.universal.gui.PrivateGUIBuilder;
import com.acrylic.universal.gui.exceptions.UnsupportedGUITemplate;
import com.acrylic.universal.gui.templates.AbstractGUISubCollectionTemplate;
import com.acrylic.universal.gui.templates.AbstractGUITemplate;
import com.acrylic.universal.gui.templates.GUISubCollectionTemplate;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class PaginatedGUI extends PrivateGUIBuilder implements Paginated<ItemStack> {

    public PaginatedGUI(AbstractInventoryBuilder inventoryBuilder) {
        this(inventoryBuilder, new GUISubCollectionTemplate());
    }

    public PaginatedGUI(AbstractInventoryBuilder inventoryBuilder, AbstractGUISubCollectionTemplate template) {
        super(inventoryBuilder);
        template(template);
    }

    public PrivateGUIBuilder template(AbstractGUISubCollectionTemplate template) {
        return super.template(template);
    }

    /**
     * Dont use for this.
     */
    @Deprecated
    @Override
    public PrivateGUIBuilder template(AbstractGUITemplate template) {
        throw new UnsupportedGUITemplate();
    }

    @Override
    public AbstractGUISubCollectionTemplate getTemplate() {
        return (AbstractGUISubCollectionTemplate) super.getTemplate();
    }

    @Override
    public int getMaxElementsPerPage() {
        return getTemplate().getTotalItemsInMenu();
    }

    @NotNull
    @Override
    public Collection<ItemStack> getCollection() {
        final AbstractGUISubCollectionTemplate subCollectionTemplate = getTemplate();
        return subCollectionTemplate.getSubCollection();
    }

    public PrivateGUIBuilder open(int page, Player... viewers) {
        AbstractGUISubCollectionTemplate template = getTemplate();
        boolean hasTemplate = template != null;
        Collection<ItemStack> collection = getPageList(page);
        for (Player viewer : viewers) {
            Inventory inventory = getInventoryBuilder().build();
            if (hasTemplate)
                template.apply(inventory, collection);
            viewer.openInventory(inventory);
        }
        return this;
    }

    @Override
    public PrivateGUIBuilder open(Player... viewers) {
        return open(1, viewers);
    }
}
