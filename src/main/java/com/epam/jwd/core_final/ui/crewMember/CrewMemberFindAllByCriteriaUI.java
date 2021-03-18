package com.epam.jwd.core_final.ui.crewMember;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;


import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrewMemberFindAllByCriteriaUI implements ApplicationMenu {
    public String ANSI_GREEN = "\u001B[32m";
    public String ANSI_RESET = "\u001B[0m";
    private static CrewMemberFindAllByCriteriaUI instance;
    private CrewService crewService = CrewServiceImpl.getInstance();
    private CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
    private final Scanner scanner = new Scanner(System.in);
    private static Logger logger = new JwdLogger(CrewMemberFindAllByCriteriaUI.class.getName(), "slf4j");

    private CrewMemberFindAllByCriteriaUI() {
    }

    public static CrewMemberFindAllByCriteriaUI getInstance() {
        if (instance == null) {
            instance = new CrewMemberFindAllByCriteriaUI();
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
        System.out.println(ANSI_GREEN + "1 - role" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2 - rank" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "3 - is ready for next mission" + ANSI_RESET);
        return null;
    }

    @Override
    public Object handleUserInput(Object o) {
        if (scanner.hasNextInt()) {
            Object intFromConsole = scanner.nextInt();
            switch ((int) intFromConsole) {
                case 1 -> crewMemberCriteria = getCriteriaByRole();
                case 2 -> crewMemberCriteria = getCriteriaByRank();
                case 3 -> crewMemberCriteria = getReadyForNextMission();
                default -> System.out.println("works only from 1 to 2");
            }
        }
        List<CrewMember> crewMembers = crewService.findAllCrewMembersByCriteria(crewMemberCriteria);
        if (crewMembers.isEmpty()){
            System.out.println("no one meets the specified requirements");
        }else {
        System.out.println(crewMembers);}
        return null;
    }

    private CrewMemberCriteria getCriteriaByRank() {
        System.out.println(ANSI_GREEN + "write a rank" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1 - TRAINEE" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2 - SECOND_OFFICER" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "3 -  FIRST_OFFICER" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "4 - CAPTAIN" + ANSI_RESET);

        if (scanner.hasNextInt()) {
            Rank rank = null;
            Object intFromConsole = scanner.nextInt();
            switch ((int) intFromConsole) {
                case 1 -> rank = Rank.resolveRankById(1);
                case 2 -> rank = Rank.resolveRankById(2);
                case 3 -> rank = Rank.resolveRankById(3);
                case 4 -> rank = Rank.resolveRankById(4);
                default -> System.out.println("works only from 1 to 4");
            }
            crewMemberCriteria.setRank(rank);
            logger.log(Level.INFO, "the user entered a criteria " + crewMemberCriteria.getRank());
            return crewMemberCriteria;
        }
        return null;
    }

    private CrewMemberCriteria getCriteriaByRole() {
        System.out.println(ANSI_GREEN + "write a role" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1 - MISSION_SPECIALIST" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2 - FLIGHT_ENGINEER" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "3 - PILOT" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "4 - COMMANDER" + ANSI_RESET);

        if (scanner.hasNextInt()) {
            Role role = null;
            Object intFromConsole = scanner.nextInt();
            switch ((int) intFromConsole) {
                case 1 -> role = Role.resolveRoleById(1);
                case 2 -> role = Role.resolveRoleById(2);
                case 3 -> role = Role.resolveRoleById(3);
                case 4 -> role = Role.resolveRoleById(4);
                default -> System.out.println("works only from 1 to 4");
            }
            crewMemberCriteria.setRole(role);
            logger.log(Level.INFO, "the user entered a criteria " + crewMemberCriteria.getRole());
            return crewMemberCriteria;
        }
        return null;
    }

    private CrewMemberCriteria getReadyForNextMission() {
        System.out.println(ANSI_GREEN + "is ready for next mission" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1 - true" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2 - false" + ANSI_RESET);

        if (scanner.hasNextInt()) {
            Object intFromConsole = scanner.nextInt();
            switch ((int) intFromConsole) {
                case 1 -> crewMemberCriteria.setReadyForNextMissions(true);
                case 2 -> crewMemberCriteria.setReadyForNextMissions(false);
                default -> System.out.println("works only from 1 to 2");
            }
            logger.log(Level.INFO, "the user entered a criteria " + crewMemberCriteria.getReadyForNextMissions());
            return crewMemberCriteria;
        }
        return null;
    }
}