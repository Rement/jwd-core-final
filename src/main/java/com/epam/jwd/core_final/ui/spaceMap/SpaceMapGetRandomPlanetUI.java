package com.epam.jwd.core_final.ui.spaceMap;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.SpacemapService;
import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;

import java.util.logging.Logger;

public class SpaceMapGetRandomPlanetUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_GREEN = "\u001B[32m";
    private static SpaceMapGetRandomPlanetUI instance;
    private static Logger logger = new JwdLogger(SpaceMapGetRandomPlanetUI.class.getName(), "slf4j");
    private SpacemapService spacemapService = SpacemapServiceImpl.getInstance();

    private SpaceMapGetRandomPlanetUI() {
    }

    public static SpaceMapGetRandomPlanetUI getInstance() {
        if (instance == null) {
            instance = new SpaceMapGetRandomPlanetUI();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

    @Override
    public Object printAvailableOptions() {
        System.out.println(ANSI_GREEN + "wait a minute, we are looking for the best planet for you" + ANSI_RESET);
        return null;
    }

    @Override
    public Object handleUserInput(Object o) {
        System.out.println(spacemapService.getRandomPlanet());
        return null;
    }
}
