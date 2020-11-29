package com.acrylic.universal.files.parsers;

public interface Parser<F, T> {

    F getParserFrom();

    F serialize(T toParse);

    T parse(F parseFrom);

    default T parse() {
        return parse(getParserFrom());
    }

}
