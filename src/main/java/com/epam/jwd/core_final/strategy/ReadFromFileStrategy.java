package com.epam.jwd.core_final.strategy;

import java.io.FileNotFoundException;

public interface ReadFromFileStrategy {
    void readFromFile(String path) throws FileNotFoundException;
}