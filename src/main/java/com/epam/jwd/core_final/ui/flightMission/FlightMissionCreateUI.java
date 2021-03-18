package com.epam.jwd.core_final.ui.flightMission;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.PlanetCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlightMissionCreateUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_RED = "\u001B[31m";
    public String ANSI_GREEN = "\u001B[32m";
    private static FlightMissionCreateUI instance;
    private static Logger logger = new JwdLogger(FlightMissionCreateUI.class.getName(), "slf4j");
    private MissionService missionService = MissionServiceImpl.getInstance();
    private FlightMission flightMission = new FlightMission();
    private FlightMissionCriteria.Builder flightMissionCriteria = FlightMissionCriteria.newBuilder();
    private SpaceshipCriteria.Builder spaceshipCriteria = SpaceshipCriteria.newBuilder();
    private CrewMemberCriteria.Builder crewMemberCriteria = CrewMemberCriteria.newBuilder();
    private PlanetCriteria.Builder planetCriteria = PlanetCriteria.newBuilder();
    private String missionsName = null;
    private LocalDate startDate = null;
    private LocalDate endDate = null;
    private Long distance = null;
    private Spaceship assignedSpaceShift = null;
    private List<CrewMember> assignedCrew = null;
    private MissionResult missionResult = null;
    private Planet fromPlanet = null;
    private Planet toPlanet = null;
    private int ear = 0;
    private int month = 0;
    private int day = 0;
    private final Scanner scanner = new Scanner(System.in);

    private FlightMissionCreateUI() {
    }

    public static FlightMissionCreateUI getInstance() {
        if (instance == null) {
            instance = new FlightMissionCreateUI();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

    @Override
    public Object printAvailableOptions() {
        System.out.println(ANSI_RED + "you must enter the data to create a FlightMission" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "       name should be any string" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "       start date should be in the format year,  month, day" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "       end date should be in the format year,  month, day" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "        flightDistance must be greater than zero" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "       assign a spaceship. the list can be viewed in chapter of spaceship" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "       assign a crew members.the list can be viewed in chapter of crew members" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "       from what planet. the list can be viewed in chapter of planet" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "       to what planet. the list can be viewed in chapter of planet" + ANSI_RESET);
        return null;
    }

    @Override
    public Object handleUserInput(Object o) {
        System.out.println(ANSI_GREEN + "write name. any string" + ANSI_RESET);
        if (scanner.hasNextLine()) {
            missionsName = scanner.nextLine();
        }
        System.out.println(ANSI_GREEN + "write startDate" + ANSI_RESET);
        startDate = setLocalDate();
        System.out.println(ANSI_GREEN + "write startDate" + ANSI_RESET);
        endDate = setLocalDate();
        System.out.println(ANSI_GREEN + "write distance" + ANSI_RESET);

        System.out.println(ANSI_GREEN + "write distance. it must be greater than zero" + ANSI_RESET);
        if (scanner.hasNextInt()) {
            int nextInt = scanner.nextInt();
            if (nextInt > 0) {
                distance = Long.valueOf(nextInt);
            }
        }

        assignedSpaceShift = setSpaceship();
        assignedCrew = setCrewMembers();
        fromPlanet = setPlanet();
        toPlanet = setPlanet();

        if (missionsName == null || startDate == null || endDate == null || assignedSpaceShift == null || assignedCrew == null) {
            System.out.println(ANSI_GREEN + "you entered incorrect data" + ANSI_RESET);
        } else {
            FlightMission flightMission = flightMissionCriteria
                    .setStartDate(startDate)
                    .setEndDate(endDate)
                    .setDistance(distance)
                    .setSpaceship(assignedSpaceShift)
                    .setCrewList(assignedCrew)
                    .setFromPlanet(fromPlanet)
                    .setToPlanet(toPlanet)
                    .setMissionResult(MissionResult.PLANNED)
                    .setName(missionsName)
                    .build();
            FlightMission createdFlightMission = missionService.createMission(flightMission);
            logger.log(Level.INFO, "created entity: " + createdFlightMission);
            if (createdFlightMission == null) {
                System.out.println(ANSI_GREEN + "such FlightMission already exists" + ANSI_RESET);
            } else {
                System.out.println("FlightMission was created " + createdFlightMission);
            }
        }
        return null;
    }

    private List<CrewMember> setCrewMembers() {
        List<CrewMember> crewMembers = new ArrayList<>();
        CrewMember crewMember = null;
        for (int i = 0; i < 4; i++) {
            System.out.println(ANSI_GREEN + "write crew member name" + ANSI_RESET);
            if (scanner.hasNext()) {
                String nameCrewMember = scanner.nextLine();
                crewMember = crewMemberCriteria
                        .setName(nameCrewMember)
                        .build();
            }
            crewMembers.add(crewMember);
        }
        return crewMembers;
    }

    private Spaceship setSpaceship() {
        Spaceship spaceship = null;
        System.out.println(ANSI_GREEN + "write name spaceship" + ANSI_RESET);
        if (scanner.hasNext()) {
            String nameSpaceShip = scanner.nextLine();
            spaceship = spaceshipCriteria
                    .setName(nameSpaceShip)
                    .build();
        }
        return spaceship;
    }

    private Planet setPlanet() {
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

    private LocalDate setLocalDate() {
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
