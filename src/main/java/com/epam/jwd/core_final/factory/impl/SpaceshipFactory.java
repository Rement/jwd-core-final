package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.util.HashMap;
import java.util.Map;

public class SpaceshipFactory implements EntityFactory<Spaceship> {
    private Spaceship spaceship = null;
    private SpaceshipCriteria.Builder spaceshipCriteria = SpaceshipCriteria.newBuilder();
    private static SpaceshipFactory instance;

    private SpaceshipFactory() {
    }

    public static SpaceshipFactory getInstance() {
        if (instance == null) {
            instance = new SpaceshipFactory();
        }
        return instance;
    }

    @Override
    public Spaceship create(Object... args) {

        Map<Role, Short> crew=new HashMap<>();
        crew.put(Role.COMMANDER, (short) 2);
        spaceship = spaceshipCriteria
                .setCrew(crew)
                .setFlightDistance(23L)
                .setReadyForNextMission(true)
                .setName("sssss")
                .build();
        return spaceship;
    }
}
