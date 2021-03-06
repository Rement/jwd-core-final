package com.epam.jwd.core_final.cashcreator;

import com.epam.jwd.core_final.domain.AbstractBaseEntity;

import java.util.Collection;
import java.util.List;

public interface BaseEntityCollectionCreatorFromFile {
    public Collection<? extends AbstractBaseEntity> createFromFile(List<String> stringStreamFromFile);
}
