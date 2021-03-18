package com.epam.jwd.core_final.ui.crewMember;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.CrewMember;

import java.util.Scanner;

public class CrewMemberStartUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_RED = "\u001B[31m";
    public String ANSI_GREEN = "\u001B[32m";
    private final CrewMemberFindByCriteriaUI crewMemberFindByCriteriaUI = CrewMemberFindByCriteriaUI.getInstance();
    private final CrewMemberFindAllUI crewMemberFindAllUI = CrewMemberFindAllUI.getInstance();
    private final CrewMemberCreateUI createCrewMember = CrewMemberCreateUI.getInstance();
    private final CrewMemberDeleteUI deleteCrewMember = CrewMemberDeleteUI.getInstance();
    private static CrewMemberStartUI instance;
    private final Scanner scanner = new Scanner(System.in);

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
        System.out.println(ANSI_GREEN + "2 - find one CrewMember by name" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "3 - create CrewMember" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "4 - delete CrewMember" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "5 - find all by criteria" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "any letter to enter the main menu" + ANSI_RESET);
        return CrewMember.class;
    }

    @Override
    public Object handleUserInput(Object o) {
        ApplicationMenu o1 = null;
        do {
            if (scanner.hasNextInt()) {
                Object intFromConsole = scanner.nextInt();
                switch ((int) intFromConsole) {
                    case 1 -> o1 = CrewMemberFindAllUI.getInstance();
                    case 2 -> o1 = CrewMemberFindByCriteriaUI.getInstance();
                    case 3 -> o1 = CrewMemberCreateUI.getInstance();
                    case 4 -> o1 = CrewMemberDeleteUI.getInstance();
                    case 5 -> o1 = CrewMemberFindAllByCriteriaUI.getInstance();
                    default -> System.out.println("works only from 1 to 5");
                }
            }
            if (o1 == null) {
                System.out.println("works only from 1 to 4");
            } else {
                Object selectedObject = o1.printAvailableOptions();
                o1.handleUserInput(selectedObject);
            }
            printAvailableOptions();
        } while (scanner.hasNextInt());
        return null;
    }
}
