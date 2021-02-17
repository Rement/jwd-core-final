package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.CreationOfMissionException;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ApplicationMenuImpl implements ApplicationMenu {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String MAIN_MENU = "Please, select a section: \n 1: Operations with missions. \n 2: Operations with spaceships. \n 3: Operations with crew members. \n 0:" +
            " Retire at NASSA";
    private static final String OPERATIONS_WITH_SPACESHIPS = " 1: Find spaceship by name. \n 2: Find spaceships by distance \n 3: Find all spaceships \n 4: Update spaceship \n 0: To main menu";
    private static final String OPERATIONS_WITH_CREW_MEMBERS = " 1: Find crew member by name. \n 2: Find crew members by rank \n 3: Find crew members by role" +
            " \n 4: Find all crew members \n 5: Update crew members \n 0: To main menu";
    private static final String OPERATIONS_WITH_MISSIONS = " 1: Create mission \n 2: Find mission by name. \n 3: Find missions by distance \n 4: Update missions \n 0: To main menu";
    private static CrewService crewService = new CrewServiceImpl();
    private static SpaceshipService spaceshipService = new SpaceshipServiceImpl();
    private static MissionService missionService = new MissionServiceImpl();

    @Override
    public ApplicationContext getApplicationContext() {
        return NassaContext.getInstance();
    }

    @Override
    public String printAvailableOptions() {
        return MAIN_MENU;
    }

    @Override
    public void handleUserInput() {
        label:
        while (true) {
            System.out.println(printAvailableOptions());
            int input = SCANNER.nextInt();
            switch (input) {
                case 1:
                    operationsWithFlightMissions();
                    break;
                case 2:
                    operationsWithSpaceships();
                    break;
                case 3:
                    operationsWithCrewMembers();
                    break;
                case 0:
                    break label;
                default:
                    System.out.println("Please, try again!");
            }
        }
    }

    private void operationsWithSpaceships() {
        label:
        while (true) {
            System.out.println(OPERATIONS_WITH_SPACESHIPS);
            int input = SCANNER.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Please, input spaceship name");
                    SCANNER.nextLine();
                    spaceshipService.findSpaceshipByCriteria(new SpaceshipCriteria.SpaceshipCriteriaBuilder().byName(SCANNER.nextLine()).build());
                    break;
                case 2:
                    System.out.println("Please, input spaceship flight distance");
                    Long dist = SCANNER.nextLong();
                    spaceshipService.findAllSpaceshipsByCriteria(new SpaceshipCriteria.SpaceshipCriteriaBuilder().byFlightDistance(dist).build())
                            .forEach(System.out::println);
                    SCANNER.nextLine();
                    break;
                case 3:
                    spaceshipService.findAllSpaceships().forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("Coming soon");
                    break;
                case 0:
                    break label;
                default:
                    System.out.println("Please, try again!");
            }
        }
    }

    private void operationsWithCrewMembers() {
        label:
        while (true) {
            System.out.println(OPERATIONS_WITH_CREW_MEMBERS);
            int input = SCANNER.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Please, input crew member name");
                    SCANNER.nextLine();
                    crewService.findCrewMemberByCriteria(new CrewMemberCriteria.CrewMemberCriteriaBuilder().byName(SCANNER.nextLine()).build());
                    break;
                case 2:
                    System.out.println("Please, input Rank id");
                    crewService.findAllCrewMembersByRank(new CrewMemberCriteria.CrewMemberCriteriaBuilder().byRank(Rank.resolveRankById(SCANNER.nextInt())).build())
                            .forEach(System.out::println);
                    SCANNER.nextLine();
                    break;
                case 3:
                    System.out.println("Please, input Role id");
                    crewService.findAllCrewMembersByCriteria(new CrewMemberCriteria.CrewMemberCriteriaBuilder().byRole(Role.resolveRoleById(SCANNER.nextInt())).build())
                            .forEach(System.out::println);
                    SCANNER.nextLine();
                    break;
                case 4:
                    crewService.findAllCrewMembers().forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Coming soon");
                    break;
                case 0:
                    break label;
                default:
                    System.out.println("Please, try again!");
            }
        }
    }

    private void operationsWithFlightMissions() {
        label:
        while (true) {
            System.out.println(OPERATIONS_WITH_MISSIONS);
            int input = SCANNER.nextInt();
            switch (input) {
                case 1:
                    try {
                        System.out.println("Please, input mission name and distance!");
                        SCANNER.nextLine();
                        missionService.createMission(new FlightMission(SCANNER.nextLine(), LocalDateTime.now(), LocalDateTime.now().plusDays(20), SCANNER.nextLong()));
                    } catch (CreationOfMissionException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    missionService.findMissionByCriteria(new FlightMissionCriteria.FlightMissionCriteriaBuilder().build());
                    break;
                case 3:
                    missionService.findAllMissions().forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("Coming soon");
                    break;
                case 0:
                    break label;
                default:
                    System.out.println("Please, try again!");
            }
        }
    }
}
