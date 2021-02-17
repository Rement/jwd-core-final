package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.util.PropertyReaderUtil;

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
public final class ApplicationProperties {
    public static final String MAIN_PATH = "./src/main/resources/input/";
    public static final String INPUT_ROOT_DIR = PropertyReaderUtil.applicationProperties.get("inputRootDir");
    public static final String OUTPUT_ROOT_DIR = PropertyReaderUtil.applicationProperties.get("outputRootDir");
    public static final String CREW_FILE_NAME = PropertyReaderUtil.applicationProperties.get("crewFileName");
    public static final String MISSIONS_FILE_NAME = PropertyReaderUtil.applicationProperties.get("missionsFileName");
    public static final String SPACESHIPS_FILE_NAME = PropertyReaderUtil.applicationProperties.get("spaceshipsFileName");
    public static final Integer FILE_REFRESH_RATE = Integer.parseInt(PropertyReaderUtil.applicationProperties.get("fileRefreshRate"));
    public static final String DATE_TIME_FORMAT = PropertyReaderUtil.applicationProperties.get("dateTimeFormat");
}
