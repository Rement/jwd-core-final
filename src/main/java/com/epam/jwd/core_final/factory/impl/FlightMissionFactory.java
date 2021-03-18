package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.EntityFactory;

public class FlightMissionFactory implements EntityFactory<FlightMission> {
    private FlightMission flightMission = null;
    private FlightMission enteredFightMission;
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
        for (Object s : args) {
            try {
                Class n = s.getClass();
                if (n.equals(FlightMission.class)) {
                    enteredFightMission = (FlightMission) s;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        flightMission = flightMissionCriteria
                .setStartDate(enteredFightMission.getStartDate())
                .setEndDate(enteredFightMission.getEndDate())
                .setDistance(enteredFightMission.getDistance())
                .setSpaceship(enteredFightMission.getAssignedSpaceShift())
                .setCrewList(enteredFightMission.getAssignedCrew())
                .setMissionResult(enteredFightMission.getMissionResult())
                .setName(enteredFightMission.getName())
                .build();
        return flightMission;
    }
}
