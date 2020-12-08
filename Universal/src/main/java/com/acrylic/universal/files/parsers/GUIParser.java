package com.acrylic.universal.files.parsers;

import com.acrylic.universal.events.EventBuilder;
import com.acrylic.universal.files.fileeditor.FileEditor;
import com.acrylic.universal.files.parsers.exceptions.ParserException;
import com.acrylic.universal.gui.*;
import com.acrylic.universal.gui.buttons.AbstractButtons;
import com.acrylic.universal.gui.buttons.Button;
import com.acrylic.universal.gui.buttons.PageButton;
import com.acrylic.universal.gui.paginated.PaginatedGUI;
import com.acrylic.universal.gui.templates.*;
import com.acrylic.universal.text.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public final class GUIParser extends AbstractVariableParser<AbstractGUIBuilder> {

    public static final String COMPOUND_GUI = "gui";
    public static final String KEY_STYLE = "style";
    public static final String KEY_ROWS = "rows";
    public static final String KEY_TITLE = "title";
    public static final String KEY_TEMPLATE = "template";
    public static final String KEY_TEMPLATE_STARTING_ROW = "starting-row";
    public static final String KEY_TEMPLATE_LAST_ROW = "last-row";
    public static final String KEY_SUB_COLLECTION_ITEMS = "sub-collection-items";
    public static final String KEY_ITEMS = "items";
    public static final String KEY_ITEM_SLOT = "slot";
    public static final String KEY_TYPE = "type";
    public static final String KEY_BUTTONS = "buttons";

    public GUIParser() { }

    public GUIParser(@NotNull Map<String, Object> map) {
        super(map);
    }

    public GUIParser(@NotNull FileEditor fileEditor) {
        super(fileEditor);
    }

    @Override
    public Map<String, Object> serialize(AbstractGUIBuilder toParse) {
        return null;
    }

    @Override
    public AbstractGUIBuilder parse(Map<String, Object> parseFrom) {
        return new GUIProducer(this, parseFrom).get();
    }

    /**
     * GUI Parser.
     */
    private static class GUIProducer extends ParserMap {

        public GUIProducer(GUIParser guiParser, Map<String, Object> parseFrom) {
            super(guiParser, parseFrom.get(COMPOUND_GUI));
        }

        public AbstractGUIBuilder get() {
            AbstractGUIBuilder guiBuilder = getMainBuilder();
            AbstractGUITemplate template = getTemplate();
            if (template != null) {
                if (template instanceof AbstractGUISubCollectionTemplate)
                    applySubCollectionItems((AbstractGUISubCollectionTemplate) template);
                applyItems(template);
                guiBuilder.template(template);
            }
            guiBuilder.clickListener(EventBuilder.listen(InventoryClickEvent.class).handle(event -> event.setCancelled(true)));
            if (guiBuilder instanceof PrivateGUIBuilder)
                applyButtons((PrivateGUIBuilder) guiBuilder);
            return guiBuilder;
        }

        private AbstractInventoryBuilder getInventoryBuilder() {
            int rows = getInteger(KEY_ROWS, new ParserException("The rows specified is not a valid integer."), 6);
            String title = getString(KEY_TITLE, null);
            AbstractInventoryBuilder inventoryBuilder = InventoryBuilder.create().rows(rows);
            if (title != null)
                inventoryBuilder.title(title);
            return inventoryBuilder;
        }

        /**
         * template:
         *   type: MIDDLE, NORMAL, SUBCOLLECION
         *   starting-row: 2
         *   last-row: @rows
         */
        private AbstractGUITemplate getTemplate() {
            ParserMap map = getParserMap(KEY_TEMPLATE);
            if (map != null) {
                String type = map.parseString(map.getParseFrom().get(KEY_TYPE), "GLOBAL").toUpperCase();
                switch (type) {
                    case "NORMAL":
                        return new GUITemplate();
                    case "SUBCOLLECTION":
                    case "MIDDLE":
                        int startingRow = map.getInteger(KEY_TEMPLATE_STARTING_ROW, new ParserException("The specified template start row is not a valid integer."), 1);
                        int lastRow = map.getInteger(KEY_TEMPLATE_LAST_ROW, new ParserException("The specified template last row is not a valid integer."), 1);
                        return (type.equals("SUBCOLLECTION")) ? new GUISubCollectionTemplate(startingRow, lastRow) : new MiddleGUITemplate(startingRow, lastRow);
                    default:
                        throw new ParserException("The specified template is not a valid template.");
                }
            }
            return null;
        }

        //PAGINATED, PRIVATE, GLOBAL
        private AbstractGUIBuilder getMainBuilder() {
            String style = getString(KEY_STYLE, "GLOBAL").toUpperCase();
            switch (style) {
                case "GLOBAL": return new GlobalGUIBuilder(getInventoryBuilder());
                case "PRIVATE": return new PrivateGUIBuilder(getInventoryBuilder());
                case "PAGINATED": return new PaginatedGUI(getInventoryBuilder());
                default: throw new ParserException(style + " is not a valid style. You may specify either GLOBAL, PRIVATE, or PAGINATED.");
            }
        }

        private void applySubCollectionItems(AbstractGUISubCollectionTemplate template) {
            ParserMap map = getParserMap(KEY_SUB_COLLECTION_ITEMS);
            if (map != null) {
                for (String key : map.getParseFrom().keySet()) {
                    Map<String, Object> itemMap = map.getMap(key);
                    template.add(new ItemStackParser(itemMap).parse());
                }
            }
        }


        private void applyItems(AbstractGUITemplate template) {
            ParserMap map = getParserMap(KEY_ITEMS);
            if (map != null) {
                for (String key : map.getParseFrom().keySet()) {
                    ParserMap itemMap = map.getParserMap(key);
                    if (itemMap != null)
                        template.addGUIItem(itemMap.getInteger(KEY_ITEM_SLOT, 0), new ItemStackParser(itemMap.getParseFrom()).parse());
                }
            }
        }

        /**
         * PAGE_BUTTON, CONSOLE_COMMAND_BUTTON, PLAYER_COMMAND_BUTTON
         *
         * player-commands:
         * - ???
         * console-commands:
         * - ???
         * messages:
         * - ???
         * broadcast:
         * - ???
         */
        private void applyButtons(PrivateGUIBuilder guiBuilder) {
            ParserMap map = getParserMap(KEY_BUTTONS);
            if (map != null) {
                AbstractButtons buttons = guiBuilder.getButtons();
                for (String key : map.getParseFrom().keySet()) {
                    ParserMap itemMap = map.getParserMap(key);
                    if (itemMap != null) {
                        String type = itemMap.getString(KEY_TYPE, "NONE").toUpperCase();
                        switch (type) {
                            case "PAGE_BUTTON":
                                buttons.addItem(new PageButton(itemMap.getInteger(KEY_ITEM_SLOT, 0), new ItemStackParser(itemMap.getParseFrom()).parse(), itemMap.getInteger("page-flip-by", 1)));
                                break;
                            case "NONE":
                                buttons.addItem(new Button(itemMap.getInteger(KEY_ITEM_SLOT, 0), new ItemStackParser(itemMap.getParseFrom()).parse()));
                                final List<String> playerCommands = itemMap.getList("player-commands", new ArrayList<>(), String.class);
                                final List<String> consoleCommands = itemMap.getList("console-commands", new ArrayList<>(), String.class);
                                final List<String> messages = itemMap.getList("messages", new ArrayList<>(), String.class);
                                final List<String> broadcast = itemMap.getList("broadcast", new ArrayList<>(), String.class);
                                buttons.addItem(new Button(itemMap.getInteger(KEY_ITEM_SLOT, 0), new ItemStackParser(itemMap.getParseFrom()).parse(), (clickedItem, event, builder) -> {
                                    Player clicker = (Player) event.getWhoClicked();
                                    playerCommands.forEach(command -> Bukkit.dispatchCommand(event.getWhoClicked(), parseStringDefaultVars(command, clicker)));
                                    consoleCommands.forEach(command -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), parseStringDefaultVars(command, clicker)));
                                    messages.forEach(msg -> clicker.sendMessage(parseStringDefaultVars(map.getString(msg, msg), clicker)));
                                    broadcast.forEach(msg -> Bukkit.broadcastMessage(parseStringDefaultVars(map.getString(msg, msg), clicker)));
                                }));
                                break;
                            default:
                                throw new ParserException(type + " is not a valid button type. You may specify either NONE, or PAGE_BUTTON.");
                        }
                    }
                }
                guiBuilder.setButtons(buttons);
            }
        }

        private static final Pattern STRING_DEFAULT_VARS = Pattern.compile("%player%");

        private String parseStringDefaultVars(String msg, Player clicker) {
            return ChatUtils.get(STRING_DEFAULT_VARS.matcher(msg).replaceAll(clicker.getName()));
        }

    }

}
