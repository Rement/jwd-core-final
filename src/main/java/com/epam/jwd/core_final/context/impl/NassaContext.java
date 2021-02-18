package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.CreateCrewMemberException;
import com.epam.jwd.core_final.exception.CreateSpaceshipException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.strategy.impl.ReadCrewMembersFromFileStrategy;
import com.epam.jwd.core_final.strategy.impl.ReadSpaceshipsFromFileStrategy;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

// todo
public class NassaContext implements ApplicationContext {

    private static NassaContext instance;

    public static NassaContext getInstance() {
        if (instance == null) {
            instance = new NassaContext();
        }
        return instance;
    }

    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<FlightMission> flightMissions = new ArrayList<>();

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if (tClass.getName().equals(CrewMember.class.getName())) {
            return (Collection<T>) crewMembers;
        } else if (tClass.getName().equals(Spaceship.class.getName())) {
            return (Collection<T>) spaceships;
        } else if (tClass.getName().equals(FlightMission.class.getName())) {
            return (Collection<T>) flightMissions;
        } else {
            throw new IllegalArgumentException("Nope");
        }
    }

    /**
     * You have to read input files, populate collections
     *
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {
        CrewServiceImpl crewService = new CrewServiceImpl();
        SpaceshipServiceImpl spaceshipService = new SpaceshipServiceImpl();
        ReadCrewMembersFromFileStrategy crewStrategy = new ReadCrewMembersFromFileStrategy();
        ReadSpaceshipsFromFileStrategy spaceshipsStrategy = new ReadSpaceshipsFromFileStrategy();
        try {
            spaceshipsStrategy.readFromFile(ApplicationProperties.MAIN_PATH + ApplicationProperties.INPUT_ROOT_DIR + ApplicationProperties.SPACESHIPS_FILE_NAME);
            crewStrategy.readFromFile(ApplicationProperties.MAIN_PATH + ApplicationProperties.INPUT_ROOT_DIR + ApplicationProperties.CREW_FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            spaceships.addAll(spaceshipService.createSpaceship(spaceshipsStrategy));
        } catch (CreateSpaceshipException e) {
            e.printStackTrace();
        }
        try {
            crewMembers.addAll(crewService.createCrewMember(crewStrategy));
        } catch (CreateCrewMemberException e) {
            e.printStackTrace();
        }
    }
}
