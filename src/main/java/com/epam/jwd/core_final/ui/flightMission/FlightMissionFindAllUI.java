package com.epam.jwd.core_final.ui.flightMission;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;

import java.util.List;
import java.util.logging.Logger;

public class FlightMissionFindAllUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_RED = "\u001B[31m";
    public String ANSI_GREEN = "\u001B[32m";
    private static FlightMissionFindAllUI instance;
    private List<FlightMission> flightMissions;
    private static Logger logger = new JwdLogger(FlightMissionFindAllUI.class.getName(), "slf4j");
    private MissionService missionService = MissionServiceImpl.getInstance();
    private FlightMission flightMission = new FlightMission();
    private FlightMissionCriteria.Builder flightMissionCriteria = FlightMissionCriteria.newBuilder();

    private FlightMissionFindAllUI() {
    }

    public static FlightMissionFindAllUI getInstance() {
        if (instance == null) {
            instance = new FlightMissionFindAllUI();
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
        flightMissions = missionService.findAllMissions();
        flightMissions.stream().forEach(System.out::println);
        return null;
    }
}
