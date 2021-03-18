package com.epam.jwd.core_final.ui.spaceship;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpaceshipDeleteUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_GREEN = "\u001B[32m";
    private static SpaceshipDeleteUI instance;
    private SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
    private SpaceshipCriteria.Builder spaceshipCriteria = SpaceshipCriteria.newBuilder();
    private static Logger logger = new JwdLogger(SpaceshipDeleteUI.class.getName(), "slf4j");
    private final Scanner scanner = new Scanner(System.in);

    private SpaceshipDeleteUI() {
    }

    public static SpaceshipDeleteUI getInstance() {
        if (instance == null) {
            instance = new SpaceshipDeleteUI();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

    @Override
    public Object printAvailableOptions() {
        System.out.println(ANSI_GREEN + "write name" + ANSI_RESET);
        return null;
    }

    @Override
    public Object handleUserInput(Object o) {
        if (scanner.hasNext()) {
            String name = scanner.nextLine();
            Spaceship spaceship = spaceshipCriteria
                    .setName(name)
                    .build();
            spaceshipService.deleteSpaceship(spaceship);
            logger.log(Level.INFO, "crewMember with name " + name + " tried to delete ");
        }
        return null;
    }
}
