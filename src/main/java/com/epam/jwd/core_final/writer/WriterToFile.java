package com.epam.jwd.core_final.writer;

import com.epam.jwd.core_final.domain.*;

public interface WriterToFile<T extends BaseEntity>{
    public void writeToFile(T baseEntity);
}
