package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {
    Method[] methodsCrewMember = CrewMember.class.getDeclaredMethods();
    Method[] methodsMissionService = MissionService.class.getDeclaredMethods();
    Method[] methodsSpaceshipService = SpaceshipService.class.getDeclaredMethods();


    ApplicationContext getApplicationContext();

    default Object printAvailableOptions() {
        Arrays.stream(methodsCrewMember)
                .map(Method::toString)
                .map(s -> s.substring(3))
                .forEach(System.out::println);


        Arrays.stream(methodsMissionService).forEach(System.out::println);
        Arrays.stream(methodsSpaceshipService).forEach(System.out::println);
        System.out.println("print something");
        return null;
    }

    default Object handleUserInput(Object o) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextInt();
        return null;
    }
}
