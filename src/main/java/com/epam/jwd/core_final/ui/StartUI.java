package com.epam.jwd.core_final.ui;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.ui.crewMember.CrewMemberStartUI;
import com.epam.jwd.core_final.ui.flightMission.FlightMissionStartUI;
import com.epam.jwd.core_final.ui.spaceMap.SpaceMapStartUI;
import com.epam.jwd.core_final.ui.spaceship.SpaceshipStartUI;

import java.util.Scanner;

public class StartUI extends Thread implements ApplicationMenu {
    public String ANSI_RESET = "\u001B[0m";
    public String ANSI_RED = "\u001B[31m";
    public String ANSI_GREEN = "\u001B[32m";
    private Object object;

    @Override
    public void run() {
        super.run();
        printAvailableOptions();
        handleUserInput(object);
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return null;
    }

    @Override
    public Object printAvailableOptions() {
        System.out.println(ANSI_RED + "do you want to work with" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1 - CrewMember" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2 - SpaceShip" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "3 - SpaceMap" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "4 - FlightMission" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "any letter to exit the program" + ANSI_RESET);
        return null;
    }

    @Override
    public Object handleUserInput(Object o) {
        ApplicationMenu o1 = null;
        Scanner scanner = new Scanner(System.in);
        do {
            if (scanner.hasNextInt()) {
                Object intFromConsole = scanner.nextInt();
                switch ((int) intFromConsole) {
                    case 1 -> o1 = CrewMemberStartUI.getInstance();
                    case 2 -> o1 = SpaceshipStartUI.getInstance();
                    case 3 -> o1 = SpaceMapStartUI.getInstance();
                    case 4 -> o1 = new FlightMissionStartUI();
                    default -> o1 = null;
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
