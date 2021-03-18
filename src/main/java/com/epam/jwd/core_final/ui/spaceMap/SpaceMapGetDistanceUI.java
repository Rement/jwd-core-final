package com.epam.jwd.core_final.ui.spaceMap;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.PlanetCriteria;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.SpacemapService;
import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;

import java.util.Scanner;
import java.util.logging.Logger;

public class SpaceMapGetDistanceUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_GREEN = "\u001B[32m";
    private static SpaceMapGetDistanceUI instance;
    private static Logger logger = new JwdLogger(SpaceMapGetDistanceUI.class.getName(), "slf4j");
    private String nameFirstPlanet = null;
    private String nameSecondPlanet = null;
    private SpacemapService spacemapService = SpacemapServiceImpl.getInstance();
    private PlanetCriteria.Builder planetCriteria = PlanetCriteria.newBuilder();
    private final Scanner scanner = new Scanner(System.in);

    private SpaceMapGetDistanceUI() {
    }

    public static SpaceMapGetDistanceUI getInstance() {
        if (instance == null) {
            instance = new SpaceMapGetDistanceUI();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

    @Override
    public Object printAvailableOptions() {
        System.out.println(ANSI_GREEN + "you must enter name of two planets" + ANSI_RESET);
        return null;
    }

    @Override
    public Object handleUserInput(Object o) {
        System.out.println(ANSI_GREEN + "write name first planet. any string" + ANSI_RESET);

        if (scanner.hasNextLine()) {
            nameFirstPlanet = scanner.nextLine();
        }
        Planet firstPlanet = planetCriteria
                .setName(nameFirstPlanet)
                .build();
        System.out.println(ANSI_GREEN + "write name second planet. any string" + ANSI_RESET);
        if (scanner.hasNextLine()) {
            nameSecondPlanet = scanner.nextLine();
        }
        Planet secondPlanet = planetCriteria
                .setName(nameSecondPlanet)
                .build();
        if (firstPlanet == null || secondPlanet == null) {
            System.out.println(ANSI_GREEN + "you entered incorrect data" + ANSI_RESET);
        } else {
            Integer distance = spacemapService.getDistanceBetweenPlanets(firstPlanet, secondPlanet);
            System.out.println(distance);
        }
        return null;
    }
}
