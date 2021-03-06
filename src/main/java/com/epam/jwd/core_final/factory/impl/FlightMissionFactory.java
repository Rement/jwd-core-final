package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightMissionFactory implements EntityFactory<FlightMission> {
    private FlightMission flightMission = null;
    private Spaceship spaceship = null;
    private FlightMissionCriteria.Builder flightMissionCriteria = FlightMissionCriteria.newBuilder();
    private static FlightMissionFactory instance;

    private SpaceshipCriteria.Builder spaceshipCriteria = SpaceshipCriteria.newBuilder();

    private FlightMissionFactory() {
    }

    public static FlightMissionFactory getInstance() {
        if (instance == null) {
            instance = new FlightMissionFactory();
        }
        return instance;
    }

    @Override
    public FlightMission create(Object... args) {
        Map<Role, Short> crew = new HashMap<>();
        crew.put(Role.COMMANDER, (short) 2);
        spaceship = spaceshipCriteria
                .setCrew(crew)
                .setFlightDistance(23L)
                .setReadyForNextMission(true)
                .setName("ppppp")
                .build();
        List<CrewMember> assignedCrew = new ArrayList<>();
        assignedCrew.add(new CrewMember());
        flightMission = flightMissionCriteria
                .setMissionsName("ssss")
                .setStartDate(LocalDate.of(1914, 7, 28))
                .setEndDate(LocalDate.of(1984, 03, 23))
                .setDistance(1223213L)
                .setSpaceship(spaceship)
                .setCrewList(assignedCrew)
                .setMissionResult(MissionResult.FAILED)
                .build();
        return flightMission;
    }
}
