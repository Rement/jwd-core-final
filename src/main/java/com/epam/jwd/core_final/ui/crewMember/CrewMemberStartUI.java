package com.epam.jwd.core_final.ui.crewMember;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

import java.util.Scanner;

public class CrewMemberStartUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_RED = "\u001B[31m";
    public String ANSI_GREEN = "\u001B[32m";
    private CrewService crewService = CrewServiceImpl.getInstance();
    private CrewMember crewMember = new CrewMember();
    private static CrewMemberStartUI instance;

    private CrewMemberStartUI() {
    }

    public static CrewMemberStartUI getInstance() {
        if (instance == null) {
            instance = new CrewMemberStartUI();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

    @Override
    public Object printAvailableOptions() {
        System.out.println(ANSI_RED + "what do you want to do?" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1 - find all CrewMembers" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2 - find one CrewMember by criteria" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "3 - create CrewMember" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "4 - delete CrewMember" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "any letter to enter the main menu" + ANSI_RESET);
        return CrewMember.class;
    }

    @Override
    public Object handleUserInput(Object o) {
        Scanner scanner = new Scanner(System.in);
        do {
            if (scanner.hasNextInt()) {
                Object intFromConsole = scanner.nextInt();
                switch ((int) intFromConsole) {
                    case 1 -> crewService.findAllCrewMembers();
                    case 2 -> CrewMemberSetCriteriaUI.getInstance();
                    case 3 -> crewService.createCrewMember(crewMember);
                    case 4 -> crewService.deleteCrewMember(crewMember);
                    default -> System.out.println("works only from 1 to 4");
                }
            }
            printAvailableOptions();
        } while (scanner.hasNextInt());
        return null;
    }
}
