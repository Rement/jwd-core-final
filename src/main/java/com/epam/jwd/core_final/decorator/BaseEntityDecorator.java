package com.epam.jwd.core_final.decorator;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.factory.EntityFactory;

public abstract class BaseEntityDecorator implements EntityFactory<BaseEntity> {
    private EntityFactory factory;

    public BaseEntityDecorator(EntityFactory factory) {
        this.factory = factory;
    }

    public BaseEntity create(Object... args){
        return factory.create(args);
    }
}
