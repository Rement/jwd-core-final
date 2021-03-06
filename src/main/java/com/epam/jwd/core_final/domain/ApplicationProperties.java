package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.util.Properties;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public class ApplicationProperties {
    private String inputRootDir;
    private String outputRootDir;
    private String crewFileName;
    private String missionsFileName;
    private String spaceshipsFileName;
    private String spaceMapFileName;
    private Integer fileRefreshRate;
    private String dateTimeFormat;
    private String rootDir = "src/main/resources/";
    private String indexForCrewFileName= ";";
    private String indexForSpaceshipFile = "\n";
    private String indexForSpaceMapFileName = " ";
    PropertyReaderUtil propertyReaderUtil = PropertyReaderUtil.getInstance();
    Properties properties = propertyReaderUtil.getProperties();

    public String getInputRootDir() {
        inputRootDir = properties.getProperty("inputRootDir");
        return inputRootDir;
    }

    public String getOutputRootDir() {
        outputRootDir=properties.getProperty("outputRootDir");
        return outputRootDir;
    }

    public String getCrewFileName() {
        crewFileName = properties.getProperty("crewFileName");
        return crewFileName;
    }

    public String getMissionsFileName() {
        return missionsFileName;
    }

    public String getSpaceshipsFileName() {
        spaceshipsFileName = properties.getProperty("spaceshipsFileName");
        return spaceshipsFileName;
    }

    public String getSpaceMapFileName(){
        spaceMapFileName=properties.getProperty("spaceMapFileName");
        return spaceMapFileName;
    }

    public Integer getFileRefreshRate() {
        return fileRefreshRate;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public String getRootDir() {
        return rootDir;
    }

    public String getIndexForCrewFileName() {
        return indexForCrewFileName;
    }

    public String getIndexForSpaceshipFileName() {
        return indexForSpaceshipFile;
    }

    public String getIndexForSpaceMapFileName(){return indexForSpaceMapFileName;}
}
