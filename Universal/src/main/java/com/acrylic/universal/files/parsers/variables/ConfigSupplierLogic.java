package com.acrylic.universal.files.parsers.variables;

@FunctionalInterface
public interface ConfigSupplierLogic<T> {

    AbstractConfigValue<T> getConfigValue(String parse);

}
