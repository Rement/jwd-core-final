package com.epam.jwd.core_final.ui.flightMission;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlightMissionWriteToJsonUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_GREEN = "\u001B[32m";
    private static FlightMissionWriteToJsonUI instance;
    private static Logger logger = new JwdLogger(FlightMissionWriteToJsonUI.class.getName(), "slf4j");
    private MissionService missionService = MissionServiceImpl.getInstance();
    private FlightMission flightMission = new FlightMission();
    private FlightMissionCriteria.Builder flightMissionCriteria = FlightMissionCriteria.newBuilder();
    private final Scanner scanner = new Scanner(System.in);

    private FlightMissionWriteToJsonUI() {
    }

    public static FlightMissionWriteToJsonUI getInstance() {
        if (instance == null) {
            instance = new FlightMissionWriteToJsonUI();
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
            FlightMission flightMission = flightMissionCriteria
                    .setName(name)
                    .build();
            logger.log(Level.INFO, "flightMission with name " + name + " tried to write to JSON ");
            missionService.writeMissionToJsonFile(flightMission);
        }
        return null;
    }
}
