package com.epam.jwd.core_final.ui.spaceship;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import java.util.Scanner;

public class SpaceshipStartUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_RED = "\u001B[31m";
    public String ANSI_GREEN = "\u001B[32m";
    private SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
    private SpaceshipCriteria.Builder crewMemberCriteria = SpaceshipCriteria.newBuilder();
    private static SpaceshipStartUI instance;

    private SpaceshipStartUI() {
    }

    public static SpaceshipStartUI getInstance() {
        if (instance == null) {
            instance = new SpaceshipStartUI();
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
        System.out.println(ANSI_GREEN + "1 - find all Spaceships" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2 - find one Spaceship by name" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "3 - create Spaceship" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "4 - delete Spaceship" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "5 - found by criteria" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "any letter to enter the main menu" + ANSI_RESET);
        return Spaceship.class;
    }

    @Override
    public Object handleUserInput(Object o) {
        Scanner scanner = new Scanner(System.in);
        ApplicationMenu o1 = null;
        do {
            if (scanner.hasNextInt()) {
                Object intFromConsole = scanner.nextInt();
                switch ((int) intFromConsole) {
                    case 1 -> o1 = SpaceshipFindAllUI.getInstance();
                    case 2 -> o1 = SpaceshipFindByCriteriaUI.getInstance();
                    case 3 -> o1 = SpaceshipCreateUI.getInstance();
                    case 4 -> o1 = SpaceshipDeleteUI.getInstance();
                    case 5 -> o1 = SpaceshipFindAllByCriteriaUI.getInstance();
                    default -> o1 = null;
                }
            }
            if (o1 == null) {
                System.out.println("works only from 1 to 4");
            } else {
                Object selectedObject = o1.printAvailableOptions();
                o1.handleUserInput(selectedObject);
            }
            printAvailableOptions();
        } while (scanner.hasNextInt());
        return null;
    }
}
