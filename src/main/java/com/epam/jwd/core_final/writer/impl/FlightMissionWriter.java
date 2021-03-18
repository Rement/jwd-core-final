package com.epam.jwd.core_final.writer.impl;

import com.epam.jwd.core_final.Main;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.writer.BaseEntityWriter;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

public class FlightMissionWriter implements BaseEntityWriter<FlightMission> {
    private ApplicationProperties applicationProperties = new ApplicationProperties();
    private static FlightMissionWriter instance;
    Collection<FlightMission> flightMissions;

    private FlightMissionWriter() {
    }

    public static FlightMissionWriter getInstance() {
        if (instance == null) {
            instance = new FlightMissionWriter();
        }
        return instance;
    }

    @Override
    public void writeOneEntityToDatabase(FlightMission flightMission) {
        flightMissions = Main.getFlightMissions();
        flightMissions.add(flightMission);
        Main.setFlightMissions(flightMissions);
    }

    @Override
    public void writeAllToDatabase(List<String> baseEntity) {
    }

    @Override
    public void writeToJsonFile(FlightMission baseEntity) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(Paths.get(getOutputFileName()).toFile(), baseEntity);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getOutputFileName() {
        String crewFileName = applicationProperties.getMissionsFileName();
        String dirName = applicationProperties.getOutputRootDir();
        String dir = applicationProperties.getRootDir();
        return dir + "/" + dirName + "/" + crewFileName;
    }
}
