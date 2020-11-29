package com.acrylic.universal.files.parsers.variables;

import com.acrylic.universal.files.parsers.exceptions.VariableParserException;
import com.acrylic.universal.files.parsers.exceptions.VariableParserMismatchException;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public interface VariableParser {

    Object getObject(@NotNull String var);

    default Object processObject(@NotNull String var) {
        Object o = getObject(var);
        if (o == null)
            throw new VariableParserException(var + " is not a valid variable.", var);
        return o;
    }

    default long getWholeNumber(@NotNull String var) throws VariableParserMismatchException {
        Object c = processObject(var);
        if (c instanceof Long)
            return (long) c;
        throw new VariableParserMismatchException(var, "Long");
    }

    default int getInteger(@NotNull String var) throws VariableParserMismatchException {
        return (int) getWholeNumber(var);
    }

    default short getShort(@NotNull String var) throws VariableParserMismatchException {
        return (short) getWholeNumber(var);
    }

    default byte getByte(@NotNull String var) throws VariableParserMismatchException {
        return (byte) getWholeNumber(var);
    }

    default double getNonWholeNumber(@NotNull String var) throws VariableParserMismatchException {
        Object c = processObject(var);
        if (c instanceof Double)
            return (double) c;
        throw new VariableParserMismatchException(var, "Double");
    }

    default float getFloat(@NotNull String var) throws VariableParserMismatchException {
        return (float) getNonWholeNumber(var);
    }

    default boolean getBoolean(@NotNull String var) throws VariableParserMismatchException {
        Object c = processObject(var);
        if (c instanceof Boolean)
            return (boolean) c;
        throw new VariableParserMismatchException(var, "Boolean");
    }

    default String getString(@NotNull String var) throws VariableParserMismatchException {
        Object c = getObject(var);
        return (c == null) ? var : c.toString();
    }

}
