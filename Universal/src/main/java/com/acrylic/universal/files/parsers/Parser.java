package com.acrylic.universal.files.parsers;

public interface Parser<F, T> {

    F serialize(T toParse);

    T parse(F parseFrom);

}
