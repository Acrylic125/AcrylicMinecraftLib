package com.acrylic.universal.factory;

public interface AbstractFactory {

    EntityFactory getEntityFactory();

    UtilityFactory getUtilityFactory();

}
