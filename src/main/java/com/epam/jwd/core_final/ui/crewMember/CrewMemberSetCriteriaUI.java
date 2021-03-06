package com.epam.jwd.core_final.ui.crewMember;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.decorator.impl.PostProcessorImpl;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

import java.util.Scanner;

public class CrewMemberSetCriteriaUI implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_RED = "\u001B[31m";
    public String ANSI_GREEN = "\u001B[32m";
    private static CrewMemberSetCriteriaUI instance;
    private CrewService crewService = CrewServiceImpl.getInstance();
    private final Scanner scanner = new Scanner(System.in);
    private Object object;

    private CrewMemberSetCriteriaUI() {
    }

    public static CrewMemberSetCriteriaUI getInstance() {
        if (instance == null) {
            instance = new CrewMemberSetCriteriaUI();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

    @Override
    public Object printAvailableOptions() {
        System.out.println(ANSI_RED + "what do you want to enter?" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1 - id" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2 - name" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "any letter to enter the crewmember menu" + ANSI_RESET);
        return null;
    }

    @Override
    public Object handleUserInput(Object o) {
        if (scanner.hasNextInt()) {
            Object intFromConsole = scanner.nextInt();
            switch ((int) intFromConsole) {
                case 1 -> crewService.findCrewMemberByCriteria((Criteria<? extends CrewMember>) object);
                case 2 -> crewService.findCrewMemberByCriteria((Criteria<? extends CrewMember>) object);
                default -> System.out.println("works only from 1 to 2");
            }
        }
        return null;
    }

    public String handleName() {
        System.out.println("write name");
        String name = scanner.nextLine();
        return name;
    }

    public Integer handId() {
        System.out.println("write id");
        Integer id = scanner.nextInt();
        return id;
    }
}