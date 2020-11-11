package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DefaultSpaceshipServiceImpl implements SpaceshipService {
    private List<Spaceship> spaceships;

    public DefaultSpaceshipServiceImpl(final List<Spaceship> spaceships) {
        this.spaceships = spaceships;
    }

    public DefaultSpaceshipServiceImpl(final ApplicationContext context) {
        this.spaceships = (List<Spaceship>) context.retrieveBaseEntityList(Spaceship.class);
    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        return spaceships;
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(SpaceshipCriteria criteria) {
        if (criteria == null) {
            throw new IllegalArgumentException("Criteria must not be null!");
        }

        Stream<Spaceship> stream = spaceships.stream();

        if (criteria.getId() != null) {
            stream = stream.filter(spaceship -> spaceship.getId().equals(criteria.getId()));
        }
        if (criteria.getName() != null) {
            stream = stream.filter(spaceship -> spaceship.getName().equals(criteria.getName()));
        }
        if (criteria.getCrew() != null) {
            stream = stream.filter(spaceship -> spaceship.getCrew().equals(criteria.getCrew()));
        }
        if (criteria.getFlightDistance() != null) {
            stream = stream.filter(spaceship -> spaceship.getFlightDistance().equals(criteria.getFlightDistance()));
        }
        if (criteria.isReadyForNextMissions() != null) {
            stream = stream.filter(spaceship -> spaceship.isReadyForNextMissions().equals(criteria.isReadyForNextMissions()));
        }

        return stream.collect(toList());
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(SpaceshipCriteria criteria) {
        List<Spaceship> spaceships = findAllSpaceshipsByCriteria(criteria);

        return spaceships.isEmpty()
                ? Optional.empty()
                : Optional.of(spaceships.get(0));
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        return null;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship crewMember) throws RuntimeException {

    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
        return null;
    }
}
