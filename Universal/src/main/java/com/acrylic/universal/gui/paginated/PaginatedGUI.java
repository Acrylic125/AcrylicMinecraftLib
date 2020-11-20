package com.acrylic.universal.gui.paginated;

import com.acrylic.universal.gui.AbstractGUIBuilder;
import com.acrylic.universal.gui.AbstractInventoryBuilder;
import com.acrylic.universal.gui.PrivateGUIBuilder;
import com.acrylic.universal.gui.buttons.AbstractButtons;
import com.acrylic.universal.gui.buttons.PageButton;
import com.acrylic.universal.gui.exceptions.UnsupportedGUITemplate;
import com.acrylic.universal.gui.templates.AbstractGUISubCollectionTemplate;
import com.acrylic.universal.gui.templates.AbstractGUITemplate;
import com.acrylic.universal.gui.templates.GUISubCollectionTemplate;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

@Setter @Getter
public class PaginatedGUI
        extends PrivateGUIBuilder
        implements Paginated<ItemStack> {

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

    public PrivateGUIBuilder update(int page, Inventory inventory, Player viewer) {
        page = getPage(page);
        exactUpdate(page, getPageList(page), inventory, viewer);
        return this;
    }
    public PrivateGUIBuilder open(int page, Player... viewers) {
        Collection<ItemStack> collection = getPageList(page);
        for (Player viewer : viewers) {
            Inventory inventory = getInventoryBuilder().build();
            exactUpdate(page, collection, inventory, viewer);
            viewer.openInventory(inventory);
        }
        return this;
    }

    private void exactUpdate(int exactPage, Collection<ItemStack> collection, Inventory inventory, Player viewer) {
        AbstractGUISubCollectionTemplate template = getTemplate();
        AbstractGUITemplate pageTemplate = pageTemplates.get(exactPage);
        AbstractButtons buttons = getButtons();
        if (template != null)
            template.apply(inventory, collection, viewer);
        if (pageTemplate != null)
            pageTemplate.apply(inventory, viewer);
        if (buttons != null)
            applyButtons(inventory, this, exactPage);
    }

    public void applyButtons(Inventory inventory, AbstractGUIBuilder builder, int page) {
        getButtons().forEach(button -> {
            inventory.setItem(button.getSlot(), (button instanceof PageButton) ? ((PageButton) button).getItem(this, page) : button.getItem());
        });
    }

    @Override
    public int getMaxPage() {
        return (int) Math.max(this.highestPageTemplate, Math.ceil((float) getCollection().size() / getMaxElementsPerPage()));
    }

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

    @Override
    public PrivateGUIBuilder update(Inventory inventory) {
        return super.update(inventory);
    }

    @Override
    public PrivateGUIBuilder open(Player... viewers) {
        return open(1, viewers);
    }

    @Override
    public void applyButtons(Inventory inventory, AbstractGUIBuilder builder) {
        applyButtons(inventory, builder, 1);
    }

}
