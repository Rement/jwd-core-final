package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static com.epam.jwd.core_final.util.LogUtil.logError;
import static java.nio.file.Files.createDirectories;

public final class InitEnvironmentUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitEnvironmentUtil.class);
    private InitEnvironmentUtil() { }

    public static void init(final String folderPrefix, final ApplicationProperties properties) {
        try {
            createDirectories(Path.of(folderPrefix + "/" + properties.getOutputRootDir()));
            new File(folderPrefix
                    + "/" + properties.getOutputRootDir()
                    + "/" + properties.getMissionsFileName()).createNewFile();
        } catch (IOException e) {
            logError(LOGGER, "Couldn't create output directory", e);
        }
    }
}
