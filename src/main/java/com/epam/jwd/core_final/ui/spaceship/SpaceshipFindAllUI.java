package com.epam.jwd.core_final.ui.spaceship;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import java.util.List;
import java.util.logging.Logger;

public class SpaceshipFindAllUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_GREEN = "\u001B[32m";
    private static SpaceshipFindAllUI instance;
    private List<Spaceship> spaceships;
    private SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
    private static Logger logger = new JwdLogger(SpaceshipFindAllUI.class.getName(), "slf4j");

    private SpaceshipFindAllUI() {
    }

    public static SpaceshipFindAllUI getInstance() {
        if (instance == null) {
            instance = new SpaceshipFindAllUI();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

    @Override
    public Object printAvailableOptions() {
        System.out.println(ANSI_GREEN + "wait a minute. we're looking for everyone." + ANSI_RESET);
        return null;
    }

    @Override
    public Object handleUserInput(Object o) {
        spaceships = spaceshipService.findAllSpaceships();
        spaceships.stream().forEach(System.out::println);
        return null;
    }
}
