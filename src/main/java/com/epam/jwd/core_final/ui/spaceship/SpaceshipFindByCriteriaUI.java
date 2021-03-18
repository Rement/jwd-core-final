package com.epam.jwd.core_final.ui.spaceship;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpaceshipFindByCriteriaUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_GREEN = "\u001B[32m";
    private static SpaceshipFindByCriteriaUI instance;
    private SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
    private SpaceshipCriteria.Builder spaceshipCriteria = SpaceshipCriteria.newBuilder();
    private Criteria<Spaceship> criteria;
    private final Scanner scanner = new Scanner(System.in);
    private static Logger logger = new JwdLogger(SpaceshipFindByCriteriaUI.class.getName(), "slf4j");

    private SpaceshipFindByCriteriaUI() {
    }

    public static SpaceshipFindByCriteriaUI getInstance() {
        if (instance == null) {
            instance = new SpaceshipFindByCriteriaUI();
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
            Object stringFromConsole = scanner.nextLine();
            spaceshipCriteria.setName((String) stringFromConsole).build();
            criteria = new SpaceshipCriteria(spaceshipCriteria);
            logger.log(Level.INFO, "the user entered a name ");
            Optional crewMember = spaceshipService.findSpaceshipByCriteria(criteria);
            System.out.println(crewMember);
        }
        return null;
    }
}
