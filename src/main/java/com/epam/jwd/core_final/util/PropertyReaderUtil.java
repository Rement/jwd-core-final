package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class PropertyReaderUtil {

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

    public static Map<String, String> applicationProperties = new HashMap<>();

    public static void loadProperties() throws FileNotFoundException {
        final String propertiesFileName = "./src/main/resources/application.properties";
        InputStream inputStream = new FileInputStream(propertiesFileName);
        try {
            properties.load(inputStream);
            applicationProperties.put("inputRootDir",properties.getProperty("inputRootDir"));
            applicationProperties.put("outputRootDir",properties.getProperty("outputRootDir"));
            applicationProperties.put("crewFileName",properties.getProperty("crewFileName"));
            applicationProperties.put("missionsFileName",properties.getProperty("missionsFileName"));
            applicationProperties.put("spaceshipsFileName",properties.getProperty("spaceshipsFileName"));
            applicationProperties.put("fileRefreshRate",properties.getProperty("fileRefreshRate"));
            applicationProperties.put("dateTimeFormat",properties.getProperty("dateTimeFormat"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
