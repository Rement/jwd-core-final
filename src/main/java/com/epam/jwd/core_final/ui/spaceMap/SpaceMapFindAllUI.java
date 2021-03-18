package com.epam.jwd.core_final.ui.spaceMap;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.SpacemapService;
import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class SpaceMapFindAllUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_GREEN = "\u001B[32m";
    private static SpaceMapFindAllUI instance;
    private static Logger logger = new JwdLogger(SpaceMapGetDistanceUI.class.getName(), "slf4j");
    private SpacemapService spacemapService = SpacemapServiceImpl.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    private SpaceMapFindAllUI() {
    }

    public static SpaceMapFindAllUI getInstance() {
        if (instance == null) {
            instance = new SpaceMapFindAllUI();
        }
        return instance;
    }
    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

    @Override
    public Object printAvailableOptions() {
        System.out.println(ANSI_GREEN +"wait a minute. we're looking for everyone."+ANSI_RESET);
        return null;
    }

    @Override
    public Object handleUserInput(Object o) {
        List<Planet> planets = spacemapService.findAllPlanets();
        planets.stream().forEach(System.out::println);
        return null;
    }
}
