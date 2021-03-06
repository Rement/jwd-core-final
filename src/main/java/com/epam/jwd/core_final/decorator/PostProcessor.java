package com.epam.jwd.core_final.decorator;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.factory.EntityFactory;

public abstract class PostProcessor<T extends BaseEntity> implements EntityFactory<BaseEntity> {
    private BaseEntity baseEntity;

    public PostProcessor(BaseEntity baseEntity) {
        this.baseEntity = baseEntity;
    }

    @Override
    public BaseEntity create(Object... args) {
        return baseEntity;
    }
}
