package com.epam.jwd.core_final.decorator.impl;

import com.epam.jwd.core_final.Main;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.decorator.BaseEntityDecorator;
import com.epam.jwd.core_final.decorator.PreProcessor;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.logger.JwdLogger;

import java.util.Collection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PreProcessorImpl extends BaseEntityDecorator implements PreProcessor {
    private static Logger logger = new JwdLogger(PreProcessorImpl.class.getName(), "slf4j");
    private BaseEntity baseEntity = null;
    private Collection<CrewMember> crewMembers;
    private Collection<Spaceship> spaceships;
    private Collection<FlightMission> flightMissions;
    private Boolean checkedEntity;

    public PreProcessorImpl(EntityFactory factory) {
        super(factory);
    }


    @Override
    public BaseEntity create(Object... args) {
        this.baseEntity = super.create(args);
        if (!preProcess()) {
            return baseEntity;
        }
        return null;
    }

    public boolean preProcess() {
        Class baseEntityClass = baseEntity.getClass();
        if (baseEntityClass.equals(CrewMember.class)) {
            checkedEntity = isEqualsCrewMember();
        }
        if (baseEntityClass.equals(Spaceship.class)) {
            checkedEntity = isEqualsSpaceship();
        }
        if (baseEntityClass.equals(FlightMission.class)) {
            checkedEntity = isEqualsFlightMission();
        }
        logger.log(Level.INFO, "is there already such an entity " + checkedEntity);
        return checkedEntity;
    }

    private boolean isEqualsCrewMember() {
        crewMembers = Main.getCrewMembers();
        CrewMember gettedCrewMember = (CrewMember) baseEntity;
        Optional<CrewMember> crewMember = crewMembers
                .stream()
                .filter(c -> c.getName().equals(gettedCrewMember.getName()))
                .findFirst();
        return crewMember.isPresent();
    }

    private boolean isEqualsSpaceship() {
        spaceships = Main.getSpaceships();
        Spaceship gettedSpaceShip = (Spaceship) baseEntity;
        Optional<Spaceship> spaceship = spaceships
                .stream()
                .filter(c -> c.getName().equals(gettedSpaceShip.getName()))
                .findFirst();
        return spaceship.isPresent();
    }

    private boolean isEqualsFlightMission() {
        flightMissions = Main.getFlightMissions();
        FlightMission gettedFlightMission = (FlightMission) baseEntity;
        Optional<FlightMission> flightMission = flightMissions
                .stream()
                .filter(c -> c.getName().equals(gettedFlightMission.getName()))
                .findFirst();
        return flightMission.isPresent();
    }
}
