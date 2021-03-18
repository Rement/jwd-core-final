package com.epam.jwd.core_final.writer.impl;

import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.writer.BaseEntityWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SpaceshipWriter implements BaseEntityWriter<Spaceship> {
    private ApplicationProperties applicationProperties = new ApplicationProperties();
    private static SpaceshipWriter instance;

    private SpaceshipWriter() {
    }

    public static SpaceshipWriter getInstance() {
        if (instance == null) {
            instance = new SpaceshipWriter();
        }
        return instance;
    }

    @Override
    public void writeOneEntityToDatabase(Spaceship spaceship) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getInputFileName(), true))) {
            Map<Role, Short> crewMembers = spaceship.getCrew();
            bufferedWriter.write(spaceship.getName() + ";" + spaceship.getFlightDistance() + ";");
            for (Map.Entry entry : crewMembers.entrySet()) {
                bufferedWriter.write(entry.getKey() + ":" + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeAllToDatabase(List<String> baseEntity) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getInputFileName(), true))) {
            for (String s : baseEntity) {
                bufferedWriter.write(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToJsonFile(Spaceship baseEntity) {

    }

    private String getInputFileName() {
        String fileName = applicationProperties.getCrewFileName();
        String dirName = applicationProperties.getInputRootDir();
        String dir = applicationProperties.getRootDir();
        return dir + "/" + dirName + "/" + fileName;
    }
}
