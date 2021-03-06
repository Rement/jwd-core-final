package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.decorator.impl.PostProcessorImpl;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.List;
import java.util.Optional;

public class SpaceshipServiceImpl implements SpaceshipService {
    SpaceshipFactory factory = SpaceshipFactory.getInstance();
    PostProcessorImpl postProcessor;
    private static SpaceshipServiceImpl instance;

    private SpaceshipServiceImpl() {
    }

    public static SpaceshipServiceImpl getInstance() {
        if (instance == null) {
            instance = new SpaceshipServiceImpl();
        }
        return instance;
    }
    @Override
    public List<Spaceship> findAllSpaceships() {
        return null;
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        return null;
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        return Optional.empty();
    }

    @Override
    public Spaceship deleteSpaceship(Spaceship spaceship) {
        return null;
    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
        postProcessor = new PostProcessorImpl(factory.create(spaceship));
        postProcessor.create(factory.create(spaceship));
        return null;
    }
}
