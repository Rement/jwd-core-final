package com.epam.jwd.core_final.ui;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.PlanetCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.SpacemapService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;

import java.util.Scanner;

public class SpaceMapUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_RED = "\u001B[31m";
    public String ANSI_GREEN = "\u001B[32m";
    private SpacemapService spacemapService = SpacemapServiceImpl.getInstance();
    private Planet planet = new Planet();
    private PlanetCriteria.Builder crewMemberCriteria = PlanetCriteria.newBuilder();

    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

    @Override
    public Object printAvailableOptions() {
        System.out.println(ANSI_RED + "what do you want to do?" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1 - get random planet" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2 - get distance between planets" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "any letter to enter the main menu" + ANSI_RESET);
        return Planet.class;
    }

    @Override
    public Object handleUserInput(Object o) {
        Scanner scanner = new Scanner(System.in);
        do {
            if (scanner.hasNextInt()) {
                Object intFromConsole = scanner.nextInt();
                switch ((int) intFromConsole) {
                    case 1 -> spacemapService.getRandomPlanet();
                    case 2 -> spacemapService.getDistanceBetweenPlanets(planet, planet);
                    default -> System.out.println("works only from 1 to 4");
                }
            }
            printAvailableOptions();
        } while (scanner.hasNextInt());
        return null;
    }
}
