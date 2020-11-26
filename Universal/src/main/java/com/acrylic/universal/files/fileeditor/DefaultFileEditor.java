package com.acrylic.universal.files.fileeditor;

import com.acrylic.universal.files.parsers.ItemStackParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import files.editor.Configurable;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultFileEditor implements FileEditor {

    private Map<String, Object> contents;
    private final FileEditor parent;

    @SuppressWarnings("unchecked")
    public DefaultFileEditor(@NotNull File file, @NotNull ObjectMapper mapper) {
        try {
            this.contents = (HashMap<String, Object>) mapper.readValue(file, HashMap.class);
        } catch (IOException ex) {
            this.contents = new HashMap<>();
        }
        this.parent = null;
    }

    public DefaultFileEditor(@NotNull Map<String, Object> contents, @Nullable FileEditor parent) {
        this.contents = contents;
        this.parent = parent;
    }

    public DefaultFileEditor(@NotNull Map<String, Object> contents) {
        this(contents, null);
    }

    public DefaultFileEditor() {
        this(new HashMap<>(), null);
    }

    @NotNull
    @Override
    public Map<String, Object> getContents() {
        return contents;
    }

    @Nullable
    @Override
    public FileEditor getParent() {
        return parent;
    }

    @Nullable
    @Override
    public FileEditor getFileEditor(@NotNull String s) {
        Object o = getObject(s);
        HashMap<String, Object> newMap = new HashMap<>();
        if (o instanceof Map) {
            ((Map<?, ?>) o).forEach((var, val) -> {
                newMap.put(var.toString(), val);
            });
        }
        contents.put(s, newMap);
        return new DefaultFileEditor(newMap, this);
    }

    @NotNull
    @Override
    public FileEditor getInstance() {
        return this;
    }

    @Override
    public byte getByte(@NotNull String s) {
        Object o = getObject(s);
        return (o instanceof Byte) ? (byte) o : 0;
    }

    @Override
    public char getChar(@NotNull String s) {
        Object o = getObject(s);
        return (o instanceof Character) ? (char) o : '?';
    }

    @NotNull
    @Override
    public Number getDecimalNumber(@NotNull String s) {
        return getDouble(s);
    }

    @Override
    public double getDouble(@NotNull String s) {
        Object o = getObject(s);
        return (o instanceof Long || o instanceof Double || o instanceof Integer || o instanceof Float || o instanceof Short || o instanceof Byte) ? (double) o : 0;
    }

    @Override
    public float getFloat(@NotNull String s) {
        Object o = getObject(s);
        return (o instanceof Float || o instanceof Short || o instanceof Byte) ? (float) o : 0;
    }

    @Override
    public int getInt(@NotNull String s) {
        Object o = getObject(s);
        return (o instanceof Integer || o instanceof Float || o instanceof Short || o instanceof Byte) ? (int) o : 0;
    }

    @Nullable
    @Override
    public List<?> getList(@NotNull String s) {
        Object o = getObject(s);
        return (o instanceof List<?>) ? (List<?>) o : new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public <T> List<T> getList(@NotNull String s, @NotNull Class<T> clazz) {
        List<?> list = getList(s);
        ArrayList<T> newList = new ArrayList<T>();
        if (list == null)
            return newList;
        for (Object o : list) {
            if (o.getClass().isInstance(clazz))
                newList.add((T) o);
        }
        return newList;
    }

    @Override
    public long getLong(@NotNull String s) {
        Object o = getObject(s);
        return (o instanceof Long || o instanceof Double || o instanceof Integer || o instanceof Float || o instanceof Short || o instanceof Byte) ? (long) o : 0;
    }

    @NotNull
    @Override
    public Number getNumber(@NotNull String s) {
        return getDouble(s);
    }

    @Nullable
    @Override
    public Object getObject(@NotNull String s) {
        return contents.get(s);
    }

    @Override
    public short getShort(@NotNull String s) {
        Object o = getObject(s);
        return (o instanceof Short || o instanceof Byte) ? (short) o : 0;
    }

    @Nullable
    @Override
    public String getString(@NotNull String s) {
        Object o = getObject(s);
        return (o == null) ? null : o.toString();
    }

    @NotNull
    @Override
    public Number getWholeNumber(@NotNull String s) {
        return getLong(s);
    }

    @NotNull
    @Override
    public Configurable set(@NotNull String s, @NotNull Object o) {
        contents.put(s, o);
        return this;
    }

    private static final String INDENT = "  ";

    @Override
    public void saveToBufferedWriter(BufferedWriter bufferedWriter) {
        try {
            writeToBufferedWriter(bufferedWriter, getContents(), "");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void writeToBufferedWriter(BufferedWriter bufferedWriter, Map<?, ?> map, String indent) {
        map.forEach((var, val) -> {
            try {
                if (val instanceof Map) {
                    bufferedWriter.write(indent + var + ":\n");
                    writeToBufferedWriter(bufferedWriter, (Map<?, ?>) val, indent + INDENT);
                } else
                    bufferedWriter.write(indent + var + ": " + val.toString() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void setItem(@NotNull ItemStack item) {

    }

    @Override
    public ItemStack getItem(@NotNull String s) {
        FileEditor fileEditor = getFileEditor(s);
        if (fileEditor == null)
            return null;
        return new ItemStackParser(fileEditor).parse(fileEditor.getContents());
    }

    @Override
    public ItemStackParser getItemParser(@NotNull String s) {
        return new ItemStackParser(getFileEditor(s));
    }

    @Override
    public String toString() {
        return "DefaultFileEditor{" +
                "contents=" + contents +
                ", parent=" + parent +
                '}';
    }
}
