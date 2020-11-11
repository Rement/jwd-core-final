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

    Collection<Spaceship> spaceships = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class);

    @Override
    public Collection<Spaceship> findAllSpaceships() {
        return spaceships;
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(SpaceshipCriteria criteria) {
        return spaceships.stream().filter(spaceship -> spaceship.getFlightDistance() > criteria.getFlightDistance()).collect(Collectors.toList());
    }

    @Override
    public void findSpaceshipByCriteria(SpaceshipCriteria criteria) {
        Optional.of(spaceships.stream().filter(s -> s.getName().equals(criteria.getName()))).ifPresent(spaceship -> System.out.println(spaceship.findAny().toString()));
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        return null;
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
