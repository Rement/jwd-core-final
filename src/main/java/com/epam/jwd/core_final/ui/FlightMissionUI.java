package com.epam.jwd.core_final.ui;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;

import java.util.Scanner;

public class FlightMissionUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_RED = "\u001B[31m";
    public String ANSI_GREEN = "\u001B[32m";
    private MissionService missionService = MissionServiceImpl.getInstance();
    private FlightMission flightMission = new FlightMission();
    private FlightMissionCriteria.Builder findAllCrewMembers = FlightMissionCriteria.newBuilder();
    private Criteria criteria;

    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

    @Override
    public Object printAvailableOptions() {
        System.out.println(ANSI_RED + "what do you want to do?" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1 - find all FlightMissions" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2 - find one FlightMission by criteria" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "3 - create FlightMission" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "4 - delete FlightMission" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "any letter to enter the main menu" + ANSI_RESET);
        return FlightMission.class;
    }

    @Override
    public Object handleUserInput(Object o) {
        Scanner scanner = new Scanner(System.in);
        do {
            if (scanner.hasNextInt()) {
                Object intFromConsole = scanner.nextInt();
                switch ((int) intFromConsole) {
                    case 1 -> missionService.findAllMissions();
                    case 2 -> missionService.findMissionByCriteria(criteria);
                    case 3 -> missionService.createMission(flightMission);
                    case 4 -> missionService.deleteFlightMission(flightMission);
                    default -> System.out.println("works only from 1 to 4");
                }
            }
            printAvailableOptions();
        } while (scanner.hasNextInt());
        return null;
    }
}
