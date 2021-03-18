package com.epam.jwd.core_final.ui.spaceship;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpaceshipCreateUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_RED = "\u001B[31m";
    public String ANSI_GREEN = "\u001B[32m";
    private static SpaceshipCreateUI instance;
    private SpaceshipCriteria.Builder spaceshipCriteria = SpaceshipCriteria.newBuilder();
    private SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
    private static Logger logger = new JwdLogger(SpaceshipCreateUI.class.getName(), "slf4j");
    private Map<Role, Short> crew = null;
    private Long flightDistance = null;
    private String name = null;
    private final Scanner scanner = new Scanner(System.in);

    private SpaceshipCreateUI() {
    }

    public static SpaceshipCreateUI getInstance() {
        if (instance == null) {
            instance = new SpaceshipCreateUI();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

    @Override
    public Object printAvailableOptions() {
        System.out.println(ANSI_RED + "you must enter the data to create a spaceship" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "       name should be any string" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "       flightDistance must be greater than zero" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "       crew should be roleId and number of people" + ANSI_RESET);
        return null;
    }

    @Override
    public Object handleUserInput(Object o) {
        System.out.println(ANSI_GREEN + "write name. any string" + ANSI_RESET);

        if (scanner.hasNextLine()) {
            name = scanner.nextLine();
        }
        System.out.println(ANSI_GREEN + "write flightDistance. it must be greater than zero" + ANSI_RESET);
        if (scanner.hasNextInt()) {
            int nextInt = scanner.nextInt();
            if (nextInt < 0) {
                flightDistance = Long.valueOf(nextInt);
            }
        }
        crew = setCrew();

        if (name == null || crew == null || flightDistance == null) {
            System.out.println(ANSI_GREEN + "you entered incorrect data" + ANSI_RESET);
        } else {
            Spaceship spaceship = spaceshipCriteria
                    .setFlightDistance(flightDistance)
                    .setCrew(crew)
                    .setReadyForNextMission(true)
                    .setName(name)
                    .build();
            try {
                Spaceship createdSpaceship = spaceshipService.createSpaceship(spaceship);
                logger.log(Level.INFO, "created entity: " + createdSpaceship);
                if (createdSpaceship == null) {
                    System.out.println(ANSI_GREEN + "such crewMember already exists" + ANSI_RESET);
                } else {
                    System.out.println(ANSI_GREEN + "crewMember was created " + createdSpaceship + ANSI_RESET);
                }
            }catch (RuntimeException e){
                e.printStackTrace();
                System.out.println("something went wrong. try again");
            }
        }
        return null;
    }


    private Map<Role, Short> setCrew() {
        System.out.println(ANSI_GREEN + "write crew" + ANSI_RESET);
        Map<Role, Short> newCrew = null;
        for (int i = 0; i < 4; i++) {
            System.out.println(ANSI_GREEN + "write " + i + " part of crew" + ANSI_RESET);
            short countCrew = 0;
            Role role = null;
            System.out.println(ANSI_GREEN + "write id if role. any number from 1 to 4" + ANSI_RESET);
            if (scanner.hasNextInt()) {
                int nextInt = scanner.nextInt();
                if (nextInt <= 4 && nextInt >= 1) {
                    role = Role.resolveRoleById(nextInt);
                }
            }
            System.out.println(ANSI_GREEN + "write number of crew. any number from 1 to 10" + ANSI_RESET);
            if (scanner.hasNextInt()) {
                int nextInt = scanner.nextInt();
                if (nextInt <= 10 && nextInt >= 1) {
                    countCrew = (short) nextInt;
                }
            }
            newCrew.put(role, countCrew);
        }
        return newCrew;
    }
}
