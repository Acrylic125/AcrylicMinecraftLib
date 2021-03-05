package com.acrylic.universal.files.parsers.variables;

import com.acrylic.universal.files.parsers.AbstractVariableParser;
import com.acrylic.universal.collection.IndexMapConsumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public final class VariableStore implements VariableParser {

    private final Map<String, Object> cached;

    public VariableStore(@NotNull AbstractVariableParser<?> variableParser, @Nullable IndexMapConsumer<String, Object> actionAsIterated) {
        this.cached = new HashMap<>();

        AtomicInteger index = new AtomicInteger(-1);
        variableParser.getVariableMap().forEach((s, configVariable) -> {
            int i = index.addAndGet(1);
            Object obj = configVariable.get();
            if (actionAsIterated != null)
                actionAsIterated.accept(i, s, obj);
            this.cached.put(s, obj);
        });
    }


    @Override
    public Object getObject(@NotNull String var) {
        return cached.get(var);
    }

}
