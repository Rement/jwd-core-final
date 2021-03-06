package com.epam.jwd.core_final.writer.impl;

import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.writer.WriterToFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WriteSpaceshipToFile implements WriterToFile<Spaceship> {
    private ApplicationProperties applicationProperties = new ApplicationProperties();
    private String dir = applicationProperties.getRootDir();
    String fileNameWhole = null;
    private String dirName;
    String fileName;
    private static WriteSpaceshipToFile instance;

    private WriteSpaceshipToFile() {
    }

    public static WriteSpaceshipToFile getInstance() {
        if (instance == null) {
            instance = new WriteSpaceshipToFile();
        }
        return instance;
    }
    @Override
    public void writeToFile(Spaceship spaceship) {
        fileName = applicationProperties.getSpaceshipsFileName();
        dirName = applicationProperties.getInputRootDir();
        fileNameWhole = dir + "/" + dirName + "/" + fileName;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileNameWhole, true))) {
            Map<Role, Short> crewMembers = spaceship.getCrew();
            bufferedWriter.write(spaceship.getName() + ";" + spaceship.getFlightDistance() + ";");
            for (Map.Entry entry : crewMembers.entrySet()) {
                bufferedWriter.write(entry.getKey() + ":" + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
