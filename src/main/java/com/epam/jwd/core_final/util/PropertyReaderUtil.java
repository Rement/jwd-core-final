package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import static java.lang.Integer.parseInt;

public final class PropertyReaderUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyReaderUtil.class);
    private static final Properties properties = new Properties();

    private PropertyReaderUtil() {
    }

    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */
    public static ApplicationProperties loadProperties() {
        final String propertiesFileName = "src/main/resources/application.properties";

        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(propertiesFileName))) {
            properties.load(reader);
        } catch (IOException e) {
            LOGGER.error("Can't load application.properties!", e);
        }

        // I think Builder looks more beautiful
        // However it's not necessary here
        return ApplicationProperties.builder()
                .withInputRootDir(properties.getProperty("inputRootDir"))
                .withOutputRootDir(properties.getProperty("outputRootDir"))
                .withCrewFileName(properties.getProperty("crewFileName"))
                .withMissionsFileName(properties.getProperty("missionsFileName"))
                .withFileRefreshRate(parseInt(properties.getProperty("fileRefreshRate")))
                .withSpaceshipsFileName(properties.getProperty("spaceshipsFileName"))
                .withDateTimeFormat(properties.getProperty("dateTimeFormat"))
                .build();
    }
}
