package com.acrylic.universal.files.parsers;

import com.acrylic.universal.files.fileeditor.FileEditor;
import com.acrylic.universal.files.parsers.exceptions.ParserException;
import com.acrylic.universal.gui.*;
import com.acrylic.universal.gui.paginated.PaginatedGUI;
import com.acrylic.universal.gui.templates.AbstractGUITemplate;
import com.acrylic.universal.gui.templates.GUISubCollectionTemplate;
import com.acrylic.universal.gui.templates.GUITemplate;
import com.acrylic.universal.gui.templates.MiddleGUITemplate;
import org.bukkit.Bukkit;

import java.util.Map;

public final class GUIParser extends AbstractVariableParser<AbstractGUIBuilder> {

    public static final String COMPOUND_GUI = "gui";
    public static final String KEY_STYLE = "style";
    public static final String KEY_ROWS = "rows";
    public static final String KEY_TITLE = "title";
    public static final String KEY_TEMPLATE = "template";
    public static final String KEY_TEMPLATE_TYPE = "type";
    public static final String KEY_TEMPLATE_STARTING_ROW = "starting-row";
    public static final String KEY_TEMPLATE_LAST_ROW = "last-row";

    public GUIParser() { }

    public GUIParser(FileEditor fileEditor) {
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
    private static class GUIProducer {

        private final ParserMap<AbstractGUIBuilder> parserMap;

        public GUIProducer(GUIParser guiParser, Map<String, Object> parseFrom) {
            Bukkit.broadcastMessage(parseFrom + "");
            parserMap = new ParserMap<>(guiParser, parseFrom.get(COMPOUND_GUI));
        }

        public AbstractGUIBuilder get() {
            AbstractGUIBuilder guiBuilder = getMainBuilder();
            AbstractGUITemplate template = getTemplate();
            if (template != null)
                guiBuilder.template(template);
            return guiBuilder;
        }

        private AbstractInventoryBuilder getInventoryBuilder() {
            int rows = parserMap.getInteger(KEY_ROWS, new ParserException("The rows specified is not a valid integer."), 6);
            String title = parserMap.getString(KEY_TITLE, null);
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
            ParserMap<AbstractGUIBuilder> map = parserMap.getParserMap(KEY_TEMPLATE);
            if (map != null) {
                String type = map.parseString(map.getParseFrom().get(KEY_TEMPLATE_TYPE), "GLOBAL").toUpperCase();
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
            String style = parserMap.getString(KEY_STYLE, "GLOBAL").toUpperCase();
            switch (style) {
                case "GLOBAL":
                    return new GlobalGUIBuilder(getInventoryBuilder());
                case "PRIVATE":
                    return new PrivateGUIBuilder(getInventoryBuilder());
                case "PAGINATED":
                    return new PaginatedGUI(getInventoryBuilder());
                default:
                    throw new ParserException(style + " is not a valid style. You may specify either GLOBAL, PRIVATE, or PAGINATED.");
            }
        }

    }

}
