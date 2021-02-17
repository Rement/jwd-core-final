package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Scanner;

import static com.epam.jwd.core_final.util.PrintUtil.printMsgToConsole;

public final class InputUtil {
    private static final Scanner SCANNER = new Scanner(new InputStreamReader(System.in));
    private InputUtil() { }

    public static int readInt(final String msg) {
        printMsgToConsole(msg);
        return SCANNER.nextInt();
    }

    public static long readLong(final String msg) {
        printMsgToConsole(msg);
        return SCANNER.nextLong();
    }

    public static String readLine(final String msg) {
        printMsgToConsole(msg);
        return SCANNER.next();
    }

    public static LocalDate readLocalDate(final String msg) {
        printMsgToConsole(msg);
        int year = readInt("Enter year:");
        int month = readInt("Enter month:");
        int day = readInt("Enter day:");
        return LocalDate.of(year, month, day);
    }

    public static FlightMission readMission(String msg) {
        printMsgToConsole(msg);

        String missionName = readLine("Enter mission name: ");

        return new FlightMission(
                missionName,
                readLocalDate("Enter start date: "),
                readLocalDate("Enter end date: "),
                readLong("Enter distance"),
                null,
                null
        );
    }

    public static FlightMissionCriteria readMissionCriteria(String msg) {
        printMsgToConsole(msg);

        String missionName = readLine("Enter name of the mission to update (or skip)");
        LocalDate startDate = readLocalDate("Enter start date: ");
        LocalDate endDate = readLocalDate("Enter end date: ");
        Long distance = readLong("Enter distance: ");

        return FlightMissionCriteria.builder()
                .withMissionName(missionName)
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withFlightDistance(distance)
                .build();
    }
}
