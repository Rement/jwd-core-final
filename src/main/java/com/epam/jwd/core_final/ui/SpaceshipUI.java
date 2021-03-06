package com.epam.jwd.core_final.ui;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import java.util.Scanner;

public class SpaceshipUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_RED = "\u001B[31m";
    public String ANSI_GREEN = "\u001B[32m";
    private SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
    private Spaceship spaceship = new Spaceship();
    private Criteria criteria;
    private SpaceshipCriteria.Builder crewMemberCriteria = SpaceshipCriteria.newBuilder();

    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

    @Override
    public Object printAvailableOptions() {
        System.out.println(ANSI_RED + "what do you want to do?" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1 - find all Spaceships" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2 - find one Spaceship by criteria" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "3 - create Spaceship" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "4 - delete Spaceship" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "any letter to enter the main menu" + ANSI_RESET);
        return Spaceship.class;
    }

    @Override
    public Object handleUserInput(Object o) {
        Scanner scanner = new Scanner(System.in);
        do {
            if (scanner.hasNextInt()) {
                Object intFromConsole = scanner.nextInt();
                switch ((int) intFromConsole) {
                    case 1 -> spaceshipService.findAllSpaceships();
                    case 2 -> spaceshipService.findSpaceshipByCriteria(criteria);
                    case 3 -> spaceshipService.createSpaceship(spaceship);
                    case 4 -> spaceshipService.deleteSpaceship(spaceship);
                    default -> System.out.println("works only from 1 to 4");
                }
            }
            printAvailableOptions();
        } while (scanner.hasNextInt());
        return null;
    }
}
