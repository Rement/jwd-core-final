package com.epam.jwd.core_final.writer;

import com.epam.jwd.core_final.domain.*;

import java.util.List;

public interface BaseEntityWriter<T extends BaseEntity>{
    void writeOneEntityToDatabase(T baseEntity);
    void writeAllToDatabase(List<String> baseEntity);
    void writeToJsonFile(T baseEntity);
}
