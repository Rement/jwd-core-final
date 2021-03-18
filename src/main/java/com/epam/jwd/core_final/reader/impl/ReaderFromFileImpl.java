package com.epam.jwd.core_final.reader.impl;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.reader.ReaderFromFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReaderFromFileImpl implements ReaderFromFile {
    private ApplicationProperties applicationProperties = new ApplicationProperties();
    private String dir = applicationProperties.getRootDir();
    private String dirName = applicationProperties.getInputRootDir();
    String fileNameWhole = null;
    private List<String> stringStreamFromFile = null;
    String fileName = null;
    String delimiter = null;

    private static ReaderFromFileImpl instance;

    private ReaderFromFileImpl() {
    }

    public static ReaderFromFileImpl getInstance() {
        if (instance == null) {
            instance = new ReaderFromFileImpl();
        }
        return instance;
    }

    @Override
    public synchronized List<String> readFromFile(Class tClass) {
        if (tClass.equals(CrewMember.class)) {
            fileName = applicationProperties.getCrewFileName();
            delimiter = applicationProperties.getIndexForCrewFileName();
        }
        if (tClass.equals(Spaceship.class)) {
            fileName = applicationProperties.getSpaceshipsFileName();
            delimiter = applicationProperties.getIndexForSpaceshipFileName();
        }
        if (tClass.equals(Planet.class)) {
            fileName = applicationProperties.getSpaceMapFileName();
            delimiter = applicationProperties.getIndexForSpaceMapFileName();
        }
        fileNameWhole = dir + "/" + dirName + "/" + fileName;

        List<String> stringStreamFromFile = null;

     try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileNameWhole))) {
            while (bufferedReader.readLine() != null) {
                stringStreamFromFile = bufferedReader
                        .lines()
                        .flatMap(s -> Stream.of(s.split(delimiter)))
                        .map(String::valueOf)
                        .collect(Collectors.toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringStreamFromFile;
    }
}
