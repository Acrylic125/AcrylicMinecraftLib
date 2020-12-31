package com.acrylic.acrylic.test;

import com.acrylic.universal.files.configloader.ConfigValue;

public abstract class Animal {

    @ConfigValue(path = "main-dog-name")
    public static String MAIN_DOG_NAME = "GayBoy";

    @ConfigValue(path = "name")
    private String name;
    @ConfigValue(path = "age")
    private int age = 10;

    public Animal(String name) {
        this.name = name;
    }

    public abstract String say();

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
