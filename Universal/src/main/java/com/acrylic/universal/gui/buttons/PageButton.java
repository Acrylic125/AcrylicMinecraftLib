package com.acrylic.universal.gui.buttons;

import com.acrylic.universal.gui.paginated.PaginatedGUI;
import com.acrylic.universal.text.ChatUtils;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Supported Variables:
 * You can use these variables in the name and/or the lore
 * to display based on the current PaginatedGUI.
 *
 * {PAGE} : The current page.
 * {LAST_PAGE} : The last page.
 */
@Setter @Getter
public class PageButton extends PrivateButton {

    public static final String VAR_PAGE = "{PAGE}";
    public static final String VAR_LAST_PAGE = "{LAST_PAGE}";
    private static final String NBT_PAGE = "page";

    private int pageIncrement;
    /** If this is true, When #getItem(gui, page) is used,
     * and if {page + pageIncrement} is smaller than 1
     * or greater than gui#getMaxPage, the method will
     * return null;
     *
     * This can be used for next and last page buttons.
     */
    private boolean nullIfOutOfBounds = true;

    public PageButton(int slot, @NotNull ItemStack item) {
        this(slot, item, 0);
    }

    public PageButton(int slot, @NotNull ItemStack item, int pageIncrement) {
        super(slot, item);
        this.pageIncrement = pageIncrement;
        setButtonAction((clickedItem, event, guiBuilder) -> {
            NBTItem nbtItem = new NBTItem(clickedItem);
            NBTCompound compound = nbtItem.getCompound(AbstractButton.NBT_COMPOUND_NAME);
            if (compound != null) {
                int page = compound.getInteger(NBT_PAGE);
                if (guiBuilder instanceof PaginatedGUI)
                    ((PaginatedGUI) guiBuilder).update(page, event.getClickedInventory(), (Player) event.getView().getPlayer());
            }
        });
    }

    @Override
    public String getId() {
        return "PageButton";
    }

    public ItemStack getItem(PaginatedGUI gui, int page) {
        ItemStack item = super.getItem();
        int lastPage = gui.getMaxPage();
        page = page + pageIncrement;
        if (nullIfOutOfBounds && (page < 1 || page > lastPage))
            return null;
        page = gui.getPage(page);
        NBTItem nbtItem = new NBTItem(convert(item, page, lastPage));
        NBTCompound compound = nbtItem.getCompound(AbstractButton.NBT_COMPOUND_NAME);
        compound.setInteger(NBT_PAGE, page);
        return nbtItem.getItem();
    }

    private ItemStack convert(@NotNull ItemStack item, int page, int lastPage) {
        ItemMeta meta = item.getItemMeta();
        String pageAsString = page + "";
        String lastPageAsString = lastPage + "";
        if (meta != null) {
            List<String> l = meta.getLore();
            if (l != null) {
                ArrayList<String> lore = new ArrayList<>();
                l.forEach(line -> lore.add(ChatUtils.get(line.replace(VAR_PAGE, pageAsString).replace(VAR_LAST_PAGE, lastPageAsString))));
                meta.setLore(lore);
            }
            String name = meta.getDisplayName();
            meta.setDisplayName(ChatUtils.get(name.replace(VAR_PAGE, pageAsString).replace(VAR_LAST_PAGE, lastPageAsString)));
        }
        item.setItemMeta(meta);
        return item;
    }

}
