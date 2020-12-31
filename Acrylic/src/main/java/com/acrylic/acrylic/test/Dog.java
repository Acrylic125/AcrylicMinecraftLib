package com.acrylic.acrylic.test;

import com.acrylic.universal.files.configloader.ConfigValue;
import com.acrylic.universal.files.configloader.Configurable;

@Configurable(
        filePath = "test/doggo.yml",
        plugin = "Acrylic",
        resourcePlugin = "Acrylic",
        resourcePath = "animals/animaltest.yml",
        loadWithParentClass = true
)
public class Dog extends Animal {

    @ConfigValue(path = "say")
    private String say = "BARK!";

    public Dog(String name) {
        super(name);
    }

    @Override
    public String say() {
        return say;
    }

}
