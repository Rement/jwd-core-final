package com.epam.jwd.core_final.ui.spaceship;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpaceshipFindAllByCriteriaUI implements ApplicationMenu {
    public String ANSI_GREEN = "\u001B[32m";
    public String ANSI_RESET = "\u001B[0m";
    private static SpaceshipFindAllByCriteriaUI instance;
    private SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
    private SpaceshipCriteria spaceshipCriteria = new SpaceshipCriteria();
    private final Scanner scanner = new Scanner(System.in);
    private static Logger logger = new JwdLogger(SpaceshipFindAllByCriteriaUI.class.getName(), "slf4j");

    private SpaceshipFindAllByCriteriaUI() {
    }

    public static SpaceshipFindAllByCriteriaUI getInstance() {
        if (instance == null) {
            instance = new SpaceshipFindAllByCriteriaUI();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

    @Override
    public Object printAvailableOptions() {
        System.out.println(ANSI_GREEN + "write a criteria" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1 - distance" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2 - is ready for next mission" + ANSI_RESET);
        return null;
    }

    @Override
    public Object handleUserInput(Object o) {
        if (scanner.hasNextInt()) {
            Object intFromConsole = scanner.nextInt();
            switch ((int) intFromConsole) {
                case 1 -> spaceshipCriteria = getCriteriaByDistance();
                case 2 -> spaceshipCriteria = getReadyForNextMission();
                default -> System.out.println("works only from 1 to 2");
            }
        }
        List<Spaceship> spaceships = spaceshipService.findAllSpaceshipsByCriteria(spaceshipCriteria);
        if (spaceships.isEmpty()) {
            System.out.println("no one meets the specified requirements");
        } else {
            System.out.println(spaceships);
        }
        return null;
    }

    private SpaceshipCriteria getCriteriaByDistance() {
        System.out.println(ANSI_GREEN + "write a distance which you want to sort" + ANSI_RESET);
        if (scanner.hasNextInt()) {
            spaceshipCriteria.setFlightDistance((long) scanner.nextInt());
            logger.log(Level.INFO, "the user entered a criteria " + spaceshipCriteria.getFlightDistance());
            return spaceshipCriteria;
        }
        return null;
    }

    private SpaceshipCriteria getReadyForNextMission() {
        System.out.println(ANSI_GREEN + "is ready for next mission" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1 - true" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2 - false" + ANSI_RESET);

        if (scanner.hasNextInt()) {
            Object intFromConsole = scanner.nextInt();
            switch ((int) intFromConsole) {
                case 1 -> spaceshipCriteria.setReadyForNextMissions(true);
                case 2 -> spaceshipCriteria.setReadyForNextMissions(false);
                default -> System.out.println("works only from 1 to 2");
            }
            logger.log(Level.INFO, "the user entered a criteria " + spaceshipCriteria.getFlightDistance());
            return spaceshipCriteria;
        }
        return null;
    }
}
