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
import java.util.HashMap;
import java.util.Set;

public class PaginatedGUI extends PrivateGUIBuilder implements Paginated<ItemStack> {

    private final HashMap<Integer, AbstractGUITemplate> pageTemplates = new HashMap<>();
    private int highestPageTemplate = 0;

    public PaginatedGUI(AbstractInventoryBuilder inventoryBuilder) {
        this(inventoryBuilder, new GUISubCollectionTemplate());
    }

    public PaginatedGUI(AbstractInventoryBuilder inventoryBuilder, AbstractGUISubCollectionTemplate template) {
        super(inventoryBuilder);
        template(template);
    }

    public void addTemplateToPage(int page, @NotNull AbstractGUITemplate template) {
        if (page < 1)
            return;
        pageTemplates.put(page, template);
        this.highestPageTemplate = Math.max(page, this.highestPageTemplate);
    }

    public void removePageTemplate(int page) {
        pageTemplates.remove(page);
        recalculateHighestPageTemplate();
    }

    private void recalculateHighestPageTemplate() {
        Set<Integer> set = pageTemplates.keySet();
        if (set.size() == 0)
            this.highestPageTemplate = 0;
        else {
            int currentHighest = 0;
            for (Integer p : set)
                if (p > currentHighest)
                    currentHighest = p;
            this.highestPageTemplate = currentHighest;
        }
    }

    public PrivateGUIBuilder template(AbstractGUISubCollectionTemplate template) {
        return super.template(template);
    }

    @Override
    public int getMaxPage() {
        return (int) Math.max(this.highestPageTemplate, Math.ceil((float) getCollection().size() / getMaxElementsPerPage()));
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
        page = getPage(page);
        AbstractGUISubCollectionTemplate template = getTemplate();
        boolean hasTemplate = template != null;
        AbstractGUITemplate pageTemplate = pageTemplates.get(page);
        boolean hasPageTemplate = pageTemplate != null;

        Collection<ItemStack> collection = getPageList(page);
        for (Player viewer : viewers) {
            Inventory inventory = getInventoryBuilder().build();
            if (hasTemplate)
                template.apply(inventory, collection, viewer);
            if (hasPageTemplate)
                pageTemplate.apply(inventory, viewer);
            viewer.openInventory(inventory);
        }
        return this;
    }

    @Override
    public PrivateGUIBuilder open(Player... viewers) {
        return open(1, viewers);
    }
}
