package com.epam.jwd.core_final.ui.flightMission;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;

import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlightMissionFindByCriteriaUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_GREEN = "\u001B[32m";
    private static FlightMissionFindByCriteriaUI instance;
    private static Logger logger = new JwdLogger(FlightMissionFindByCriteriaUI.class.getName(), "slf4j");
    private MissionService missionService = MissionServiceImpl.getInstance();
    private Criteria<FlightMission> criteria;
    private FlightMissionCriteria.Builder flightMissionCriteria = FlightMissionCriteria.newBuilder();
    private final Scanner scanner = new Scanner(System.in);

    private FlightMissionFindByCriteriaUI() {
    }

    public static FlightMissionFindByCriteriaUI getInstance() {
        if (instance == null) {
            instance = new FlightMissionFindByCriteriaUI();
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
            flightMissionCriteria.setName((String) stringFromConsole).build();
            criteria = new FlightMissionCriteria(flightMissionCriteria);
            logger.log(Level.INFO, "the user entered a name ");
            Optional flightMission = missionService.findMissionByCriteria(criteria);
            System.out.println(flightMission);
        }
        return null;
    }
}
