package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;

public class SpaceshipFactory implements EntityFactory<Spaceship> {
    private Spaceship spaceship = null;
    private Spaceship enteredSpaceship;
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
        for (Object s : args) {
            try {
                Class n = s.getClass();
                if (n.equals(Spaceship.class)) {
                    enteredSpaceship = (Spaceship) s;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        spaceship = spaceshipCriteria
                .setCrew(enteredSpaceship.getCrew())
                .setFlightDistance(enteredSpaceship.getFlightDistance())
                .setReadyForNextMission(true)
                .setName(enteredSpaceship.getName())
                .build();
        return spaceship;
    }
}
