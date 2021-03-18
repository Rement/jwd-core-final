package com.epam.jwd.core_final.ui.crewMember;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.decorator.impl.PostProcessorImpl;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.logger.JwdLogger;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrewMemberCreateUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_RED = "\u001B[31m";
    public String ANSI_GREEN = "\u001B[32m";
    private static CrewMemberCreateUI instance;
    private CrewService crewService = CrewServiceImpl.getInstance();
    private CrewMemberCriteria.Builder crewMemberCriteria = CrewMemberCriteria.newBuilder();
    private static Logger logger = new JwdLogger(CrewMemberCreateUI.class.getName(), "slf4j");
    private Role role = null;
    private Rank rank = null;
    private String name = null;
    private final Scanner scanner = new Scanner(System.in);

    private CrewMemberCreateUI() {
    }

    public static CrewMemberCreateUI getInstance() {
        if (instance == null) {
            instance = new CrewMemberCreateUI();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

    @Override
    public Object printAvailableOptions() {
        System.out.println(ANSI_RED + "you must enter the data to create a crewMember" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "       name should be any string" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "       role should be any number from 1 to 4" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "       rank should be number from 1 to 4" + ANSI_RESET);
        return null;
    }

    @Override
    public Object handleUserInput(Object o) {
        System.out.println(ANSI_GREEN + "write name. any string" + ANSI_RESET);
        if (scanner.hasNextLine()) {
            name = scanner.nextLine();
        }
        System.out.println(ANSI_GREEN + "write role. any number from 1 to 4" + ANSI_RESET);
        if (scanner.hasNextInt()) {
            int roleId = scanner.nextInt();
            if (roleId <= 4 && roleId >= 1) {
                role = Role.resolveRoleById(roleId);
            }
        }
        System.out.println(ANSI_GREEN + "write rank. any number from 1 to 4" + ANSI_RESET);
        if (scanner.hasNextInt()) {
            int rankId = scanner.nextInt();
            if (rankId <= 4 && rankId >= 1) {
                rank = Rank.resolveRankById(rankId);
            }
        }
        if (name == null || role == null || rank == null) {
            System.out.println(ANSI_GREEN + "you entered incorrect data" + ANSI_RESET);
        } else {
            CrewMember crewMember = crewMemberCriteria
                    .setRole(role)
                    .setRank(rank)
                    .setReadyForNextMission(true)
                    .setName(name)
                    .build();
            CrewMember createdCrewMember = null;
            try {
                createdCrewMember = crewService.createCrewMember(crewMember);
                logger.log(Level.INFO, "created entity: " + createdCrewMember);
                if (createdCrewMember == null) {
                    System.out.println(ANSI_GREEN + "such crewMember already exists" + ANSI_RESET);
                } else {
                    System.out.println("crewMember was created " + createdCrewMember);
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
                System.out.println("something went wrong. try again");
            }

        }
        return null;
    }
}
