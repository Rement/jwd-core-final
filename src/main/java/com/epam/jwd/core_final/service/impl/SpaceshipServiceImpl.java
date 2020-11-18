package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.AssignSpaceshipException;
import com.epam.jwd.core_final.exception.CreateSpaceshipException;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.strategy.impl.ReadSpaceshipsFromFileStrategy;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpaceshipServiceImpl implements SpaceshipService {
    private static SpaceshipServiceImpl instance;
    Collection<Spaceship> spaceships = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class);

    public static SpaceshipServiceImpl getInstance() {
        if (instance == null) {
            instance = new SpaceshipServiceImpl();
            return instance;
        }
        return null;
    }

    @Override
    public Collection<Spaceship> findAllSpaceships() {
        return spaceships;
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(SpaceshipCriteria criteria) {
        return spaceships.stream().filter(spaceship -> spaceship.getFlightDistance() > criteria.getFlightDistance()).collect(Collectors.toList());
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(SpaceshipCriteria criteria) {
        return spaceships.stream().filter(s -> s.getName().equals(criteria.getName())).findFirst();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        Optional<Spaceship> newShip = spaceships.stream().filter(spaceship1 -> spaceship1.getName().equals(spaceship.getName()))
                .findFirst();
        if (newShip.isPresent()) {
            spaceships.remove(newShip.get());
            spaceships.add(spaceship);
        }
        return spaceship;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship) throws AssignSpaceshipException {
        spaceship.setReadyForNextMission(false);
    }

    @Override
    public void freedSpaceships(Spaceship spaceship) {
        spaceship.setReadyForNextMission(true);
    }

    @Override
    public Collection<Spaceship> createSpaceship(ReadSpaceshipsFromFileStrategy strategy) throws CreateSpaceshipException {
        return strategy.getSpaceships();
    }
}
