package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.AssignSpaceshipException;
import com.epam.jwd.core_final.exception.CreateSpaceshipException;
import com.epam.jwd.core_final.strategy.impl.ReadSpaceshipsFromFileStrategy;

import java.util.Collection;
import java.util.List;

/**
 * All its implementations should be a singleton
 * You have to use streamAPI for filtering, mapping, collecting, iterating
 */
public interface SpaceshipService {

    Collection<Spaceship> findAllSpaceships();
//TODO интересуюсь по поводу входного значения
//    List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria);

    List<Spaceship> findAllSpaceshipsByCriteria(SpaceshipCriteria criteria);

    void findSpaceshipByCriteria(SpaceshipCriteria criteria);

    Spaceship updateSpaceshipDetails(Spaceship spaceship);

  //  void updateSpaceshipDetails(Spaceship spaceship);


    // todo create custom exception for case, when spaceship is not able to be assigned
    void assignSpaceshipOnMission(Spaceship spaceship) throws AssignSpaceshipException;

    public void freedSpaceships(Spaceship spaceship);

    // todo create custom exception for case, when crewMember is not able to be created (for example - duplicate.
    // spaceship unique criteria - only name!
    Collection<Spaceship> createSpaceship(ReadSpaceshipsFromFileStrategy strategy) throws CreateSpaceshipException;
}
