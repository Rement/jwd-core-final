package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.CreationOfMissionException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ApplicationMenuImpl implements ApplicationMenu {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String MAIN_MENU = "Please, select a section: \n 1: Operations with missions. \n 2: Operations with spaceships. \n 3: Operations with crew members." +
            "\n 4: Reinit \n 0:" + " Retire at NASSA";
    private static final String OPERATIONS_WITH_SPACESHIPS = " 1: Find spaceship by name. \n 2: Find spaceships by distance \n 3: Find all spaceships \n 4: Update spaceship \n 0: To main menu";
    private static final String OPERATIONS_WITH_CREW_MEMBERS = " 1: Find crew member by name. \n 2: Find crew members by rank \n 3: Find crew members by role" +
            " \n 4: Find all crew members \n 5: Update crew members \n 0: To main menu";
    private static final String OPERATIONS_WITH_MISSIONS = " 1: Create mission \n 2: Find mission by name. \n 3: Find missions by distance \n 4: Update missions \n 5: Find all missions \n 0: To main menu";
    private CrewService crewService = CrewServiceImpl.getInstance();
    private SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
    private MissionService missionService = MissionServiceImpl.getInstance();
    private Long id = 0L;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public ApplicationContext getApplicationContext() {
        return NassaContext.getInstance();
    }

    @Override
    public String printAvailableOptions() {
        return MAIN_MENU;
    }

    @Override
    public void handleUserInput() throws IOException, InvalidStateException {
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
                case 4:
                    NassaContext.getInstance().init();
                    break;
                case 0:
                    System.out.println("Do you want to delete file with missions?(y/n)");
                    SCANNER.nextLine();
                    String s = SCANNER.nextLine();
                    if (s.equals("y")) {
                        deleteFile();
                        break label;
                    } else {
                        break label;
                    }
                default:
                    System.out.println("Please, try again!");
            }
        }
    }

    private void operationsWithSpaceships() throws IOException {
        label:
        while (true) {
            System.out.println(OPERATIONS_WITH_SPACESHIPS);
            int input = SCANNER.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Please, input spaceship name");
                    SCANNER.nextLine();
                    System.out.println(spaceshipService.findSpaceshipByCriteria(new SpaceshipCriteria.SpaceshipCriteriaBuilder().byName(SCANNER.nextLine()).build()));
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
                    System.out.println("Please, input retired spaceship's name");
                    SCANNER.nextLine();
                    Spaceship spaceship = spaceshipService.findSpaceshipByCriteria(new SpaceshipCriteria.SpaceshipCriteriaBuilder().byName(SCANNER.nextLine()).build()).get();
                    System.out.println("Please, input new name");
                    String name = SCANNER.nextLine();
                    System.out.println("Please, input new distance");
                    Long newDistance = SCANNER.nextLong();
                    System.out.println(spaceshipService.updateSpaceshipDetails(new Spaceship(spaceship.getId(), name, newDistance, spaceship.getCrew())));
                    SCANNER.nextLine();
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
                    System.out.println(crewService.findCrewMemberByCriteria(new CrewMemberCriteria.CrewMemberCriteriaBuilder().byName(SCANNER.nextLine()).build()));
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
                    System.out.println("Please, input retired member's name");
                    SCANNER.nextLine();
                    CrewMember crewMember = crewService.findCrewMemberByCriteria(new CrewMemberCriteria.CrewMemberCriteriaBuilder().byName(SCANNER.nextLine()).build()).get();
                    System.out.println("Please, input new Role");
                    int role = SCANNER.nextInt();
                    SCANNER.nextLine();
                    System.out.println("Please, input new name");
                    String name = SCANNER.nextLine();
                    System.out.println("Please, input new Rank");
                    int rank = SCANNER.nextInt();
                    System.out.println(crewService.updateCrewMemberDetails(new CrewMember(crewMember.getId(), Role.resolveRoleById(role), name, Rank.resolveRankById(rank))));
                    SCANNER.nextLine();
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
                        System.out.println("Do you want to save the mission?(y/n)");
                        SCANNER.nextLine();
                        String s = SCANNER.nextLine();
                        System.out.println("Please, input mission name and distance!");
                        if (s.equals("y")) {
                            writeJSONFile(missionService.createMission(
                                    new FlightMission(++id, SCANNER.nextLine(), LocalDateTime.now(), LocalDateTime.now().plusDays(20), SCANNER.nextLong())));
                            SCANNER.nextLine();
                        } else if (s.equals("n")) {
                            missionService.createMission(
                                    new FlightMission(++id, SCANNER.nextLine(), LocalDateTime.now(), LocalDateTime.now().plusDays(20), SCANNER.nextLong()));
                        } else {
                            System.out.println("Bad input");
                            continue;
                        }
                    } catch (CreationOfMissionException | IOException e) {
                        e.printStackTrace();
                        id--;
                    }
                    break;
                case 2:
                    System.out.println("Please, input mission name!");
                    SCANNER.nextLine();
                    missionService.findMissionByCriteria(new FlightMissionCriteria.FlightMissionCriteriaBuilder().byName(SCANNER.nextLine()).build());
                    break;
                case 3:
                    System.out.println("Please, input mission distance!");
                    missionService.findAllMissionsByCriteria(new FlightMissionCriteria.FlightMissionCriteriaBuilder().byDistance(SCANNER.nextLong()).build())
                            .forEach(System.out::println);
                    SCANNER.nextLine();
                    break;
                case 4:
                    System.out.println("Coming soon");
                    break;
                case 5:
                    missionService.findAllMissions().forEach(System.out::println);
                    break;
                case 0:
                    break label;
                default:
                    System.out.println("Please, try again!");
            }
        }
    }

    private void writeJSONFile(FlightMission flightMission) throws IOException {
        File file = new File(ApplicationProperties.MAIN_PATH + ApplicationProperties.OUTPUT_ROOT_DIR + ApplicationProperties.MISSIONS_FILE_NAME);
        if (!file.exists()) {
            file.createNewFile();
        }
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(file, flightMission);
    }

    private void deleteFile() {
        File file = new File(ApplicationProperties.MAIN_PATH + ApplicationProperties.OUTPUT_ROOT_DIR + ApplicationProperties.MISSIONS_FILE_NAME);
        if (file.exists()) {
            file.deleteOnExit();
        }
    }
}
