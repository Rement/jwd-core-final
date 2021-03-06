package com.epam.jwd.core_final.writer.impl;

import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.writer.WriterToFile;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.nio.file.Paths;

public class WriteFlightMissionToJsonFile implements WriterToFile<FlightMission> {
    private ApplicationProperties applicationProperties = new ApplicationProperties();
    private String dir = applicationProperties.getRootDir();
    String fileNameWhole = null;
    private String dirName;
    String fileName;
    private static WriteFlightMissionToJsonFile instance;

    private WriteFlightMissionToJsonFile() {
    }

    public static WriteFlightMissionToJsonFile getInstance() {
        if (instance == null) {
            instance = new WriteFlightMissionToJsonFile();
        }
        return instance;
    }

    @Override
    public void writeToFile(FlightMission flightMission) {
        fileName = applicationProperties.getCrewFileName();
        dirName = applicationProperties.getOutputRootDir();
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(Paths.get(dir + "/" + dirName + "/" + fileName).toFile(), flightMission);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
