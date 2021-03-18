package com.epam.jwd.core_final.ui.spaceMap;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.PlanetCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.SpacemapService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;

import java.util.Scanner;
import java.util.logging.Logger;

public class SpaceMapStartUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_RED = "\u001B[31m";
    public String ANSI_GREEN = "\u001B[32m";
    private static SpaceMapStartUI instance;
    private static Logger logger = new JwdLogger(SpaceMapStartUI.class.getName(), "slf4j");

    private SpaceMapStartUI() {
    }

    public static SpaceMapStartUI getInstance() {
        if (instance == null) {
            instance = new SpaceMapStartUI();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

    @Override
    public Object printAvailableOptions() {
        System.out.println(ANSI_RED + "what do you want to do?" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1 - get random planet" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2 - get distance between planets" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "3 - find all planets" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "any letter to enter the main menu" + ANSI_RESET);
        return Planet.class;
    }

    @Override
    public Object handleUserInput(Object o) {
        Scanner scanner = new Scanner(System.in);
        ApplicationMenu o1 = null;
        do {
            if (scanner.hasNextInt()) {
                Object intFromConsole = scanner.nextInt();
                switch ((int) intFromConsole) {
                    case 1 -> o1 = SpaceMapGetRandomPlanetUI.getInstance();
                    case 2 -> o1 = SpaceMapGetDistanceUI.getInstance();
                    case 3-> o1 = SpaceMapFindAllUI.getInstance();
                    default -> o1 = null;
                }
            }
            if (o1 == null) {
                System.out.println(ANSI_GREEN + "works only from 1 to 2" + ANSI_RESET);
            } else {
                Object selectedObject = o1.printAvailableOptions();
                o1.handleUserInput(selectedObject);
            }
            printAvailableOptions();
        } while (scanner.hasNextInt());
        return null;
    }
}
