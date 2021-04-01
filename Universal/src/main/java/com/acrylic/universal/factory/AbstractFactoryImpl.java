package com.acrylic.universal.factory;

public class AbstractFactoryImpl implements AbstractFactory {

    private final EntityFactory entityFactory = new EntityFactoryImpl();
    private final UtilityFactory utilityFactory = new UtilityFactoryImpl();

    @Override
    public EntityFactory getEntityFactory() {
        return entityFactory;
    }

    @Override
    public UtilityFactory getUtilityFactory() {
        return utilityFactory;
    }
}
