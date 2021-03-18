package com.epam.jwd.core_final.ui.flightMission;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.PlanetCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class FlightMissionFindAllByCriteriaUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_GREEN = "\u001B[32m";
    private static FlightMissionFindAllByCriteriaUI instance;
    private static Logger logger = new JwdLogger(FlightMissionFindAllByCriteriaUI.class.getName(), "slf4j");
    private MissionService missionService = MissionServiceImpl.getInstance();
    private FlightMissionCriteria flightMissionCriteria = new FlightMissionCriteria();
    private PlanetCriteria.Builder planetCriteria = PlanetCriteria.newBuilder();
    private final Scanner scanner = new Scanner(System.in);


    private FlightMissionFindAllByCriteriaUI() {
    }

    public static FlightMissionFindAllByCriteriaUI getInstance() {
        if (instance == null) {
            instance = new FlightMissionFindAllByCriteriaUI();
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
        System.out.println(ANSI_GREEN + "1 - start day" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2 - end date" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "3 - distance" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "4 - from planet" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "5 - to planet" + ANSI_RESET);
        return null;
    }

    @Override
    public Object handleUserInput(Object o) {
        if (scanner.hasNextInt()) {
            Object intFromConsole = scanner.nextInt();
            switch ((int) intFromConsole) {
                case 1 -> flightMissionCriteria = getCriteriaByStartDay();
                case 2 -> flightMissionCriteria = getCriteriaByEndDay();
                case 3 -> flightMissionCriteria = getCriteriaByDistatce();
                case 4 -> flightMissionCriteria = getCriteriaByFirstPlanet();
                case 5 -> flightMissionCriteria = getCriteriaBySecondPlanet();
                default -> System.out.println("works only from 1 to 5");
            }
        }
        List<FlightMission> flightMissions = missionService.findAllMissionsByCriteria(flightMissionCriteria);
        if (flightMissions.isEmpty()) {
            System.out.println("no one meets the specified requirements");
        } else {
            System.out.println(flightMissions);
        }
        return null;
    }

    private FlightMissionCriteria getCriteriaByFirstPlanet() {
        flightMissionCriteria.setFromPlanet(getPlanet());
        return flightMissionCriteria;
    }

    private FlightMissionCriteria getCriteriaBySecondPlanet() {
        flightMissionCriteria.setToPlanet(getPlanet());
        return flightMissionCriteria;
    }

    private Planet getPlanet() {
        Planet planet = null;
        System.out.println(ANSI_GREEN + "write name Planet" + ANSI_RESET);
        if (scanner.hasNext()) {
            String namePlanet = scanner.nextLine();
            planet = planetCriteria
                    .setName(namePlanet)
                    .build();
        }
        return planet;
    }

    private FlightMissionCriteria getCriteriaByDistatce() {
        Long distance = null;
        if (scanner.hasNextInt()) {
            int nextInt = scanner.nextInt();
            if (nextInt > 0) {
                distance = Long.valueOf(nextInt);
                flightMissionCriteria.setDistance(distance);
            }
        }
        return flightMissionCriteria;
    }

    private FlightMissionCriteria getCriteriaByEndDay() {
        System.out.println(ANSI_GREEN + "write endDate" + ANSI_RESET);
        LocalDate startDate = setLocalDate();
        flightMissionCriteria.setEndDate(startDate);
        return flightMissionCriteria;
    }

    private FlightMissionCriteria getCriteriaByStartDay() {
        System.out.println(ANSI_GREEN + "write startDate" + ANSI_RESET);
        LocalDate startDate = setLocalDate();
        flightMissionCriteria.setStartDate(startDate);
        return flightMissionCriteria;
    }

    private LocalDate setLocalDate() {
        int ear = 0;
        int month = 0;
        int day = 0;
        System.out.println(ANSI_GREEN + "write year from 1000 to 3000" + ANSI_RESET);
        if (scanner.hasNextInt()) {
            int nextInt = scanner.nextInt();
            if (nextInt > 1000 && nextInt < 3000) {
                ear = nextInt;
            } else {
                ear = 0;
            }
        }
        System.out.println(ANSI_GREEN + "write month from 1 to 12" + ANSI_RESET);
        if (scanner.hasNextInt()) {
            int nextInt = scanner.nextInt();
            if (nextInt >= 1 && nextInt <= 12) {
                month = nextInt;
            } else {
                month = 0;
            }
        }
        System.out.println(ANSI_GREEN + "write month from 1 to 31" + ANSI_RESET);
        if (scanner.hasNextInt()) {
            int nextInt = scanner.nextInt();
            if (nextInt >= 1 && nextInt <= 31) {
                day = nextInt;
            } else {
                day = 0;
            }
        }
        return LocalDate.of(ear, month, day);
    }
}
