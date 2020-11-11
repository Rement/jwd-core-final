package com.epam.jwd.core_final.scheduledjob;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.exception.InvalidStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import static com.epam.jwd.core_final.util.LogUtil.logDebug;
import static com.epam.jwd.core_final.util.LogUtil.logError;

public class FileRefreshJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileRefreshJob.class);
    private static FileRefreshJob job;
    private ApplicationContext context;
    private ApplicationProperties properties;
    private File fileCrew;
    private File fileSpaceship;

    private long lastModifiedCrew;
    private long lastModifiedSpaceship;


    public static FileRefreshJob getInstance() {
        if (job == null) {
            job = new FileRefreshJob();
        }
        return job;
    }

    private FileRefreshJob() {
        context = NassaContext.getInstance();
        properties = context.getApplicationProperties();
        fileCrew = new File(properties.getInputRootDir() + properties.getCrewFileName());
        fileSpaceship = new File(properties.getInputRootDir()+ properties.getSpaceshipsFileName());
        lastModifiedCrew = fileCrew.lastModified();
        lastModifiedSpaceship = fileSpaceship.lastModified();
    }

    public void perform() throws InvalidStateException {
        //Didn't know where to put it
        context.init();
        LOGGER.info("Starting file refresh job...");

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                logDebug(LOGGER, "Started FileRefreshJob");

                if (fileCrew.lastModified() != lastModifiedCrew || fileSpaceship.lastModified() != lastModifiedSpaceship) {
                    lastModifiedCrew = fileCrew.lastModified();
                    lastModifiedSpaceship = fileSpaceship.lastModified();

                    try {
                        context.init();
                    } catch (InvalidStateException e) {
                        logError(LOGGER, "Can't refresh input files!", e);
                    }
                }
            }
        };

        LOGGER.info("Started file refresh job!");
        timer.schedule(task, 0, properties.getFileRefreshRate());
    }
}
