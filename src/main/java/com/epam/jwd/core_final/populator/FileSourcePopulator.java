package com.epam.jwd.core_final.populator;

import com.epam.jwd.core_final.domain.BaseEntity;

import java.io.IOException;
import java.util.Collection;

public interface FileSourcePopulator {
    Collection<? extends BaseEntity> readResources(String filepath) throws IOException;
}
