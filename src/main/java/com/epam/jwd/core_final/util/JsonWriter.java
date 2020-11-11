package com.epam.jwd.core_final.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public final class JsonWriter {
    private JsonWriter() { }

    public static void writeCollectionToJson(Collection<?> collection, String filepath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filepath);

        mapper.writeValue(new File(filepath), collection);
    }
}
